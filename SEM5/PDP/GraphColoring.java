import mpi.MPI;

import java.util.*;


public class GraphColoring {
        public static Map<Integer, String> nColoring(int size, Graph graph, Colors colors) throws Exception {
            int codesNo = colors.getColorsNumber();
            int[] codes = grapColoringRecursive(0, graph, codesNo, new int[graph.getVertexCount()], 0, size, 0, colors);

            //solution
            return colors.getNodesToColors(codes);
        }

        public static void child(int mpiMe, int mpiSize, Graph graph, int codesNo, Colors colors) {
            //receive data
            int[] data = new int[3];
            MPI.COMM_WORLD.Recv(data, 0, 3, MPI.INT, MPI.ANY_SOURCE, MPI.ANY_TAG);

            int parent = data[0];
            int node = data[1];
            int power = data[2];

            int[] codes = new int[graph.getVertexCount()];
            MPI.COMM_WORLD.Recv(codes, 0, graph.getVertexCount(), MPI.INT, MPI.ANY_SOURCE, MPI.ANY_TAG);

            //recursive function call
            int[] newCodes = grapColoringRecursive(node, graph, codesNo, codes, mpiMe, mpiSize, power, colors);

            //send data
            MPI.COMM_WORLD.Send(newCodes, 0, graph.getVertexCount(), MPI.INT, parent, 0);
        }

        private static int[] grapColoringRecursive(int node, Graph graph, int codesNr, int[] codes, int me, int size, int power, Colors colors)
        {

            System.out.println(me + " is checking " + colors.getNodesToColors(codes));

            //invalid solution
            if (!check(node, codes, graph)) {
                int[] badResult = new int[graph.getVertexCount()];
                Arrays.fill(badResult, -1);
                return badResult;
            }

            //solution found
            if(node+1 == graph.getVertexCount()) {
                return codes;
            }


            //get valid processes to continue the work
            int coefficient = (int) Math.pow(codesNr, power);
            int code = 0;
            int destination = me + coefficient * (code + 1);

            while(code + 1 < codesNr && destination < size) {
                code++;
                destination = me + coefficient * (code + 1);
            }


            //send data
            int nextNode = node + 1;
            int nextPower = power + 1;

            for(int currentCode = 1; currentCode < code; currentCode++) {
                destination = me + coefficient * currentCode;
                System.out.println(me + " chooses " + destination);
                int[] data = new int[]{me, nextNode, nextPower};
                MPI.COMM_WORLD.Send(data, 0, data.length, MPI.INT, destination, 0);

                //i hate java
                int[] nextCodes = codes.clone();
                nextCodes[nextNode] = currentCode;

                MPI.COMM_WORLD.Send(nextCodes, 0, graph.getVertexCount(), MPI.INT, destination, 0);

            }

            //try code 0 for next node on this process
            int[] nextCodes = codes.clone();
            nextCodes[nextNode] = 0;


            int[] result = grapColoringRecursive(nextNode, graph, codesNr, nextCodes, me, size, nextPower, colors);
            if(result[0] != -1) {
                return result;
            }

            //receive data from destination processes
            for(int currentCode =1; currentCode < code; currentCode++) {
                destination = me + coefficient * currentCode;

                result = new int[graph.getVertexCount()];
                MPI.COMM_WORLD.Recv(result, 0, graph.getVertexCount(), MPI.INT, destination, MPI.ANY_TAG);

                if(result[0] != -1) {
                    return result;
                }
            }

            //try remaining codes for next node on this process (if any)
            for(int currentCode = code; currentCode < codesNr; currentCode++) {
                nextCodes = codes.clone();
                nextCodes[nextNode] = currentCode;

                result = grapColoringRecursive(nextNode, graph, codesNr, nextCodes, me, size, power, colors);
                if(result[0] != -1) {
                    return result;
                }
            }

            //invalid solution (array of -1)
            return result;
        }

        private static boolean check(int node, int[] codes, Graph graph) {
            for (int currentNode = 0; currentNode < node; currentNode++)
                if ((graph.isEdge(node, currentNode) || graph.isEdge(currentNode, node)) && codes[node] == codes[currentNode])
                    return false;

            return true;
        }

    }
