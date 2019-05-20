package WeightedGraphs;

import javax.swing.*;
import java.awt.*;

public class Grid {


    public Grid(TestPane testPane) {
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


}






