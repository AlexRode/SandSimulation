import java.awt.Color;
import java.awt.Graphics;

abstract class Particle {
    // Propriedades comuns, como cor
    protected String name;
    protected Color color;

    // Construtor
    public Particle(String name, Color color) {
        this.color = color;
        this.name=name;
    }

    // Método abstrato para desenhar a partícula
    public abstract void draw(Graphics g, int x, int y, int size);
    
    public abstract void update(Particle[][] grid, int x, int y);

}

