import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawCanvas extends JPanel {
    private BufferedImage canvas;
    private SandSimulation simulation;

    public DrawCanvas(SandSimulation simulation, BufferedImage canvas) {
        this.simulation = simulation;
        this.canvas = canvas;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Desenha cada partícula de areia
        for (SandParticle particle : simulation.getParticles()) {
            particle.draw(g);
        }
    }
    
  /*   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, this);

        // Desenha cada partícula de areia
        for (SandParticle particle : simulation.getParticles()) {
            particle.draw(g);
        }
    }
    */
}


