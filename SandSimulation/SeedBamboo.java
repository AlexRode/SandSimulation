
/*import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SeedBamboo extends Particle implements Destructible{
    private long startTime;
    private boolean stabilized;
    private static final long GROWTH_DELAY = 2000; // 2000 milissegundos = 2 segundos
    private static final int MAX_HEIGHT = 10; // Altura máxima do bambu
    private int currentHeight = 0;

    public SeedBamboo() {
        super(Color.GREEN); // Cor inicial da semente
        this.stabilized = false;
    }

    @Override
    public void update(Particle[][] grid, int x, int y) {
        if (!stabilized) {
            checkStabilization(grid, x, y);
        } else {
            growBamboo(grid, x, y);
        }
    }

    private void checkStabilization(Particle[][] grid, int x, int y) {
        int gridHeight = grid[0].length;
    
        // Verifica se a semente está na parte inferior do grid ou se tem uma partícula abaixo dela que representa o solo
        if (y == gridHeight - 1 || (grid[x][y + 1] instanceof Destructible)) {//crira soil por enquanto esta destructable
            stabilized = true;
            startTime = System.currentTimeMillis();
        }
    }

    private void growBamboo(Particle[][] grid, int x, int y) {
        if (System.currentTimeMillis() - startTime >= GROWTH_DELAY && currentHeight < MAX_HEIGHT) {
            if (y - currentHeight - 1 >= 0 && grid[x][y - currentHeight - 1] == null) {
                grid[x][y - currentHeight - 1] = new Bamboo();
                currentHeight++;
            }
        }
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}*/


