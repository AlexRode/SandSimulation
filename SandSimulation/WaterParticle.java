import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class WaterParticle extends Particle{
    int velocity = 1; // A velocidade inicial da partícula de água
    static final int MAX_VELOCITY = 3; // Velocidade máxima de queda
    private static final Color[] WATER_COLORS = {
        new Color(0, 191, 255, 128), // Azul claro com transparência
       // new Color(70, 130, 180, 128), // Azul aço com transparência
        new Color(135, 206, 235, 128), // Azul céu com transparência
        // Adicione mais cores aqui se desejar
    };


    private Random random = new Random();
    public WaterParticle() {
        super("WATER", WATER_COLORS[new Random().nextInt(WATER_COLORS.length)]);
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
        Random random = new Random();

        // Primeiro, tente mover-se para baixo, aumentando a velocidade
        boolean movedDown = false;
        for (int i = 1; i <= Math.min(velocity, MAX_VELOCITY); i++) {
            if (y + i < gridHeight && grid[x][y + i] == null) {
                grid[x][y + i] = this;
                grid[x][y] = null;
                y = y + i; // Atualiza a posição atual da partícula
                movedDown = true;
                break;
            }
        }
        if (movedDown) {
            velocity = Math.min(velocity + 1, MAX_VELOCITY);
            return;
        } else {
            velocity = 1; // Reseta a velocidade se não se moveu para baixo
        }

        // Tente mover-se lateralmente ou diagonalmente com escolha aleatória da direção
        int[] directions = random.nextBoolean() ? new int[]{1, -1} : new int[]{-1, 1};
        for (int dir : directions) {
            // Movimento lateral
            if (x + dir >= 0 && x + dir < gridWidth && grid[x + dir][y] == null) {
                grid[x + dir][y] = this;
                grid[x][y] = null;
                return;
            }

            // Movimento diagonal
            if (y < gridHeight - 1 && x + dir >= 0 && x + dir < gridWidth && grid[x + dir][y + 1] == null) {
                grid[x + dir][y + 1] = this;
                grid[x][y] = null;
                return;
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

    

