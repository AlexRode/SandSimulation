import java.awt.Color;
import java.awt.Graphics;

abstract class Particle {
    // Propriedades comuns, como cor
    protected String name;
    protected Color color;
    public double lateralDispersion ;
    // Construtor
    public Particle(Color color) {
        this.color = color;
        
    }

    // Método abstrato para desenhar a partícula
    public abstract void draw(Graphics g, int x, int y, int size);
    
    public abstract void update(Particle[][] grid, int x, int y);

}

