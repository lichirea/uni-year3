import mpi.MPI;

import java.util.*;


public class GraphColoring {
    public static void work(Graph graph, int start, int end, int me, Colors colors) {
        int [] solution = new int[graph.getVertexCount()];
        for(int i = start; i <= end; i++) {
            solution = indexToSolution(i, colors.getColorsNumber(), graph.getVertexCount());
            System.out.println(me + " is checking the solution " + i + ": " + colors.getNodesToColors(solution));
            if(check(solution, graph)){
                System.out.println(me + " FOUND A SOLUTION " + colors.getNodesToColors(solution));
            }
        }
    }

    public static int[] indexToSolution(int index, int colorCount, int vertexCount) {
        int[] solution = new int[vertexCount];
        for (int i = vertexCount - 1; i >= 0; i--) {
            solution[i] = index % colorCount;
            index = index / colorCount;
        }
        return solution;
    }

    public static boolean check(int[] codes, Graph graph) {
        for (int currentNode = 0; currentNode < graph.getVertexCount(); currentNode++) {
            for (int n : graph.getVertices().get(currentNode)) {
                if ((graph.isEdge(n, currentNode) || graph.isEdge(currentNode, n)) && codes[n] == codes[currentNode])
                    return false;
            }
        }
        return true;
    }
}
