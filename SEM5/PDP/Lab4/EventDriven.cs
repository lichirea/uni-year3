using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Lab4
{
    static class EventDriven
    {
        public static void Run(List<string> UrlList)
        {
            //begin the process for each url
            UrlList.ForEach((url) => Start(url));
        }

        public static void Start(string url)
        {
            //setup for connection
            var host = Dns.GetHostEntry(url);
            var ipAddress = host.AddressList[0];
            var remoteEndpoint = new IPEndPoint(ipAddress, 80);

            var socket = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

            var state = new State
            {
                socket = socket,
                url = url,
                endpointPath = "/",
                remoteEndpoint = remoteEndpoint
            };

            //beginconnect
            socket.BeginConnect(remoteEndpoint, OnConnect, state);
        }

        public static void OnConnect(IAsyncResult result)
        {
            //callback function after connection
            Console.Out.WriteLine("Hello from thread " + Thread.CurrentThread.ManagedThreadId);
            var state = (State)result.AsyncState;

            //confirm the connection? idk why this as to be here
            state.socket.EndConnect(result);
            Console.WriteLine("Thread " + Thread.CurrentThread.ManagedThreadId + " connected to " + state.url + " on " + state.remoteEndpoint);

            //construct the request
            var request = "GET " + state.endpointPath + " HTTP/1.1\r\n" +
                   "Host: " + state.url + "\r\n" +
                   "Content-Length: 0\r\n\r\n" +
                   "Content-Type: text/html";

            var byteData = Encoding.ASCII.GetBytes(request);

            //send the request
            state.socket.BeginSend(byteData, 0, byteData.Length, 0, OnSend, state);
        }

        private static void OnSend(IAsyncResult result)
        {
            //callback function for request sending
            var state = (State)result.AsyncState;

            //confirm the sending??
            var bytesSent = state.socket.EndSend(result);
            Console.WriteLine("Thread " + Thread.CurrentThread.ManagedThreadId + " sent some stuff to server");

            //start to receive response
            state.socket.BeginReceive(state.buffer, 0, 512, 0, OnReceive, state);
        }

        private static void OnReceive(IAsyncResult result)
        {
            //callback function for receiving
            var state = (State)result.AsyncState;
            Console.Out.WriteLine("i received some stuff on thread " + Thread.CurrentThread.ManagedThreadId);
            try
            {
                //confirm connection
                var bytesRead = state.socket.EndReceive(result);

                //add the resposne to the response buffer
                state.response += Encoding.ASCII.GetString(state.buffer, 0, bytesRead);

                //if not done, receive again
                if (!state.response.ToString().Contains("</html>"))
                {
                    state.socket.BeginReceive(state.buffer, 0, 512, 0, OnReceive, state);
                    return;
                }

                Console.WriteLine(state.response);

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