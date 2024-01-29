import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class SandParticle extends Particle {
    private static final Color[] SAND_COLORS = {
        new Color(194, 178, 128), // Bege
        new Color(237, 201, 175), // Creme
        new Color(213, 196, 161), // Castanho claro
        //new Color(255, 255, 204), // Amarelo claro
        new Color(255, 239, 153), // Amarelo dourado
        // Adicione mais cores aqui se desejar
    };
    public SandParticle() {
        super("SAND", SAND_COLORS[new Random().nextInt(SAND_COLORS.length)]); // Cor específica para areia
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
        Random random = new Random();
    
        // Verifica se não está na parte inferior do grid
        if (y < gridHeight - 1) {
            int[] directions = {0, random.nextBoolean() ? 1 : -1, -1 * (random.nextBoolean() ? 1 : -1)};
    
            for (int dir : directions) {
                int newX = x + dir;
    
                // Verifica se a célula está dentro do grid e se está disponível
                if (newX >= 0 && newX < gridWidth && grid[newX][y + 1] == null) {
                    grid[newX][y + 1] = this;
                    grid[x][y] = null;
                    return; // Sai da função após mover a partícula
                }
            }
        }
    }



    /*@Override
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
    }*/
}
