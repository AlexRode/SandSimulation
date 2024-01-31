package scr.Particles;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Particle {
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
    public Particle getNeighbor(Particle[][] grid, int x, int y, int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
            return grid[newX][newY];
        }
        return null; // Fora dos limit
    }
}

