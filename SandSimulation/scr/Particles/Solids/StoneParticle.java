package scr.Particles.Solids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import scr.utils.Destructible;


public class StoneParticle extends Solid implements Destructible{

   
    private static final Color[] STONE_COLORS = {
        new Color(112, 128, 144), // Pedra ardósia
        new Color(169, 169, 169), // Cinza escuro
        new Color(105, 105, 105), // Cinza fosco
        // Adicione mais cores aqui se desejar
    };

  

    public StoneParticle() {
        super(STONE_COLORS[new Random().nextInt(STONE_COLORS.length)]);
        lateralDispersion = 0;
       
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }

   

    
}
