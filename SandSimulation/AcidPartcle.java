import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class AcidParticle extends Liquid {
    private int lifetime = 1; // Tempo de vida do ácido antes de desaparecer
    private int destructionCapacity = 5; // Capacidade de destruir outras partículas
    private static final double SELF_DESTRUCTION_PROBABILITY = 0.1; // 10% de chance de autodestruição

    private static final Color[] ACID_COLORS = {
        new Color(50, 205, 50, 128), // Verde limão com transparência
        // Adicione mais cores aqui se desejar
    };

    public AcidParticle() {
        super(ACID_COLORS[new Random().nextInt(ACID_COLORS.length)]);
        this.lifetime = new Random().nextInt(50) + 50; // Vida entre 50 e 100 atualizações, por exemplo
    }

   

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        // Use a cor definida para o ácido
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
            if (canMoveOrDestroy(grid[x][y + 1])) {
                moveOrDestroy(grid, x, y, x, y + 1);
                moved = true;
            } else {
                // Decide aleatoriamente cair para a esquerda ou para a direita
                int dir = random.nextBoolean() ? 1 : -1;
    
                // Verifica se a célula diagonal na direção escolhida está disponível
                if (x + dir >= 0 && x + dir < gridWidth && canMoveOrDestroy(grid[x + dir][y + 1])) {
                    moveOrDestroy(grid, x, y, x + dir, y + 1);
                    moved = true;
                }
                // Tenta a direção oposta se a primeira escolha não estiver disponível
                else if (x - dir >= 0 && x - dir < gridWidth && canMoveOrDestroy(grid[x - dir][y + 1])) {
                    moveOrDestroy(grid, x, y, x - dir, y + 1);
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
    
    private boolean canMoveOrDestroy(Particle particle) {
        return particle == null || (particle instanceof Destructible && destructionCapacity > 0);
    }
    
    private void moveOrDestroy(Particle[][] grid, int oldX, int oldY, int newX, int newY) {
        if (grid[newX][newY] != null && grid[newX][newY] instanceof Destructible && destructionCapacity > 0) {
            // Destroi a partícula na posição nova
            destructionCapacity--;
        }
        // Move a partícula de ácido para a nova posição
        grid[newX][newY] = this;
        // Limpa a antiga posição
        grid[oldX][oldY] = null;
    }
}
