
    import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SeedBamboo extends Particle {
    private boolean isStable;
    private int stableTime; // Tempo desde que a semente estabilizou
    private int growthStage;
    private static final int STABLE_TIME_REQUIRED = 2; // 2 segundos para começar a crescer
    private static final int MAX_GROWTH_STAGE = 10;  
    private static final Color[] SEED_COLOR = {
        new Color(144, 238, 144, 128), // Luz Verde
        new Color(60, 179, 113, 128),  // Verde Primavera
        new Color(34, 139, 34, 128),   // Verde Floresta
        new Color(154, 205, 50, 128),  // Verde Amarelado
        new Color(107, 142, 35, 128)   // Oliva Escura
    }; 
    private static final Color BAMBOO_COLOR = new Color(34, 139, 34); // Cor do bambu
    // Máximo estágio de crescimento

    public SeedBamboo() {
        super(SEED_COLOR[new Random().nextInt(SEED_COLOR.length)]);
        this.isStable = false;
        this.growthStage = 0;
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        int segmentHeight = size; // Altura de cada segmento do bambu

        for (int i = 0; i < growthStage; i++) {
            // Desenha cada segmento do bambu
            g.setColor(BAMBOO_COLOR); // Cor do bambu
            g.fillRect(x, y - i * segmentHeight, size, segmentHeight);

            // Desenha as linhas dos segmentos
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y - i * segmentHeight, x + size, y - i * segmentHeight);

            // Adiciona folhas em intervalos específicos
            if (i % 3 == 0) {
                drawLeaves(g, x, y - i * segmentHeight, size);
            }
        }
    }

    private void drawLeaves(Graphics g, int x, int y, int size) {
        g.setColor(Color.GREEN);
        // Desenha algumas folhas simples. Ajuste conforme necessário para o seu estilo
        g.fillOval(x - size / 2, y, size / 2, size / 4);
        g.fillOval(x + size, y, size / 2, size / 4);
    }

    // ... outros métodos, como update ...


    @Override
    public void update(Particle[][] grid, int x, int y) {
        int gridWidth = grid.length;
        int gridHeight = grid[0].length;

        // Verifica se a semente ainda está se movendo
        if (!isStable) {
            if (y + 1 < gridHeight && grid[x][y + 1] == null) {
                grid[x][y + 1] = this;
                grid[x][y] = null;
            } else {
                isStable = true;
            }
        }

        // Conta o tempo de estabilização
        if (isStable) {
            stableTime++;
            if (stableTime >= STABLE_TIME_REQUIRED && growthStage < MAX_GROWTH_STAGE) {
                growthStage++;
                // O crescimento será tratado no método draw()
            }
        }
    }
}

