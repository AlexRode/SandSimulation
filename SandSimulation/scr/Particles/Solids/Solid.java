package scr.Particles.Solids;

import java.awt.Color;

import java.util.Random;

import scr.Particles.Particle;

public abstract class Solid extends Particle {
    //protected double lateralDispersion ; // Dispersão lateral
    public Solid(Color color) {
        super( color);
        
        //TODO Auto-generated constructor stub
    }
     @Override
public void update(Particle[][] grid, int x, int y) {
    int gridWidth = grid.length;
    int gridHeight = grid[0].length;
    Random random = new Random();

    
    
    

    // Verifica se não está na parte inferior do grid
    if (y < gridHeight - 1) {
        // Primeiro, tenta mover para baixo
        if (grid[x][y + 1] == null) {
            grid[x][y + 1] = this;
            grid[x][y] = null;
            return;
            
        }
        

        // Se não puder mover para baixo, considera movimento lateral
        int lateralDirection = random.nextBoolean() ? 1 : -1;
        int maxLateralMovement = (int) Math.round(lateralDispersion * 2);
        for (int i = 1; i <= maxLateralMovement; i++) {
            int newX = x + lateralDirection * i;

            // Verifica se a célula está dentro do grid e se está disponível
            if (newX >= 0 && newX < gridWidth && grid[newX][y + 1] == null) {
                grid[newX][y + 1] = this;
                grid[x][y] = null;
                return;
            }
        }
    }
}
public void setLateralDispersion(double dispersion) {
    this.lateralDispersion = dispersion;
}
}
