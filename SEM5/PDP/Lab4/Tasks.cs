using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lab4
{
    static class Tasks
    {
        public static void Run(List<string> UrlList)
        {
            //create a task for each url
            var taskList = new List<Task>();
            UrlList.ForEach((url) => taskList.Add(Task.Factory.StartNew(Start, url)));

            //waits for all tasks to finish
            Task.WaitAll(taskList.ToArray());
            Thread.Sleep(5000);

        }

        public static void Start(object url)
        {
            //we are already in the thread here
            //start the connection
            var urlString = (string)url;
            var host = Dns.GetHostEntry(urlString);
            var ipAddress = host.AddressList[0];
            var remoteEndpoint = new IPEndPoint(ipAddress, 80);

            var socket = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

            var state = new State
            {
                socket = socket,
                url = urlString,
                endpointPath = "/",
                remoteEndpoint = remoteEndpoint
            };

            //this function returns another task, so it waits for it to finish
            Connect(state).Wait();


            //the request is another task
            var request = "GET " + state.endpointPath + " HTTP/1.1\r\n" +
                   "Host: " + state.url + "\r\n" +
                   "Content-Length: 0\r\n\r\n" +
                   "Content-Type: text/html";
            Send(state, request).Wait();

            //same here, the function returns a task which is waited for
            Receive(state).Wait();
        }

        private static Task Connect(State state)
        {
            //same all the way down
            state.socket.BeginConnect(state.remoteEndpoint, OnConnect, state);

            return Task.FromResult(state.connected.WaitOne());
        }

        private static void OnConnect(IAsyncResult result)
        {
            Console.Out.WriteLine("Hello from thread " + Thread.CurrentThread.ManagedThreadId);
            var state = (State)result.AsyncState;

            state.socket.EndConnect(result);

            Console.WriteLine("Thread " + Thread.CurrentThread.ManagedThreadId + " connected to " + state.url + " on " + state.remoteEndpoint);

            state.connected.Set();
        }

        private static Task Send(State state, string request)
        {
            var byteData = Encoding.ASCII.GetBytes(request);

            state.socket.BeginSend(byteData, 0, byteData.Length, 0, OnSend, state);

            return Task.FromResult(state.sent.WaitOne());
        }

        private static void OnSend(IAsyncResult result)
        {
            var state = (State)result.AsyncState;

            var bytesSent = state.socket.EndSend(result);
            Console.WriteLine("Thread " + Thread.CurrentThread.ManagedThreadId + " sent some stuff to server");

            state.sent.Set();
        }

        private static Task Receive(State state)
        {
            state.socket.BeginReceive(state.buffer, 0, 512, 0, OnReceive, state);

            return Task.FromResult(state.received.WaitOne());
        }

        private static void OnReceive(IAsyncResult result)
        {
            var state = (State)result.AsyncState;
            Console.Out.WriteLine("i received some stuff on thread " + Thread.CurrentThread.ManagedThreadId);
            try
            {
                var bytesRead = state.socket.EndReceive(result);

                state.response += Encoding.ASCII.GetString(state.buffer, 0, bytesRead);

                //if not done
                if (!state.response.ToString().Contains("</html>"))
                {
                    state.socket.BeginReceive(state.buffer, 0, 512, 0, OnReceive, state);
                    return;
                }

                Console.WriteLine(state.response);

                state.received.Set();

                // release the socket
                state.socket.Shutdown(SocketShutdown.Both);
                state.socket.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }
    }
}