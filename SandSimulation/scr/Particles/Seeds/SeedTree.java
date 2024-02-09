package scr.Particles.Seeds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import scr.Particles.Particle;

import scr.Particles.Solids.Solid;

public class SeedTree extends Solid {

    private static Random random = new Random();

    // private static final int MAX_GROW_HEIGHT = 20; // Altura máxima de
    // crescimento

    private static final Color[] Seed_COLORS = {
            new Color(150, 75, 0), // Castanho claro

    };

    private String currentLSystemString;
    private final String axiom = "X";
    private final Map<Character, String> lSystemRules;

    public SeedTree() {
        super(Seed_COLORS[random.nextInt(Seed_COLORS.length)]);
        lSystemRules = new HashMap<>();
        lSystemRules.put('F', "FF");
        lSystemRules.put('X', "F-[[X]+X]+F[+FX]-X");

        // Gere a string L-System com base nas iterações desejadas
        currentLSystemString = generateLSystemString(5); // Número de iterações
    }

    private String generateLSystemString(int iterations) {
        String result = axiom;
        for (int i = 0; i < iterations; i++) {
            StringBuilder sb = new StringBuilder();
            for (char c : result.toCharArray()) {
                sb.append(lSystemRules.getOrDefault(c, String.valueOf(c)));
            }
            result = sb.toString();
        }
        return result;
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }

    @Override
    public void update(Particle[][] grid, int x, int y) {
        // Criação de uma pilha para armazenar estados (para ramificações)
        Stack<TurtleState> stateStack = new Stack<>();
        TurtleState currentState = new TurtleState(x, y, 0, -1); // Iniciar com crescimento para cima

        // Iterar sobre a string do L-System e interpretar os comandos
        for (char c : currentLSystemString.toCharArray()) {
            switch (c) {
                case 'F': // Move para frente na direção atual
                    int newX = currentState.x + currentState.dirX;
                    int newY = currentState.y + currentState.dirY;
                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                        if (grid[newX][newY] == null) {
                            grid[newX][newY] = new SeedTree(); // Novo ramo
                        }
                        currentState.x = newX;
                        currentState.y = newY;
                    }
                    break;
                case '+': // Gira para a direita
                    // Rotação simples por 90 graus
                    int temp = currentState.dirX;
                    currentState.dirX = -currentState.dirY;
                    currentState.dirY = temp;
                    break;
                case '-': // Gira para a esquerda
                    // Rotação simples por 90 graus
                    temp = currentState.dirX;
                    currentState.dirX = currentState.dirY;
                    currentState.dirY = -temp;
                    break;
                case '[': // Salva o estado atual (ramificação começa)
                    stateStack.push(
                            new TurtleState(currentState.x, currentState.y, currentState.dirX, currentState.dirY));
                    break;
                case ']': // Restaura o estado anterior (ramificação termina)
                    if (!stateStack.isEmpty()) {
                        currentState = stateStack.pop();
                    }
                    break;
            }
        }
    }

    /*
     * private void growBranches(Particle[][] grid, int x, int y) {
     * // Determina a altura máxima que os ramos podem crescer.
     * int branchHeight = MAX_GROW_HEIGHT / 2;
     * // Tenta crescer ramos à esquerda e à direita se houver espaço.
     * growBranch(grid, x, y, branchHeight, -1); // Cresce para a esquerda.
     * growBranch(grid, x, y, branchHeight, 1); // Cresce para a direita.
     * }
     */

    /*
     * private void growBranch(Particle[][] grid, int x, int y, int
     * branchGrowHeight, int direction) {
     * // Certifique-se de que 'grid' e 'random' foram inicializados.
     * if (grid == null || random == null) {
     * throw new IllegalStateException("Grid or random is not initialized.");
     * }
     * 
     * //int verticalDirection = random.nextBoolean() ? -1 : 1;
     * //int HorizontalDirection = random.nextBoolean() ? -1 : 1;
     * //int ransposition = random.nextBoolean() ? -2 : 2;
     * 
     * int randomNum = random.nextInt(10)-5 ;//faz com que por vezes o for não
     * funcione
     * int randomNum2 = random.nextInt(5)-5 ;
     * for (int i = 1; i <= randomNum; i++) {
     * int newX = x + i * direction ;
     * // int newY = y + i * verticalDirection;
     * int newY = y + i *randomNum2;
     * // Verifica se as novas coordenadas estão dentro dos limites do grid.
     * if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
     * // Verifica se o espaço está disponível e, se sim, coloca um novo ramo.
     * if (grid[newX][newY] == null) {
     * grid[newX][newY] = new Plant(); // Garanta que Plant() não retorna null.
     * }
     * }
     * }
     * }
     */

    class TurtleState {
        int x, y;
        int dirX, dirY; // Direção do crescimento

        TurtleState(int x, int y, int dirX, int dirY) {
            this.x = x;
            this.y = y;
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }

}