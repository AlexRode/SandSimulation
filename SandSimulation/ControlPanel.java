
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel {
    JLabel fpsLabel;

    public ControlPanel(Simulation simulation) {
        setLayout(new FlowLayout());

        // Botão para selecionar Areia
        JButton sandButton = new JButton("Areia");
        sandButton.addActionListener(e -> simulation.setSelectedParticleType(new SandParticle()));
        add(sandButton);

        // Botão para selecionar Água
        JButton waterButton = new JButton("Água");
        waterButton.addActionListener(e -> simulation.setSelectedParticleType(new WaterParticle()));
        add(waterButton);

        // Botão para selecionar Pedra
        JButton stoneButton = new JButton("Pedra");
        stoneButton.addActionListener(e -> simulation.setSelectedParticleType(new StoneParticle()));
        add(stoneButton);

        // Botão para selecionar Fumo
        JButton smokeButton = new JButton("Fumo");
        smokeButton.addActionListener(e -> simulation.setSelectedParticleType(new SmokeParticle()));
        add(smokeButton);

        // Botão para selecionar Borracha
        JButton eraserButton = new JButton("Borracha");
        eraserButton.addActionListener(e -> simulation.setSelectedParticleType(new Eraser()));
        add(eraserButton);

        // Botão para apagar tudo
        JButton clearButton = new JButton("Apagar Tudo");
        clearButton.addActionListener(e -> simulation.clearGrid());
        add(clearButton);

        // Rótulo para exibir informações sobre os tipos de partículas
        JLabel particleInfoLabel = new JLabel("0-ERASER    1-SAND     2-WATER     3-STONE    4-SMOKE  ");
        add(particleInfoLabel);

        // Rótulo para exibir informações de FPS
        //JLabel fpsLabel = new JLabel("FPS: " + simulation.getFps());
        //add(fpsLabel);

        // Rótulo para exibir informações de raio e dispersão
      // JLabel radiusLabel = new JLabel("Raio: " + simulation.getAddAreaRadius() + " Dispersão: " + simulation.getFillPercentage());
        //add(radiusLabel);

        // Adicione mais botões e rótulos conforme necessário
    }
}

