import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SandSimulation extends JFrame {

    private JSlider radiusSlider;
    private int sandRadius = 10;
    private final int sliderHeight = 50;
    public static final int width = 800; // Largura do canvas como uma constante estática
    public static final int height = 600; // Altura do canvas como uma constante estática
    public static final int gridSize = 10;
   
    public static int getGridsize() {
        return gridSize;
    }
    private BufferedImage canvas;
    private DrawCanvas drawCanvas;
    private boolean mousePressed = false;
    private Timer sandTimer;
    

    public Particle[][] grid;

    private ArrayList<SandParticle> sandParticles;

    public SandSimulation() {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        drawCanvas = new DrawCanvas( this,canvas);
        initUI();
        initSlider();
        sandTimer = new Timer(10, e -> addSandContinuously());
        sandParticles = new ArrayList<SandParticle>();
         Timer timer = new Timer((int) 0.75, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSand();
                drawCanvas.repaint();
            }
        });
        timer.start();
    }
    
        private void updateSand() {
            // Crie uma cópia da lista atual para evitar a modificação simultânea.
            ArrayList<SandParticle> particlesCopy = new ArrayList<>(sandParticles);
            for (SandParticle particle : particlesCopy) {
                particle.update();
            }
            repaint(); 
        }
     /*private void updateSand() {
        // Varre a imagem de baixo para cima, começando pelo segundo quadrado do fundo até o topo.
        for (int y = height - gridSize * 2; y > 0; y -= gridSize) {
            for (int x = 0; x <= width - gridSize; x += gridSize) {
                if (isSand(x, y)) {
                    boolean moved = false;
    
                    // Verifica se o quadrado abaixo está vazio.
                    if (isClear(x, y + gridSize)) {
                        moveSand(x, y, x, y + gridSize);
                        moved = true;
                    }
    
                    // Se não puder mover para baixo, verifica os lados.
                    if (!moved) {
                        // Escolhe aleatoriamente para qual lado a areia pode cair
                        boolean moveLeft = Math.random() < 0.5;
                        if (moveLeft && x - gridSize >= 0 && isClear(x - gridSize, y + gridSize)) {
                            moveSand(x, y, x - gridSize, y + gridSize);
                        } else if (!moveLeft && x + gridSize <= width - gridSize && isClear(x + gridSize, y + gridSize)) {
                            moveSand(x, y, x + gridSize, y + gridSize);
                        }
                    }
                }
            }
        }
    }
 */
     /*public void moveSand(int fromX, int fromY, int toX, int toY) {
        // Limpa o quadrado atual de areia
        //clearSand(fromX, fromY);
    
        // Adiciona areia no novo quadrado
        fillGridSquareWithSand(toX, toY);
    }
    */
    private void addSandContinuously() {
    Point mousePos = MouseInfo.getPointerInfo().getLocation();
    SwingUtilities.convertPointFromScreen(mousePos, drawCanvas);
    if (drawCanvas.contains(mousePos)) {
        addSand(mousePos.x, mousePos.y);
       
    }
}
    
  ////////////////sem grid////////////////
  
  /*  private void updateSand() {
        for (int y = height - 2; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (isSand(x, y) && !isSand(x, y + 1)) {
                    moveSand(x, y);
                }
            }
        }
    }
*/
private void initUI() {
    drawCanvas = new DrawCanvas(this,canvas);
    add(drawCanvas, BorderLayout.CENTER);

    // Configura a janela
    setTitle("Simulação de Areia");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Ajusta o tamanho da janela para corresponder exatamente ao tamanho do canvas
    // Inclui as dimensões das bordas e da barra de título
    setPreferredSize(new Dimension(width, height + sliderHeight*2));
    //pack();
    Insets insets = getInsets();
    int frameWidth = width + insets.left + insets.right;
    int frameHeight = height + insets.top + insets.bottom;
    int totalheight = frameHeight + sliderHeight;
    setSize(frameWidth, totalheight);

    setResizable(true); // Impede o redimensionamento da janela
    setLocationRelativeTo(null);


   drawCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
                addSand(e.getX(), e.getY());
                startSandTimer(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
                sandTimer.stop();
            }
        });

        drawCanvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mousePressed) {
                    addSand(e.getX(), e.getY());
                }
            }
        });
}
private void initSlider() {
    // Cria o slider
    radiusSlider = new JSlider(JSlider.HORIZONTAL, 1, sliderHeight, 10);
    radiusSlider.setMajorTickSpacing(10);
    radiusSlider.setMinorTickSpacing(1);
    radiusSlider.setPaintTicks(true);
    radiusSlider.setPaintLabels(true);

    // Adiciona um listener para atualizar o raio com base no valor do slider
    radiusSlider.addChangeListener(e -> {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            sandRadius = (int)source.getValue();
        }
    });

    // Adiciona o slider à janela
    add(radiusSlider, BorderLayout.SOUTH);
    pack(); // Redimensiona a janela para acomodar o slider
}

