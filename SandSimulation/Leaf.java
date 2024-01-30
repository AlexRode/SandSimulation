import java.awt.Color;
import java.awt.Graphics;

class Leaf {
    private Color color; // Cor da folha
    private int width;   // Largura da folha
    private int height;  // Altura da folha

    public Leaf() {
        // Define os atributos da folha
        this.color = new Color(34, 139, 34); // Verde escuro, por exemplo
        this.width = 10;  // Largura da folha
        this.height = 20; // Altura da folha
    }

    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        // Desenha uma forma básica para a folha
        // Por exemplo, um oval ou uma forma personalizada
        g.fillOval(x - width / 2, y - size - height, width, height);
    }

}
