import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class SmokeParticle extends Particle {
    protected double lateralDispersion = 2;
    private int lifetime; // Tempo de vida da partícula em atualizações
    private static final int MAX_LIFETIME = 100; 
    private static final Color[] SMOKE_COLORS = {
        new Color(192, 192, 192, 128), // Cinza claro com transparência
        new Color(169, 169, 169, 128), // Cinza escuro com transparência
        // Adicione mais cores aqui se desejar
    };
    public SmokeParticle() {
        super(  SMOKE_COLORS[new Random().nextInt(SMOKE_COLORS.length)]);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillOval(x, y, size, size); // Desenha um círculo com a cor da partícula
    }

   @Override
public void update(Particle[][] grid, int x, int y) {
    int gridWidth = grid.length;
    int gridHeight = grid[0].length;
    Random random = new Random();

    // Verifica se não está na parte inferior do grid
    if (y >0) {
        // Calcula a amplitude máxima do movimento lateral com base em lateralDispersion
        int lateralRange = (int) Math.round(lateralDispersion * 2);

        // Lista para armazenar todas as direções possíveis (incluindo para baixo)
        List<Integer> directions = new ArrayList<>();
        directions.add(0); // Sempre inclui movimento para baixo

        // Adiciona direções laterais baseadas na amplitude calculada
        for (int i = 1; i <= lateralRange; i++) {
            directions.add(i);  // Movimento para a direita
            directions.add(-i); // Movimento para a esquerda
        }

        // Embaralha as direções para escolher aleatoriamente
        Collections.shuffle(directions);

        for (int dir : directions) {
            int newX = x + dir;

            // Verifica se a célula está dentro do grid e se está disponível
            if (newX >= 0 && newX < gridWidth && grid[newX][y - 1] == null) {
                grid[newX][y - 1] = this;
                grid[x][y] = null;
                return; // Sai da função após mover a partícula
            }
        }
    }
    lifetime--;

    // Remove a partícula quando o tempo de vida chegar a 0
    if (lifetime <= 0) {
        grid[x][y] = null; // Remove a partícula do grid
    }
}
    
}
