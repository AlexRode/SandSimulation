package scr.Particles.Liquids;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class WaterParticle extends Liquid{
    int velocity = 1; // A velocidade inicial da partícula de água
    static final int MAX_VELOCITY = 3; // Velocidade máxima de queda
    private static final Color[] WATER_COLORS = {
        new Color(50, 191, 255, 128), // Azul claro com transparência
       // new Color(70, 130, 180, 128), // Azul aço com transparência
        new Color(135, 206, 235, 128), // Azul céu com transparência
        // Adicione mais cores aqui se desejar
    };


    //private Random random = new Random();
    public WaterParticle() {
        super( WATER_COLORS[new Random().nextInt(WATER_COLORS.length)]);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }
   
    
    
    
}

    // Métodos para lidar com calor e explosão são omitidos para simplificar

    

