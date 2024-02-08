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
        Random random = new Random();
    
        // Gera um número aleatório entre -5 e 5 (inclusive)
        int direction = random.nextInt(11) - 5;
        int newX = x + direction;
    
        // Ajusta newX para permanecer dentro dos limites da grade
        newX = Math.max(0, Math.min(newX, gridWidth - 1));
    
        // Determina o passo (1 para direita ou -1 para esquerda) baseado na direção
        int step = (newX > x) ? 1 : -1;
    
        // Verifica se há partícula no caminho antes de mover
        boolean pathBlocked = false;
        for (int i = x + step; i != newX + step; i += step) {
            if (grid[i][y] != null) {
                // Se encontrar uma partícula no caminho, define o caminho como bloqueado
                pathBlocked = true;
                break;
            }
        }
    
        if (!pathBlocked) {
            // Se o caminho não estiver bloqueado, realiza o movimento
            if (grid[newX][y] == null) {
                grid[newX][y] = this;
                grid[x][y] = null;
                return true;
            }
        }
    
        return false; // Não foi possível mover a partícula
    }
    
}
