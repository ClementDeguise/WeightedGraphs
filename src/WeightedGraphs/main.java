package WeightedGraphs;

import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {

        Graph testGraph = new Graph();

        testGraph.addVertex("0",3);
        testGraph.addVertex("1",3);
        testGraph.addVertex("2",1);
        testGraph.addVertex("3",4);
        testGraph.addVertex("4",2);
        testGraph.addVertex("5",1);
        testGraph.addVertex("6",1);
        testGraph.addVertex("7",2);
        testGraph.addVertex("8",1);
        testGraph.addVertex("9",1);
        testGraph.addVertex("10",0);
        testGraph.addVertex("11",1);
        testGraph.addVertex("12",1);
        testGraph.addVertex("13",1);
        testGraph.addVertex("14",1);
        testGraph.addVertex("15",1);

        //List<Vertex> V = testGraph.getNeighbours("0");
        //System.out.println(testGraph.getVertex("0"));

        testGraph.addNeighbours("0","1",3,3);
        testGraph.addNeighbours("10","2",0,1);
        testGraph.addNeighbours("11","1",1,3);
        testGraph.addNeighbours("11","12",1,1);
        testGraph.addNeighbours("2","0",1,3);
        testGraph.addNeighbours("0","4",3,2);
        testGraph.addNeighbours("4","13",2,1);
        testGraph.addNeighbours("8","3",1,4);
        testGraph.addNeighbours("3","7",4,2);
        testGraph.addNeighbours("7","14",2,1);
        testGraph.addNeighbours("9","5",1,1);
        testGraph.addNeighbours("5","6",1,1);
        testGraph.addNeighbours("6","15",1,1);
        testGraph.addNeighbours("2","8",1,1);
        testGraph.addNeighbours("8","9",1,1);
        testGraph.addNeighbours("10","1",0,3);
        testGraph.addNeighbours("0","3",3,4);
        testGraph.addNeighbours("3","5",4,1);
        testGraph.addNeighbours("11","4",1,2);
        testGraph.addNeighbours("4","7",2,2);
        testGraph.addNeighbours("7","6",2,1);
        testGraph.addNeighbours("12","13",1,1);
        testGraph.addNeighbours("13","14",1,1);
        testGraph.addNeighbours("14","15",1,1);

        //testGraph.getElements();

        //int t = testGraph.getSize();
        //System.out.println("graph size: " + t);


        // testing BFS
        /*Set<String> visitedNaive = testGraph.BreadthFirstSearch(testGraph,"10");

        for (String s : visitedNaive) {
            System.out.print(s + " ");
        }*/

        Map<String,String> cameFrom = testGraph.Dijkstra(testGraph,"10","7",2);

        List<String> path = testGraph.Path_reconstruct(cameFrom,"10","7");

        for (String s : path) { System.out.print(s + " "); }




    }
}
