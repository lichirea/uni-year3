using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Lab4
{
    static class AsyncAwaitTasks
    {
        public static void Run(List<string> UrlList)
        {
            //this is almost the same as the tasks
            var taskList = new List<Task>();
            UrlList.ForEach((url) => taskList.Add(Task.Factory.StartNew(Start, url)));

            Task.WaitAll(taskList.ToArray());
            Thread.Sleep(5000);

        }

        public static async void Start(object url)
        {
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

            //except that here we await the result intead of using .Wait() from Task
            await Connect(state);

            var request = "GET " + state.endpointPath + " HTTP/1.1\r\n" +
                   "Host: " + state.url + "\r\n" +
                   "Content-Length: 0\r\n\r\n" +
                   "Content-Type: text/html";
            await Send(state, request);

            await Receive(state);
        }

        private static async Task Connect(State state)
        {
            state.socket.BeginConnect(state.remoteEndpoint, OnConnect, state);

            await Task.FromResult(state.connected.WaitOne());
        }

        private static void OnConnect(IAsyncResult result)
        {
            Console.Out.WriteLine("Hello from thread " + Thread.CurrentThread.ManagedThreadId);
            var state = (State)result.AsyncState;

            state.socket.EndConnect(result);

            Console.WriteLine("Thread " + Thread.CurrentThread.ManagedThreadId + " connected to " + state.url + " on " + state.remoteEndpoint);

            state.connected.Set();
        }

        private static async Task Send(State state, string request)
        {
            var byteData = Encoding.ASCII.GetBytes(request);

            state.socket.BeginSend(byteData, 0, byteData.Length, 0, OnSend, state);

            await Task.FromResult(state.sent.WaitOne());
        }

        private static void OnSend(IAsyncResult result)
        {
            var state = (State)result.AsyncState;

            var bytesSent = state.socket.EndSend(result);
            Console.WriteLine("Thread " + Thread.CurrentThread.ManagedThreadId + " sent some stuff to server");

            state.sent.Set();
        }

        private static async Task Receive(State state)
        {
            state.socket.BeginReceive(state.buffer, 0, 512, 0, OnReceive, state);

            await Task.FromResult(state.received.WaitOne());
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