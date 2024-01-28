import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class SandParticle extends Particle {

    public SandParticle() {
        super("SAND", Color.YELLOW); // Cor específica para areia
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }

    @Override
    public void update(Particle[][] grid, int x, int y) {
        int gridWidth = grid.length;
        int gridHeight = grid[0].length;
        Random random = new Random(); // Para a aleatoriedade
    
        // Verifica se não está na parte inferior do grid
        if (y < gridHeight - 1) {
            boolean moved = false;
    
            // Verifica a célula diretamente abaixo
            if (grid[x][y + 1] == null) {
                grid[x][y + 1] = this;
                grid[x][y] = null;
                moved = true;
            } else {
                // Decide aleatoriamente cair para a esquerda ou para a direita
                int dir = random.nextBoolean() ? 1 : -1;
    
                // Verifica se a célula diagonal na direção escolhida está disponível
                if (x + dir >= 0 && x + dir < gridWidth && grid[x + dir][y + 1] == null) {
                    grid[x + dir][y + 1] = this;
                    grid[x][y] = null;
                    moved = true;
                }
                // Tenta a direção oposta se a primeira escolha não estiver disponível
                else if (x - dir >= 0 && x - dir < gridWidth && grid[x - dir][y + 1] == null) {
                    grid[x - dir][y + 1] = this;
                    grid[x][y] = null;
                    moved = true;
                }
            }
            
            // Se a partícula não pôde se mover para baixo ou diagonalmente, permanece no lugar
            if (!moved) {
                grid[x][y] = this;
            }
        }
    }
}
