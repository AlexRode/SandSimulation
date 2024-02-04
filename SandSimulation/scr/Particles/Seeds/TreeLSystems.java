package scr.Particles.Seeds;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class TreeLSystems extends JPanel {

    private String treeString;
    private int startX, startY;
    private double startAngle;
    private int stepLength;

    public TreeLSystems(String treeString, int startX, int startY, double startAngle, int stepLength) {
        this.treeString = treeString;
        this.startX = startX;
        this.startY = startY;
        this.startAngle = startAngle;
        this.stepLength = stepLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, treeString, startX, startY, startAngle, stepLength);
    }

    private void draw(Graphics g, String treeString, int startX, int startY, double startAngle, int stepLength) {
        Stack<State> stateStack = new Stack<>();
        int x = startX;
        int y = startY;
        double angle = startAngle;

        g.setColor(Color.GREEN);

        for (char c : treeString.toCharArray()) {
            switch (c) {
                case 'F':
                    int newX = x + (int) (Math.cos(Math.toRadians(angle)) * stepLength);
                    int newY = y - (int) (Math.sin(Math.toRadians(angle)) * stepLength); // Nota: O eixo Y é invertido no Swing
                    g.drawLine(x, y, newX, newY);
                    x = newX;
                    y = newY;
                    break;
                case '+':
                    angle -= 25;
                    break;
                case '-':
                    angle += 25;
                    break;
                case '[':
                    stateStack.push(new State(x, y, angle));
                    break;
                case ']':
                    State state = stateStack.pop();
                    x = state.x;
                    y = state.y;
                    angle = state.angle;
                    break;
            }
        }
    }

    // Classe State para armazenar o estado atual da caneta
    private class State {
        int x, y;
        double angle;

        public State(int x, int y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }

    public static void main(String[] args) {
        // Criar a string L-System ou gerá-la usando um método específico
        String treeString = "FF+[+F-F-F]-[-F+F+F]";

        // Cria o painel com a árvore fractal
        TreeLSystems fractalTreePanel = new TreeLSystems(treeString, 300, 400, -90, 10);

        // Criar o frame para exibir o painel
        JFrame frame = new JFrame("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(fractalTreePanel);
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}