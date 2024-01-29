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
    
        int maxFallDistance = 1; // Define a distância máxima que a partícula pode cair
        int fallDistance = 0;
    
        // Procura pela maior distância de queda possível dentro do limite
        for (int i = 1; i <= maxFallDistance; i++) {
            if (y + i < gridHeight && grid[x][y + i] == null) {
                fallDistance = i;
            } else {
                break; // Interrompe se encontrar uma célula ocupada
            }
        }
    
        if (fallDistance > 0) {
            // Mover a partícula para baixo se possível
            grid[x][y + fallDistance] = this;
            grid[x][y] = null;
        } else {
            // Se não puder cair, tente mover-se horizontalmente
            int dir = random.nextBoolean() ? 1 : -1;
            if (x + dir >= 0 && x + dir < gridWidth && grid[x + dir][y] == null) {
                grid[x + dir][y] = this;
                grid[x][y] = null;
            }
        }
    }
    
     /*@Override
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
    }*/

}

    // Métodos para lidar com calor e explosão são omitidos para simplificar

    

