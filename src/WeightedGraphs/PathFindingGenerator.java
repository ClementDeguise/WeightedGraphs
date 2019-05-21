package WeightedGraphs;


import java.util.Arrays;
import java.util.List;
import java.util.Map;



public class PathFindingGenerator {


    public static void main(String[] args) {




        // set graph height and width
        int height = 10;
        int width = 10;


        // Graph structure with neighbours
        Graph testGraph = new Graph(height,width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                testGraph.addVertex(i,j,1);
            }
        }

        testGraph.addNeighbours();


        // change some costs
        testGraph.addCost(3,3,30);
        testGraph.addCost(3,4,30);
        testGraph.addCost(3,5,30);
        testGraph.addCost(3,6,30);
        testGraph.addCost(4,3,30);
        testGraph.addCost(4,4,30);
        testGraph.addCost(4,6,30);
        testGraph.addCost(5,3,30);
        testGraph.addCost(6,3,30);



        // --------------------------------



        // pass the graph to the algo
        PathFinding pf = new PathFinding(testGraph);

        //algo
        pf.Dijkstra(new int[]{0,0},new int[]{4,5});

        //find the path taken
        List<int[]> path = pf.Path_reconstruct(new int[]{0,0},new int[]{4,5});




        TestPane testPane = new TestPane(height, testGraph.getCostMapping(),path);
        new Grid(testPane);












        /*
        testGraph.addVertex(0,0,0);
        testGraph.addVertex(0,1,3);
        testGraph.addVertex(0,2,1);
        testGraph.addVertex(0,3,1);
        testGraph.addVertex(1,0,1);
        testGraph.addVertex(1,1,3);
        testGraph.addVertex(1,2,2);
        testGraph.addVertex(1,3,1);
        testGraph.addVertex(2,0,1);
        testGraph.addVertex(2,1,4);
        testGraph.addVertex(2,2,2);
        testGraph.addVertex(2,3,1);
        testGraph.addVertex(3,0,1);
        testGraph.addVertex(3,1,1);
        testGraph.addVertex(3,2,1);
        testGraph.addVertex(3,3,1);



       /* int[] i = new int[]{1,1};
        int[] j = new int[]{1,1};
        System.out.println(Arrays.hashCode(i));
        System.out.println(Arrays.hashCode(j));
        System.out.println(Arrays.equals(i,j));

        //testGraph.getElements();


        //Integer c = testGraph.Cost(2,2);
        //System.out.println("2,2 cost: " + c);

        Vertex v = new Vertex(new int[] {2,2},1);
        System.out.println(v.getCost().hashCode() );
        System.out.println(v.getCost().hashCode() );

        //int[] x = new int[]{1,1};
        //int[] y = new int[]{1,1};
        /*Vertex x = new Vertex(new int[]{1,1});
        Vertex y = new Vertex(new int[]{1,1});

        System.out.println(x.hashCode());
        System.out.println(y.hashCode());
        System.out.println(x.equals(y) );



        testGraph.addCost(1,1,1);

        Integer c = testGraph.Cost(1,1);
        System.out.println(c);

        //testGraph.addCost(y,1);
        c = testGraph.Cost(1,1);
        System.out.println(c);


        testGraph.addNeighbours();



        int t = testGraph.getSize();
        System.out.println("graph size: " + t);




        // testing BFS
        /*Set<String> visitedNaive = testGraph.BreadthFirstSearch(testGraph,"10");

        for (String s : visitedNaive) {
            System.out.print(s + " ");
        }

        Map<Vertex,Vertex> cameFrom = testGraph.Dijkstra(testGraph,new int[]{0,0},new int[]{4,9});

        List<int[]> path = testGraph.Path_reconstruct(cameFrom,new int[]{0,0},new int[]{2,2});

        //System.out.print();

        for (int[]s : path) { System.out.print(Arrays.toString(s) + " "); }*/




    }
}
