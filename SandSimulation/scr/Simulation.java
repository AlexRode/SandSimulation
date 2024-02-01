package scr;

import javax.swing.*;

import scr.Particles.Eraser;
import scr.Particles.Particle;
import scr.Particles.Gas.SmokeParticle;
import scr.Particles.Liquids.AcidParticle;
import scr.Particles.Liquids.WaterParticle;
import scr.Particles.Seeds.SeedTree;
import scr.Particles.Solids.SandParticle;
import scr.Particles.Solids.StoneParticle;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Simulation extends JFrame {

    private long lastTimeCheck = System.nanoTime();
    private int frameCount = 0;
    private double fps = 0;

    
    private final int width = 800;  // Largura do canvas
    private final int height = 600; // Altura do canvas
    public final int gridSize = 5; // Tamanho de cada célula do grid

    private Particle selectedParticleType;
    
    private Point mousePosition = null;
    private Timer addParticleTimer;
    private int addParticleTimerDelay = 100;
    private int timevelocity = 10; //60fps +/-
    private int addAreaSize = 2;

    //private JLabel radiusLabel;
    //private JLabel numElements;
    //private JLabel FPSLabel;

    private int addAreaRadius = 2; // Raio do círculo de adição
    
    private double fillPercentage = 0.15; // % de preenchimento
    
    private Random rand = new Random();

    private ControlPanel controlPanel;
    
    public Particle[][] grid = new Particle[width/gridSize][height/gridSize];


public Simulation() {
 
    
GridPanel gridPanel = new GridPanel();
gridPanel.setPreferredSize(new Dimension(width, height));
this.add(gridPanel, BorderLayout.CENTER); // Adiciona o gridPanel no centro

//radiusLabel = new JLabel("Raio (ctrl + ou -): " + addAreaRadius +"  Dispersão (shift + ou -): " + fillPercentage); 
//this.add(radiusLabel, BorderLayout.PAGE_END);

/*JPanel topPanel = new JPanel(new BorderLayout()); // Painel no topo que combina botões e rótulos

JPanel buttonPanel = new JPanel(new FlowLayout());

/*JButton sandButton = new JButton("Areia");
sandButton.addActionListener(e -> selectedParticleType = new SandParticle());

JButton waterButton = new JButton("Água");
waterButton.addActionListener(e -> selectedParticleType = new WaterParticle());
JButton stoneButton = new JButton("Pedra");
stoneButton.addActionListener(e -> selectedParticleType = new StoneParticle());
JButton smokeButton = new JButton("Fumo");
smokeButton.addActionListener(e -> selectedParticleType = new SmokeParticle());
JButton eraserButton = new JButton("Borracha");
eraserButton.addActionListener(e -> selectedParticleType = new Eraser());


//buttonPanel.add(sandButton);
buttonPanel.add(waterButton);
buttonPanel.add(stoneButton);
buttonPanel.add(smokeButton);
buttonPanel.add(eraserButton);


JButton clearButton = new JButton("Apagar Tudo");
clearButton.addActionListener(e -> clearGrid());
buttonPanel.add(clearButton);

topPanel.add(buttonPanel, BorderLayout.CENTER);
*/
//JPanel labelPanel = new JPanel(new FlowLayout());
//numElements = new JLabel("0-ERASER    1-SAND     2-WATER     3-STONE    4-SMOKE  ");
//FPSLabel = new JLabel("FPS: "+fps);

//labelPanel.add(numElements);
//labelPanel.add(FPSLabel);
//FPSLabel.setBounds(10, 10, 100, 20);
//topPanel.add(labelPanel, BorderLayout.PAGE_END);

//this.add(topPanel, BorderLayout.PAGE_START);
configureKeyBindings();
createControlPanel();
pack();
setLocationRelativeTo(null);
setVisible(true);

addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown()) {
            
            // Verificar se a tecla de mais está sendo pressionada
            if (e.getKeyCode() == KeyEvent.VK_PLUS) {               
                    addAreaRadius += 2; // Aumentar o raio   
            }
            // Verificar se a tecla de menos está sendo pressionada
            else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                addAreaRadius = Math.max(addAreaRadius - 2, 0); // Diminuir o raio
            }
        }else if(e.isShiftDown()){
            if (e.getKeyCode() == KeyEvent.VK_PLUS) {               
                fillPercentage += 0.05;            
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
            fillPercentage = Math.min(fillPercentage + 0.05, 1.0);
        }
        }
      //  upadteradiuslabel();
       
    }  
    
});
setFocusable(true); // Para que o JFrame possa capturar eventos de teclado
requestFocusInWindow(); // Solicita foco para capturar eventos de teclado
startSimulation();

