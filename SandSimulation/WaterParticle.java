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
    
            // Se a partícula não pôde se mover para baixo ou diagonalmente, tenta a disseminação
            if (!moved) {
                if (liquidSpread(grid, x, y)) {
                    grid[x][y] = null;
                } else {
                    grid[x][y] = this;
                }
            }
        }
    }
    public boolean liquidSpread(Particle[][] grid, int x, int y) {
        int gridWidth = grid.length;
        int gridHeight = grid[0].length;
        Random random = new Random();
    
        // Gere um número aleatório entre -2 e 2 (inclusive)
        //int direction = random.nextInt(5) - 2;
        int direction = random.nextInt(11) - 5;
        // Verifique se a nova posição está dentro dos limites da grade
        int newX = x + direction;
        if (newX >= 0 && newX < gridWidth) {
            // Verifique se a célula na nova posição está vazia (null)
            if (grid[newX][y] == null) {
                // Mova a partícula líquida para a nova posição
                grid[newX][y] = this;
                grid[x][y] = null;
                return true;
            }
        }
        return false;
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

    

