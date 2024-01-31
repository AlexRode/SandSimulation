package scr.Particles.Solids;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

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
   
    

}
