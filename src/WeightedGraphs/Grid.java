package WeightedGraphs;

import javax.swing.*;
import java.awt.*;

class Grid {


    Grid(TestPane testPane) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                // TODO : look and feel manager ?
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Grid");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(testPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);


            }
        });
    }


    /*synchronized void paintGraph(PathFinding pf, TestPane testPane, Graph graph) {

        boolean done = pf.getDone();
        pf.Dijkstra(new int[]{8, 0}, new int[]{3, 8});

        while (!done) {

            testPane.getCameFrom(graph.getCameFrom());
            testPane.repaint();
            done = pf.getDone();
        }


    }*/



}






