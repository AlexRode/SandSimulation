package scr;

import javax.swing.*;
import javax.swing.border.Border;

import scr.Particles.Eraser;
import scr.Particles.Gas.SmokeParticle;
import scr.Particles.Liquids.WaterParticle;
import scr.Particles.Solids.SandParticle;
import scr.Particles.Solids.StoneParticle;

import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel {
    JLabel fpsLabel;

    public ControlPanel(Simulation simulation) {
        setLayout(new FlowLayout());

        // Adicionando botões estilizados ao painel
        add(createStyledButton("1-Areia", () -> simulation.setSelectedParticleType(new SandParticle()), simulation));
        add(createStyledButton("2-Água", () -> simulation.setSelectedParticleType(new WaterParticle()), simulation));
        add(createStyledButton("3-Pedra", () -> simulation.setSelectedParticleType(new StoneParticle()), simulation));
        add(createStyledButton("4-Fumo", () -> simulation.setSelectedParticleType(new SmokeParticle()), simulation));
        add(createStyledButton("0-Borracha", () -> simulation.setSelectedParticleType(new Eraser()), simulation));

        // Botão para limpar o grid
        JButton clearButton = createStyledButton("C-Clear", simulation::clearGrid, simulation);
        add(clearButton);

        // Rótulo para os FPS
        fpsLabel = new JLabel("FPS: 0.00");
        add(fpsLabel);
    }

    private JButton createStyledButton(String title, Runnable action, Simulation simulation) {
        JButton button = new JButton(title);
        button.setFocusable(false);
        button.setFont(new Font("Cascadia Code", Font.BOLD, 12));
        button.setBackground(new Color(100, 149, 237)); // Cor de fundo azul
        button.setForeground(Color.WHITE); // Texto em branco
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new RoundedBorder(10)); // Bordas arredondadas

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Cor mais escura ao passar o mouse
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237)); // Cor original
            }
        });

        button.addActionListener(e -> {
            action.run();
            simulation.requestFocusInWindow();
        });

        return button;
    }

    public void updateFpsLabel(double fps) {
        fpsLabel.setText("FPS: " + String.format("%.2f", fps));
    }

    // Classe para bordas arredondadas
    class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
