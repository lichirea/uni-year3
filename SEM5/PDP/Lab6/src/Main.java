import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws InterruptedException {
        ArrayList<List<Integer>> graph = new ArrayList<>(List.of(
                new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5)),

                new ArrayList<Integer>(Arrays.asList(2, 5)),
                new ArrayList<Integer>(Arrays.asList(2, 4, 5)),
                new ArrayList<Integer>(Arrays.asList(0, 1, 3)),
                new ArrayList<Integer>(Arrays.asList(2, 4)),
                new ArrayList<Integer>(Arrays.asList(0, 3, 5)),
                new ArrayList<Integer>(Arrays.asList(0, 1))));


        //generates a complete graph with size 'size'
        List<List<Integer>> bigGraph = new ArrayList<>();

        int size = 5;
        int x = 0;
        List<Integer> nodes = new ArrayList<>();
        while(x <= size) {
            nodes.add(x);
            x++;
        }
        bigGraph.add(nodes);
        List<Integer> copy;

        x = 0; int y;
        while(x <= size) {
            copy = new ArrayList<>();
            y = 0;
            while(y <= size) {
                copy.add(y);
                y++;
            }
            copy.remove(Integer.valueOf(y));
            bigGraph.add(copy);
            x++;
        }

        long startTime = System.nanoTime();
        findHamiltonianCycle(graph);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.print("Time: " + duration + "ms\n");
    }

    public static void findHamiltonianCycle(List<List<Integer>> graph) throws InterruptedException {
        ArrayList<Integer> way = new ArrayList<>();
        way.add(0); //starting point is 0

        recursiveSearch(graph, 0, way);
    }

    public static void recursiveSearch(List<List<Integer>> graph, int currentNode, ArrayList<Integer> way) throws InterruptedException {
        //if we can reach the first node from this node, and we already passed through all other nodes,
        //it means we found the cycle
        if (graph.get(currentNode+1).contains(0) && graph.get(0).size() == way.size()) {
            System.out.println("hamiltonian cycle ---> " + way); return;
        }

        //return if we didn't find a hamiltonian cycle but already went through all nodes
        if (graph.get(0).size() == way.size()) {
            return;
        }

        //go through all nodes; this could been written way simpler
        for (int i = 0; i < graph.get(0).size(); i++) {
            ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
            // check if we can get to this node and we arent returning
            if (graph.get(currentNode+1).contains(i) && !way.contains(i)) {

                //add the node to the current way
                way.add(i);
                //remove the node temporarily so we don't get stuck
                graph.get(currentNode+1).remove(Integer.valueOf(i)); //why does this have to get an object.... i spent 30 minutes on this

                List<List<Integer>> copiedGraph = new ArrayList<>(10000);
                graph.forEach(x -> {
                    List<Integer> omg = new ArrayList<>(1000);
                    omg.addAll(x);
                    copiedGraph.add(omg);
                });
                ArrayList<Integer> copiedWay = new ArrayList<>(10000);
                copiedWay.addAll(way);
                int finalI = i;
                final Runnable task = () -> {
                    try {
                        //start tasks that branch out to look for other solutions
                        recursiveSearch(copiedGraph, finalI, copiedWay);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                };
                //make sure the task is executed and finishes in the background
                Future<?> future = executor.submit(task);

                //put the node back into the graph at the current node (
                graph.get(currentNode+1).add(i);
                way.remove(way.size()-1);
            }

        }
    }
}