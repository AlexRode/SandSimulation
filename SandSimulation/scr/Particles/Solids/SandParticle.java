package scr.Particles.Solids;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import scr.Particles.Particle;
import scr.Particles.Liquids.Liquid;
import scr.Particles.Liquids.WaterParticle;
import scr.utils.Destructible;







public class SandParticle extends Solid implements Destructible{
    

    private static final Color[] SAND_COLORS = {
        new Color(194, 178, 128), // Bege
        new Color(237, 201, 175), // Creme
        new Color(213, 196, 161), // Castanho claro
        //new Color(255, 255, 204), // Amarelo claro
        new Color(255, 239, 153), // Amarelo dourado
        // Adicione mais cores aqui se desejar
    };
    public SandParticle() {
        super( SAND_COLORS[new Random().nextInt(SAND_COLORS.length)]); // Cor específica para areia
 
       lateralDispersion = 2;
        
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }
     @Override
    public void update(Particle[][] grid, int x, int y) {
        super.update(grid, x, y); // Chama a implementação padrão de Solid
        
        // Agora, verificamos se há água abaixo para interagir
        if (y < grid[0].length - 1 && grid[x][y + 1] instanceof WaterParticle) {
            // Troca posição com a água
            Particle temp = grid[x][y + 1];
            grid[x][y + 1] = this;
            grid[x][y] = temp;
        }
    }

    @Override
    public void performActionWithNeighbor(Particle[][] grid, int x, int y) {
        // Ação específica para SandParticle
        Particle below = getNeighbor(grid, x, y, 0, 1); // Vizinho abaixo

        if (below instanceof Liquid) {
            // Troca posição com a partícula líquida (areia afunda e líquido sobe)
            swapParticles(grid, x, y, x, y + 1);
        }
        
        // Após afundar, pode ser necessário verificar movimentos laterais ou outras ações
        // Por exemplo, se ainda estiver em contato com líquidos
    }

    // Método auxiliar para obter o vizinho em uma direção específica
    public Particle getNeighbor(Particle[][] grid, int x, int y, int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
            return grid[newX][newY];
        }
        return null; // Fora dos limites da grade
    }

    // Método auxiliar para trocar partículas
    private void swapParticles(Particle[][] grid, int x1, int y1, int x2, int y2) {
        Particle temp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = temp;
    }

}
