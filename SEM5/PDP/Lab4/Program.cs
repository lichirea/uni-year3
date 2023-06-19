using System;
using System.Collections.Generic;

namespace Lab4
{
    class Program
    {
        static void Main(string[] args)
        {
            List<string> UrlList = new List<string>(){
                "www.google.com",
                "www.wikipedia.com",
                "www.stackoverflow.com",
            };

            //EventDriven.Run(UrlList);
            // Tasks.Run(UrlList);
            AsyncAwaitTasks.Run(UrlList);

            Console.Out.WriteLine("great job!!!");
        }
    }
}
