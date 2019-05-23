package WeightedGraphs;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class TestPane extends JPanel {

    private int gridSize;
    private Map<Vertex,Integer> costMapping;
    private List<int[]> path;
    private Set<Vertex> cameFrom = new HashSet<>();

    private int[] source;
    private int[] goal;



    TestPane(int gridSize, Map<Vertex,Integer> costMapping) {
        this.gridSize = gridSize;
        this.costMapping = costMapping;

    }


    void getPath(List<int[]> path) { this.path = path; }

    void AddInCameFrom(Vertex a) {
        cameFrom.add(a);
    }

    // get source and goal
    void setSourceAndGoal(int[] source, int[] goal) {
        this.source = source;
        this.goal = goal;
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


                int[] v = new int[]{horz,vert};



                // draw rectangle
                g.drawRect(x, y, size, size);




                try {


                    /** IMPORTANT
                     *
                     * Passing the same HashMap cameFrom entirely every time the path finding algorithm visit an element will result
                     * in concurrency errors. Instead we use a HashSet and pass the single vertex that was just visited to the set, so
                     * previous values are not overridden.
                     *
                     *
                     * Also the order to which the elements are rendered is important. If we set the cameFrom elements after writing the cost number inside the
                     * rectangle, the cost will be erased.
                     * So be careful with the foreground / background filling of your elements. Here we will fill the colors, then overwrite them with the path
                     * when the path is found, then write the costs, Source and Goal as foreground text, IN LAST.
                     */

                    // update the visited vertices
                    if (cameFrom != null) {

                        for (Vertex V : cameFrom) {

                            // if the vertex in cameFrom is the current coord
                            if (Arrays.equals(V.getCoord(),v)) {


                                g.setColor(Color.LIGHT_GRAY);
                                g.fillRect(x + 1, y + 1, size - 1, size - 1);
                                g.setColor(Color.BLACK);


                            }
                        }
                    }


                    //only draw path at the end when it is done
                    if (path != null) {

                        for (int[] i : path) {
                            if (Arrays.equals(i, v)) {


                                g.setColor(Color.GREEN);
                                g.fillRect(x + 1, y + 1, size - 1, size - 1);
                                g.setColor(Color.BLACK);


                            }
                        }
                    }




                    // draw source and goal
                    if (source != null && goal != null) {

                        if (Arrays.equals(source,v)) {
                            g.setColor(Color.PINK);
                            g.fillRect(x + 1, y + 1, size - 1, size - 1);
                            g.setColor(Color.BLACK);
                            g.drawString("S", x+size/2,y+size/2);
                        }

                        else if (Arrays.equals(goal,v)) {
                            g.setColor(Color.RED);
                            g.fillRect(x + 1, y + 1, size - 1, size - 1);
                            g.setColor(Color.BLACK);
                            g.drawString("G", x+size/2,y+size/2);
                        }

                        else {
                            String cost = Integer.toString(costMapping.get(new Vertex(v)));
                            // draw cost value inside
                            g.drawString(cost, x+size/2,y+size/2);
                        }


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
