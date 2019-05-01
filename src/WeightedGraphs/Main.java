package WeightedGraphs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        int H = 4;
        int W = 4;


        Graph testGraph = new Graph(H,W);

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                testGraph.addVertex(i,j,1);
            }
        }

       /* int[] i = new int[]{1,1};
        int[] j = new int[]{1,1};
        System.out.println(Arrays.hashCode(i));
        System.out.println(Arrays.hashCode(j));
        System.out.println(Arrays.equals(i,j));*/

        testGraph.getElements();

        int c = testGraph.getCost(2,2);
        System.out.println("2,2 cost: " + c);

        testGraph.addNeighbours();

        int t = testGraph.getSize();
        System.out.println("graph size: " + t);




        // testing BFS
        /*Set<String> visitedNaive = testGraph.BreadthFirstSearch(testGraph,"10");

        for (String s : visitedNaive) {
            System.out.print(s + " ");
        }*/

        /*Map<String,String> cameFrom = testGraph.Dijkstra(testGraph,"10","7",2);

        List<String> path = testGraph.Path_reconstruct(cameFrom,"10","7");

        for (String s : path) { System.out.print(s + " "); }*/




    }
}
