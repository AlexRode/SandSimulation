import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class WaterParticle extends Particle{
    private Random random = new Random();
    public WaterParticle() {
        super("WATER", Color.BLUE);
        //TODO Auto-generated constructor stub
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

        // Tentar mover para baixo
        if (y + 1 < gridHeight && grid[x][y + 1] == null) {
            grid[x][y + 1] = this;
            grid[x][y] = null;
        }
        // Tentar mover horizontalmente ou diagonalmente
        else {
            moveHorizontallyOrDiagonally(grid, x, y);
        }
    }

    private void moveHorizontallyOrDiagonally(Particle[][] grid, int x, int y) {
        int gridWidth = grid.length;
        int gridHeight = grid[0].length;

        // Escolher aleatoriamente para mover esquerda ou direita primeiro
        boolean moveLeftFirst = random.nextBoolean();

        // Tentar mover horizontalmente
        if (moveInDirection(grid, x, y, moveLeftFirst ? -1 : 1, 0)) return;
        if (moveInDirection(grid, x, y, moveLeftFirst ? 1 : -1, 0)) return;

        // Tentar mover diagonalmente
        if (moveInDirection(grid, x, y, moveLeftFirst ? -1 : 1, 1)) return;
        if (moveInDirection(grid, x, y, moveLeftFirst ? 1 : -1, 1)) return;
    }

    private boolean moveInDirection(Particle[][] grid, int x, int y, int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;
        if (newX >= 0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] == null) {
            grid[newX][newY] = this;
            grid[x][y] = null;
            return true; // Movimento foi bem-sucedido
        }
        return false; // Movimento falhou
    }
}

    // Métodos para lidar com calor e explosão são omitidos para simplificar

    

