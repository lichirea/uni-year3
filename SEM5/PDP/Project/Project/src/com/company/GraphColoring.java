package com.company;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GraphColoring {

    public static Map<Integer, String> getGraphColoring(int ThreadsNr, Graph graph, Colors colors) throws Exception {
        Vector<Integer> codes = new Vector<>();

        int codesNr = colors.getColorsNumber();
        Vector<Integer> partialCodes = new Vector<>(Collections.nCopies(graph.getNodesNumber(),0));
        Lock lock = new ReentrantLock();

        grapColoringRecursive(new AtomicInteger(ThreadsNr),0,graph,codesNr,partialCodes,lock,codes);

        // if no solution is found
        if(codes.isEmpty()){
            throw new Exception("No solution");
        }

        //if soluton
        return colors.getNodesToColors(codes);
    }

    private static void grapColoringRecursive(AtomicInteger threadsNr, int node, Graph graph, int codesNr, Vector<Integer> partialCodes, Lock lock, Vector<Integer> codes)
    {

        System.out.println("Hello from " + Thread.currentThread().getName() + " I m computing for node " + node + " with codes " + partialCodes.toString());
        // if solution is found
        if(!codes.isEmpty())
        {
            return;
        }

        //solution
        if(node+1 == graph.getNodesNumber())
        {
            if ( isCodeValid(node,partialCodes,graph))
            {
                lock.lock();

                if(codes.isEmpty()){
                    codes.addAll(partialCodes);
                }

                lock.unlock();
            }

            return;
        }

        //candidate codes for next node

        int nextNode = node+1;

        List<Thread> threads = new ArrayList<>();
        List<Integer> validCodes = new ArrayList<>();

        for(int code = 0; code < codesNr; code++)
        {
            partialCodes.set(nextNode,code);

            if(isCodeValid(nextNode,partialCodes,graph))
            {

                if(threadsNr.getAndDecrement() > 0)
                {
                    Vector<Integer> nextPartialCodes = new Vector<>(partialCodes);

                    Thread thread = new Thread( () -> grapColoringRecursive(threadsNr,nextNode,graph,codesNr,nextPartialCodes,lock,codes));
                    thread.start();
                    threads.add(thread);
                }
                else{
                    validCodes.add(code);
                }
            }
        }

        //join threads
        for(Thread thread: threads)
        {
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //sequential
        for(int code: validCodes){
            partialCodes.set(nextNode,code);
            Vector<Integer> nextPartialCodes = new Vector<>(partialCodes);

            grapColoringRecursive(threadsNr,nextNode,graph,codesNr,nextPartialCodes,lock,codes);
        }
    }

    private static boolean isCodeValid(int node, Vector<Integer> codes, Graph graph)
    {
        for(int current = 0; current < node; current++)
            if((graph.isVertex(node,current) || graph.isVertex(current,node)) && codes.get(node) == codes.get(current).intValue())
            {
                return false;
            }


        return true;
    }
}
