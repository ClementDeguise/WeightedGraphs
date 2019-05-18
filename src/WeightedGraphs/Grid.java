package WeightedGraphs;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Grid {



    public Grid() {
        EventQueue.invokeLater(new Runnable() {
               @Override
               public void run() {

                   // TODO : look and feel manager ?
                   try {
                       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                   }
                   catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                       ex.printStackTrace();
                   }

                   JFrame frame = new JFrame("Grid");
                   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   frame.add(new TestPane());
                   frame.pack();
                   frame.setLocationRelativeTo(null);
                   frame.setVisible(true);


               }
           });
    }









    public static void main(String[] args) {

        new Grid();







        int H = 4;
        int W = 4;


        Graph testGraph = new Graph(H,W);

        /*for (int i = 0; i < H-1; i++) {
            for (int j = 0; j < W-1; j++) {
                testGraph.addVertex(i,j,1);
            }
        }
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

        Map<Vertex,Vertex> cameFrom = testGraph.Dijkstra(testGraph,new int[]{0,0},new int[]{2,2});

        List<int[]> path = testGraph.Path_reconstruct(cameFrom,new int[]{0,0},new int[]{2,2});

        //System.out.print();

        for (int[]s : path) { System.out.print(Arrays.toString(s) + " "); }*/




    }
}
