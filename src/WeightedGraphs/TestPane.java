package WeightedGraphs;

import java.awt.*;
import javax.swing.*;

public class TestPane extends JPanel {

    public TestPane() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    // paint the grid
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO : Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();


        int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
        int width = getWidth() - (size * 2);
        int height = getHeight() - (size * 2);


        int y = (getHeight() - (size * 10)) / 2;
        for (int horz = 0; horz < 10; horz++) {
            int x = (getWidth() - (size * 10)) / 2;
            for (int vert = 0; vert < 10; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        g2d.dispose();
    }


}
