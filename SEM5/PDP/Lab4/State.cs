using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace Lab4
{
    class State
    {
        public ManualResetEvent connected = new ManualResetEvent(false);
        public ManualResetEvent sent = new ManualResetEvent(false);
        public ManualResetEvent received = new ManualResetEvent(false);
        public Socket socket = null;
        public string url;
        public string endpointPath;
        public IPEndPoint remoteEndpoint;
        public byte[] buffer = new byte[512];
        public string response = "";
    }
}