private void startSandTimer(MouseEvent e) {
    if (sandTimer == null) {
        sandTimer = new Timer(100, event -> {
            if (mousePressed) {
                addSand(e.getX(), e.getY());
            }
        });
    }
    sandTimer.start();
}
public void addSand(int x, int y) {
    // Converte coordenadas do mouse para coordenadas da grade
    int gridX = x / gridSize;
    int gridY = y / gridSize;

    // Cria e adiciona nova partícula de areia à lista
    SandParticle newParticle = new SandParticle(this, gridX, gridY);
    sandParticles.add(newParticle);

    // Marca o canvas para repintura
    drawCanvas.repaint();
}

/*public void addSand(int x, int y) {
    // Verifique se a posição do pixel está dentro dos limites do canvas
    if (x >= 0 && x < width && y >= 0 && y < height) {
        // Pinte o pixel no canvas
        canvas.setRGB(x, y, Color.YELLOW.getRGB());
        
        // Adicione a nova partícula à lista para rastreamento na simulação
        SandParticle particle = new SandParticle(this, x, y);
        sandParticles.add(particle);
        
        // Solicite a repintura do componente para mostrar a nova partícula
        drawCanvas.repaint();
    }
}*/

public boolean isSand(int x, int y) {
    // Verifica se todo o quadrado contém areia
    for (int i = x; i < x + gridSize; i++) {
        for (int j = y; j < y + gridSize; j++) {
            if (i < width && j < height && canvas.getRGB(i, j) != Color.YELLOW.getRGB()) {
                return false; // Encontrou um pixel que não é areia
            }
        }
    }
    return true;
}
///////////////////sem grid////
/*private boolean isSand(int x, int y) {
    return canvas.getRGB(x, y) == Color.YELLOW.getRGB();
}
*/



/*
 //////////////sem grid////////////////
private void moveSand(int x, int y) {
    canvas.setRGB(x, y, new Color(0, 0, 0, 0).getRGB()); // Remove a areia da posição atual
    canvas.setRGB(x, y + 1, Color.YELLOW.getRGB()); // Coloca a areia na posição abaixo
}
*/


public boolean isClear(int x, int y) {
    // Verifica se todo o quadrado está vazio
    for (int i = x; i < x + gridSize; i++) {
        for (int j = y; j < y + gridSize; j++) {
            if (i >= width || j >= height) {
                return false; // O pixel está fora do canvas, então o quadrado não está vazio
            }
            if (canvas.getRGB(i, j) == Color.YELLOW.getRGB()) {
                return false; // Encontrou areia, então o quadrado não está vazio
            }
        }
    }
    return true;
}
public void addParticle(SandParticle p) {
    sandParticles.add(p);
  }
  public ArrayList<SandParticle> getParticles() {
    return sandParticles;
  } 
  public boolean isSpaceFree(int x, int y) {
    for (int i = x; i < x + gridSize; i++) {
        for (int j = y; j < y + gridSize; j++) {
            if (i >= width || j >= height) {
                return false; // O pixel está fora do canvas, então o quadrado não está vazio
            }
        }
    }
    // Aqui você precisa verificar se a posição está ocupada por outra partícula
    // Esta verificação depende de como você armazena e gerencia as partículas
    // Por exemplo, se você está usando um BufferedImage, pode verificar a cor do pixel
    Color pixelColor = new Color(canvas.getRGB(x, y), true);
    return pixelColor.equals(new Color(0, 0, 0, 0)); // Considerando preto como cor "livre"
}
public void fillGridSquareWithSand(int x, int y) {
    // Preenche o quadrado no canvas
    for (int i = x * gridSize; i < (x + 1) * gridSize; i++) {
        for (int j = y * gridSize; j < (y + 1) * gridSize; j++) {
            canvas.setRGB(i, j, Color.YELLOW.getRGB());
        }
    }
    // Não precisa adicionar a partícula aqui, pois ela já está na lista
}
public void clearSand(int x, int y) {
    for (int i = x * gridSize; i < (x + 1) * gridSize; i++) {
        for (int j = y * gridSize; j < (y + 1) * gridSize; j++) {
            canvas.setRGB(i, j, new Color(0, 0, 0, 0).getRGB()); // Cor de fundo
        }
    }
}
}
