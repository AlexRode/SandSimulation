package scr.Particles.Liquids;
import java.awt.Color;
import java.util.Random;

import scr.Particles.Particle;



public abstract class Liquid extends Particle {
   
    public Liquid(Color color) {
        super(color);
        //TODO Auto-generated constructor stub
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
    
}
