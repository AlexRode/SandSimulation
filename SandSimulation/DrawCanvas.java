import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawCanvas extends JPanel {
    private BufferedImage canvas;
    private int width;
    private int height;
    private int gridSize;

    public DrawCanvas(BufferedImage canvas, int width, int height, int gridSize) {
        this.canvas = canvas;
        this.width = width;
        this.height = height;
        this.gridSize = gridSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);

       g.setColor(Color.GRAY);

       //desenhar o gri
       /*  for (int i = 0; i < width; i += gridSize) {
            g.drawLine(i, 0, i, height);
        }
        for (int i = 0; i < height; i += gridSize) {
            g.drawLine(0, i, width, i);
        }
         */
    }
}
