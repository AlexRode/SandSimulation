import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class StoneParticle extends Particle {
    private Vector position; // Posição da partícula
    private Vector velocity; // Velocidade da partícula
    private Vector force;    // Força total agindo sobre a partícula

    public StoneParticle() {
        super("Stone", Color.GRAY);
        
        this.velocity = new Vector(0, 0);
        this.force = new Vector(0, 0);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }

    @Override
    public void update(Particle[][] grid, int x, int y) {
        int gridHeight = grid[0].length;
        
        // Verifique se a partícula não está na parte inferior do grid
        if (y < gridHeight - 1) {
            // Se a célula abaixo estiver vazia, a areia cai para essa célula
            if (grid[x][y + 1] == null) {
                grid[x][y + 1] = this;
                grid[x][y] = null;
            }
            // Adicione aqui lógica adicional para movimento diagonal, se desejado
        }
    }

    
}
