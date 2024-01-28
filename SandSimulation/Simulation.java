import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Simulation extends JFrame {
    private final int width = 800;  // Largura do canvas
    private final int height = 600; // Altura do canvas
    public final int gridSize = 5; // Tamanho de cada célula do grid

    private Particle selectedParticleType;
    
    private Point mousePosition = null;
    private Timer addParticleTimer;
    private int addParticleTimerDelay = 100;
    private int timevelocity = 25;
    private int addAreaSize = 2;

    private JSlider radiusSlider;
    private JSlider fillSlider;
    private JLabel radiusLabel;
    private JLabel Numelements;

    private int addAreaRadius = 7; // Raio do círculo de adição
    private double fillPercentage = 0.15; // % de preenchimento
    private Random rand = new Random();

    
    
    public Particle[][] grid = new Particle[width/gridSize][height/gridSize];


public Simulation() {
    //setLayout(new BorderLayout()); // Mudança para BorderLayou 
/* 
    JPanel sliderPanel = new JPanel(new FlowLayout()); // Painel para os sliders
    radiusSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, addAreaRadius);
    radiusSlider.addChangeListener(e -> {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            addAreaRadius = source.getValue();
            System.out.println("Raio atualizado para: " + addAreaRadius); // Saída de log para depuração
        }
    });
    // Configurações do radiusSlider...
    fillSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(fillPercentage * 100));
    // Configurações do fillSlider...
    fillSlider.addChangeListener(e -> {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            fillPercentage = source.getValue() / 100.0;
            System.out.println("Preenchimento atualizado para: " + fillPercentage); // Saída de log para depuração
        
        }
        
    });

    // Adiciona os sliders ao painel de sliders
    sliderPanel.add(new JLabel("Raio:"));
    sliderPanel.add(radiusSlider);
    sliderPanel.add(new JLabel("Preenchimento:"));
    sliderPanel.add(fillSlider);

    // Adiciona o painel de sliders ao JFrame na parte superior
    this.add(sliderPanel, BorderLayout.PAGE_START);

*/

    GridPanel gridPanel = new GridPanel();
    gridPanel.setPreferredSize(new Dimension(width, height));
    this.add(gridPanel, BorderLayout.CENTER); // Adiciona o gridPanel no centro
    
    radiusLabel = new JLabel("Raio (ctrl + ou -): " + addAreaRadius +"  Dispersão (shift + ou -): " + fillPercentage); 
    this.add(radiusLabel, BorderLayout.PAGE_END);
    
    Numelements =  new JLabel("1-SAND  2-WATER   3-STONE"); 
    this.add(Numelements, BorderLayout.PAGE_START);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedControls(e);
                switch (e.getKeyCode()) {
                                       
                    case KeyEvent.VK_1:
                    selectedParticleType = new SandParticle(); // Seleciona SAND
                        break;
                    case KeyEvent.VK_2:
                    selectedParticleType = new WaterParticle(); // Seleciona WATER
                        break;
                    case KeyEvent.VK_3:
                    selectedParticleType = new StoneParticle(); // Seleciona WATER
                        break;
                    // Adicione mais casos se tiver mais tipos de partículas
                }
            }
        });
        setFocusable(true); // Para que o JFrame possa capturar eventos de teclado
        requestFocusInWindow(); // Solicita foco para capturar eventos de teclado
        startSimulation();
    }

    public void keyPressedControls(KeyEvent e) {
        
        // Verificar primeiro se o Control está pressionado
        if (e.isControlDown()) {
            
            // Verificar se a tecla de mais está sendo pressionada
            if (e.getKeyCode() == KeyEvent.VK_PLUS) {               
                    addAreaRadius += 2; // Aumentar o raio   
            }
            // Verificar se a tecla de menos está sendo pressionada
            else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                addAreaRadius -= 2; // Diminuir o raio
            }
        }else if(e.isShiftDown()){
            if (e.getKeyCode() == KeyEvent.VK_PLUS) {               
                fillPercentage += 0.05;            
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                fillPercentage -= 0.05; 
        }
        }
        radiusLabel.setText("Raio (ctrl + ou -): " + addAreaRadius +"  Dispersão (shift + ou -): " + fillPercentage);
        radiusLabel.revalidate();
        radiusLabel.repaint();
    }
    public void startSimulation() {
        // Supondo que você tenha um timer para atualizar o estado das partículas
        Timer timer = new Timer(timevelocity, e -> updateParticles());
        timer.start();
    }

    private void updateParticles() {
        // Criando uma cópia do grid para não alterar o grid enquanto iteramos sobre ele
        Particle[][] newGrid = new Particle[width / gridSize][height / gridSize];
    
        // Copia todas as partículas para o novo grid
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                newGrid[x][y] = grid[x][y];
            }
        }
    
        // Itera sobre todas as células do grid
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] != null) {
                    // Chama update para cada partícula, passando o novo grid e a posição da partícula
                    grid[x][y].update(newGrid, x, y);
                }
            }
        }
    
        // Atualiza o grid principal para a nova configuração após o movimento das partículas
        grid = newGrid;
    
        // Re-desenha o grid com as novas posições das partículas
        GridPanel gridPanel = (GridPanel)getContentPane().getComponent(0);
        gridPanel.repaint();
    }





    class GridPanel extends JPanel {
        public GridPanel() {
            setPreferredSize(new Dimension(Simulation.this.width, Simulation.this.height));
    
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mousePosition = e.getPoint();
                    addParticleTimer.start();
                    addParticle(mousePosition.x, mousePosition.y);
                }
    
                @Override
                public void mouseReleased(MouseEvent e) {
                    addParticleTimer.stop();
                }
            });
    
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    mousePosition = e.getPoint();
                }
            });
    
            addParticleTimer = new Timer(addParticleTimerDelay, e -> {
                if (mousePosition != null) {
                    addParticle(mousePosition.x, mousePosition.y);
                }
            });
        }
        private void addParticle(int x, int y) {
            int gridX = x / gridSize;
            int gridY = y / gridSize;
        
            for (int dx = -addAreaRadius; dx <= addAreaRadius; dx++) {
                for (int dy = -addAreaRadius; dy <= addAreaRadius; dy++) {
                    int newX = gridX + dx;
                    int newY = gridY + dy;
        
                    // Verifica se a célula está dentro do círculo
                    if (Math.sqrt(dx * dx + dy * dy) <= addAreaRadius) {
                        // Verifica se as novas coordenadas estão dentro dos limites do grid
                        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                            // Decide aleatoriamente se a célula será preenchida, com base na porcentagem de preenchimento
                            if (grid[newX][newY] == null && rand.nextDouble() < fillPercentage) {
                                if (selectedParticleType instanceof SandParticle) {
                                    grid[newX][newY] = new SandParticle();
                                } else if (selectedParticleType instanceof WaterParticle) {
                                    grid[newX][newY] = new WaterParticle();
                                } else if (selectedParticleType instanceof StoneParticle) {
                                    grid[newX][newY] = new StoneParticle();
                                }
                                // Adicione outras condições para diferentes tipos de partículas
                            }
                        }
                    }
                }
            }
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] != null) {
                        grid[i][j].draw(g, i * gridSize, j * gridSize, gridSize);
                    }
                    // Desenha as bordas do grid se necessário
                  //  g.setColor(Color.BLACK);
                    //g.drawRect(i * gridSize, j * gridSize, gridSize, gridSize);
                }
            }
        }
        
    }
            
        
        
        public static void main(String[] args) {
            SwingUtilities.invokeLater(Simulation::new);
        }
}
