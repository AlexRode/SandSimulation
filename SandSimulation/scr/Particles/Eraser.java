package scr.Particles;
import java.awt.Color;
import java.awt.Graphics;


public class Eraser extends Particle {

    public Eraser() {
        super( Color.GRAY);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        // Desenhe a partícula se necessário, ou deixe vazio se ela for "invisível"
        // Exemplo: desenhar um pequeno círculo cinza para representar a borracha
        g.setColor(Color.GRAY);
        g.fillOval(x, y, size, size);
    }

    @Override
    public void update(Particle[][] grid, int x, int y) {
        // A lógica de atualização pode ser vazia, já que esta partícula serve apenas para apagar
    }
    
}
