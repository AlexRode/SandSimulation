import java.awt.Graphics;

public abstract class Particle {
    protected int x, y;
    protected ParticleType type;

    public Particle(int x, int y, ParticleType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public abstract void update(); 
    public abstract void draw(Graphics g);
// Método para atualizar o estado da partícula
}


