import mpi.*;

//-jar $MPJ_HOME$\lib\starter.jar -np 3
//MPJ_HOME=c:\mpj
public class Main {
    public static void main(String[] args) {
        MPI.Init(args);

        System.out.println("Starting");

        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        System.out.println("Hello world from " + me + " and the size is " + size);
        //example
        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(4,0);
        graph.addEdge(2,0);
        graph.addEdge(0,4);
        graph.addEdge(4,3);
        graph.addEdge(3,1);

        Colors colors = new Colors(3);
        colors.addCodeToColor(0, "red");
        colors.addCodeToColor(1, "green");
        colors.addCodeToColor(2, "blue");

        //parent
        if(me == 0) {
            try {
                long tic = System.nanoTime();

                System.out.println("final solution: " + GraphColoring.nColoring(size, graph, colors));

                long tac = System.nanoTime();
                long time = tac - tic;

                System.out.println("it took " + (time / 1000) + "ms");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //child
        else {
            int codesNo = colors.getColorsNumber();
            GraphColoring.child(me, size, graph, codesNo, colors);
        }



        System.out.println("\nbyebye");
        MPI.Finalize();
    }
}