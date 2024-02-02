package scr.Particles.Seeds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import scr.Particles.Particle;
import scr.Particles.Solids.Solid;

public class Plant extends Solid {
    // Atributos específicos da planta, se houver
    private static final Color[] PLANT_COLORS = {
        new Color(34, 139, 34), // Verde escuro (Forest Green)
        new Color(50, 205, 50), // Verde Limão (Lime Green)
        new Color(60, 179, 113), // Verde Mar Médio (Medium Sea Green)
        new Color(32, 178, 170), // Verde Água Claro (Light Sea Green)
        new Color(154, 205, 50)  // Verde Amarelo (Yellow Green)
    };
    public Plant() {
        super(PLANT_COLORS[new Random().nextInt(PLANT_COLORS.length)]);
        // Inicialização específica da planta
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(this.color);
        g.fillRect(x, y, size, size); // Desenha a parte da planta
    }
    @Override
    public  void update(Particle[][] grid, int x, int y){
    // Fora dos limit
    }
    // Você pode adicionar mais métodos específicos para a planta aqui
}



