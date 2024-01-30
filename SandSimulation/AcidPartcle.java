import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class AcidParticle extends Liquid {
    private int lifetime; // Tempo de vida do ácido antes de desaparecer
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
        Random random = new Random();
        int gridWidth = grid.length;
        int gridHeight = grid[0].length;

        // Verifica se não está na parte inferior do grid
        if (y < gridHeight - 1) {
            // Tenta mover para baixo
            if (grid[x][y + 1] == null || destructionCapacity > 0) {
                moveOrDestroy(grid, x, y + 1, x, y);
            } else {
                // Se não puder mover para baixo, tenta se espalhar horizontalmente
                boolean moved = false;
                int[] dxOptions = {-1, 1}; // Tentativas de movimento para a esquerda e direita
                for (int dx : dxOptions) {
                    int newX = x + dx;
                    if (newX >= 0 && newX < gridWidth && (grid[newX][y] == null || destructionCapacity > 0)) {
                        moveOrDestroy(grid, newX, y, x, y);
                        moved = true;
                        break; // Sai do loop se conseguiu se mover
                    }
                }
                if (!moved) {
                    // Se não puder se espalhar horizontalmente, verifica se pode se espalhar para baixo e para os lados
                    for (int dx : dxOptions) {
                        int newX = x + dx;
                        if (newX >= 0 && newX < gridWidth && (grid[newX][y + 1] == null || destructionCapacity > 0)) {
                            moveOrDestroy(grid, newX, y + 1, x, y);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void moveOrDestroy(Particle[][] grid, int newX, int newY, int oldX, int oldY) {
        if (grid[newX][newY] != null && destructionCapacity > 0) {
            // Destroi a partícula na posição nova se houver e reduz a capacidade de destruição
            destructionCapacity--;
        }
        // Move a partícula de ácido para a nova posição
        grid[newX][newY] = this;
        // Limpa a antiga posição
        grid[oldX][oldY] = null;
    }
}
