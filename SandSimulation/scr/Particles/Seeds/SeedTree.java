package scr.Particles.Seeds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import scr.Particles.Particle;
import scr.Particles.Solids.SandParticle;
import scr.Particles.Solids.Solid;

public class SeedTree extends Solid{
    
    Random random;
    private int totalGrowHeight;
    
    private int state;
    private int growthCounter;
    private static final int MAX_GROW_HEIGHT = 5; // Altura máxima de crescimento
    private int currentGrowHeight = 0; 
        private static final Color[] Seed_COLORS = {
        new Color(230, 210, 200), // Castanho claro
        
    };   

    public SeedTree(int totalGrowHeight) {
        super(Seed_COLORS[new Random().nextInt(Seed_COLORS.length)]);
        // TODO Auto-generated constructor stub
        this.state = 0;
        this.growthCounter = 0;
        this.totalGrowHeight = totalGrowHeight;
    }


    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }
    @Override
    public void update(Particle[][] grid, int x, int y) {
        if (y >= grid[0].length - 1 || grid[x][y + 1] != null) {
            if (totalGrowHeight < MAX_GROW_HEIGHT) {
                updateGrowing(grid, x, y);
            }
        } else {
            updateFalling(grid, x, y);
        }
    }

    private void updateFalling(Particle[][] grid, int x, int y) {
        // Lógica de queda
        if (y < grid[0].length - 1 && grid[x][y + 1] == null) {
            grid[x][y] = null;
            grid[x][y + 1] = this;
        } else {
            // Mudar para o estado de crescimento se atingir o chão
            state = 1;
        }
    }

    private void updateGrowing(Particle[][] grid, int x, int y) {
        // Verifica se a semente já atingiu a altura máxima de crescimento
        if (currentGrowHeight >= MAX_GROW_HEIGHT) {
            return; // Para de crescer
        }

        // Tenta crescer para cima
        int newY = y - 1; // Nova posição Y para crescimento para cima
        if (newY >= 0 && grid[x][newY] == null) {
            // Coloca uma nova semente na posição acima
            grid[x][newY] = new SeedTree(totalGrowHeight);
            currentGrowHeight++; // Incrementa a altura de crescimento
        }
    }



    
}