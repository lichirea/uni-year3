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

        Colors colors = new Colors(5);
        colors.addCodeToColor(0, "red");
        colors.addCodeToColor(1, "green");
        colors.addCodeToColor(2, "blue");
        colors.addCodeToColor(3, "purple");
        colors.addCodeToColor(4, "yellow");

        int totalSolutions = (int) Math.pow(colors.getColorsNumber(), graph.getVertexCount());

        //parent
        if(me == 0) {
            try {
                for (int count = 1; count < size; count++) {
                    int[] data = new int[1];
                    data[0] = count;
                    MPI.COMM_WORLD.Send(data, 0, 1, MPI.INT, count, 0);
                }

                GraphColoring.work(graph, 0, totalSolutions/size, me, colors);


            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //child
        else {
            int[] data = new int[1];
            MPI.COMM_WORLD.Recv(data, 0, 1, MPI.INT, 0, MPI.ANY_TAG);
            int count = data[0];

            GraphColoring.work(graph, (totalSolutions/size) * count, totalSolutions/size * (count+1), me, colors);
            if(me == size - 1) {
                GraphColoring.work(graph, (totalSolutions/size) * count, totalSolutions - 1, me, colors);
            }
        }

        System.out.println("\nbyebye from " + me);
        MPI.Finalize();
    }
}