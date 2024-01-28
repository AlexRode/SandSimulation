import java.awt.Color;
import java.awt.Graphics;

public class StoneParticle extends Particle {

    public StoneParticle() {
        super("Stone", Color.GRAY);
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
