package WeightedGraphs;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import java.util.List;


public class TestPane extends JPanel {

    private int gridSize;
    private Map<Vertex,Integer> costMapping;
    private Map<Vertex,Vertex> cameFrom;
    private Timer timer;
    private List<int[]> path;


    TestPane(int gridSize, Map<Vertex,Integer> costMapping, Map<Vertex,Vertex> cameFrom, List<int[]> path) {
        this.gridSize = gridSize;
        this.costMapping = costMapping;
        this.cameFrom = cameFrom;
        this.path = path;

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }


    // TODO need to convert the absolute coordinates to the vertex coordinates and return them.
    // they need to be accessed to mark the rectangle as visited





    // paint the grid
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO : Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();


        // global size of the grid, here it covers all the window
        int size = Math.min(getWidth() - 4, getHeight() - 4) / gridSize;


        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);


        // y position absolute
        int y = (getHeight() - (size * gridSize)) / 2;

        for (int horz = 0; horz < gridSize; horz++) {

            // x position absolute
            int x = (getWidth() - (size * gridSize)) / 2;

            // vert, horz relative positions
            for (int vert = 0; vert < gridSize; vert++) {

                // draw rectangle
                g.drawRect(x, y, size, size);
                //Shape rectangle = new Rectangle(x, y, size, size);
                //g.setColor(Color.WHITE);
                try {

                    int[] v = new int[]{horz,vert};

                    String cost = Integer.toString(costMapping.get(new Vertex(v)));
                    // draw cost value inside
                    g.drawString(cost, x+size/2,y+size/2);


                    if (path.contains(v)) {
                        g.setColor(Color.RED);
                        g.drawRect(x+size/4,y+size/4,size/2,size/2);

                    }



                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }






}