//configureKeyBindings();

}
//private void upadteradiuslabel() {
    //radiusLabel.setText("Raio (ctrl + ou -): " + addAreaRadius +"  Dispersão (shift + ou -): " + fillPercentage);
    //radiusLabel.revalidate();
  //  radiusLabel.repaint();
//}

private void configureKeyBindings() {
  
        // Mapa das teclas para as partículas correspondentes
        Map<Integer, Particle> keyToParticleMap = new HashMap<>();
        keyToParticleMap.put(KeyEvent.VK_1, new SandParticle());
        keyToParticleMap.put(KeyEvent.VK_2, new WaterParticle());
        keyToParticleMap.put(KeyEvent.VK_3, new StoneParticle());
        keyToParticleMap.put(KeyEvent.VK_4, new SmokeParticle());
        keyToParticleMap.put(KeyEvent.VK_0, new Eraser());
        keyToParticleMap.put(KeyEvent.VK_A, new AcidParticle());
        keyToParticleMap.put(KeyEvent.VK_S, new SeedTree(5));


        // Configura KeyBindings para cada entrada no mapa
        keyToParticleMap.forEach((key, particle) -> 
            addKeyBinding(key, () -> selectedParticleType = particle));
    }
    private void addKeyBinding(int keyCode, Runnable action) {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), keyCode);
        actionMap.put(keyCode, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
               // upadteradiuslabel();
            }
        });
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
                    grid[x][y].performActionWithNeighbor(grid,x, y);
                }
            }
        }
        /*for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (newGrid[x][y] != null) {
                    // Chama performActionWithNeighbor para cada partícula não nula, passando o novo grid e a posição da partícula.
                    newGrid[x][y].performActionWithNeighbor(newGrid, x, y);
                }
            }
        }*/

        // Atualiza o grid principal para a nova configuração após o movimento das partículas
        grid = newGrid;
        repaint();
        // Re-desenha o grid com as novas posições das partículas
        GridPanel gridPanel = (GridPanel)getContentPane().getComponent(0);
        gridPanel.repaint();
        long currentTime = System.nanoTime();
        long timeElapsed = currentTime - lastTimeCheck;
    
        // Se mais de um segundo passou, atualize os FPS.
        if (timeElapsed >= 1e9) {
            fps = frameCount * (1e9 / timeElapsed);
    
            System.out.println("FPS Calculado: " + fps); // Imprime o valor calculado de FPS para teste
           
            if (controlPanel != null) {
                SwingUtilities.invokeLater(() -> controlPanel.updateFpsLabel(fps));
            }
            frameCount = 0;
            lastTimeCheck = currentTime;
           
        }
        frameCount++;
       
        //calculateFps();
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
                            if (selectedParticleType instanceof Eraser) {
                                grid[newX][newY] = null; // Apaga a partícula existente
                            }
                           
                            if (grid[newX][newY] == null && rand.nextDouble() < fillPercentage) {
                                
                                if (selectedParticleType instanceof Eraser) {
                                    grid[newX][newY] = null; // Apaga a partícula existente
                                }

                                else if (selectedParticleType instanceof SandParticle) {
                                    grid[newX][newY] = new SandParticle();
                                } else if (selectedParticleType instanceof WaterParticle) {
                                    grid[newX][newY] = new WaterParticle();
                                } else if (selectedParticleType instanceof StoneParticle) {
                                    grid[newX][newY] = new StoneParticle();
                                }  else if (selectedParticleType instanceof SmokeParticle) {
                                    grid[newX][newY] = new SmokeParticle();
                                }  else if (selectedParticleType instanceof AcidParticle) {
                                    grid[newX][newY] = new AcidParticle();
                                }  else if (selectedParticleType instanceof SeedTree) {
                                    grid[newX][newY] = new SeedTree(5);
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
    private double calculateFps() {
        long currentTime = System.nanoTime();
        long timeElapsed = currentTime - lastTimeCheck;
    
        if (timeElapsed >= 1e9) {
            fps = frameCount * (1e9 / timeElapsed);
            frameCount = 0;
            lastTimeCheck = currentTime;
        }
    
        frameCount++;
        return fps;
    }
    public void clearGrid() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = null;
            }
        }
        repaint();
    }
    public void setSelectedParticleType(Particle particle) {
        selectedParticleType = particle;
    }
    
    private void createControlPanel() {
        ControlPanel controlPanel = new ControlPanel(this);
        this.add(controlPanel, BorderLayout.PAGE_START);
    }
    

    public int getAddAreaRadius() {
        return addAreaRadius;
    }
    public double getFillPercentage() {
        return fillPercentage;
    }
    public double getFps() {
        return fps;
    }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(Simulation::new);
        }
}
