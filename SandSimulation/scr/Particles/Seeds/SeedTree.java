package scr.Particles.Seeds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import scr.Particles.Particle;
import scr.Particles.Solids.SandParticle;
import scr.Particles.Solids.Solid;

public class SeedTree extends Solid{
    private int branchDivisionLimit = 2; // Limite para quantas vezes os ramos podem se dividir
    private int currentDivisionLevel = 0; // Nível atual de divisão do ramo
   
    private static Random random = new Random(); 

    
    private int totalGrowHeight=10;

     private int currenttotalGrowHeight=0;
    private int state;
    private int growthCounter;
    private static final int MAX_GROW_HEIGHT = 20; // Altura máxima de crescimento
    private int currentGrowHeight = 0; 
        private static final Color[] Seed_COLORS = {
        new Color(150,75, 0), // Castanho claro
        
    };   

    public SeedTree() {
        super(Seed_COLORS[random.nextInt(Seed_COLORS.length)]);
       // this.state = 0;
        //this.growthCounter = 0;
        //this.totalGrowHeight = totalGrowHeight;
    }


    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(color);
        g.fillRect(x, y, size, size); // Preenche o quadrado com a cor da partícula
    }
    @Override
public void update(Particle[][] grid, int x, int y) {
    // Verifica se a árvore já completou seu crescimento total, incluindo ramos.
    if (this.state == 2) {
        return;
    }

    // Fase de crescimento do caule.
    if (this.state == 0 && this.currentGrowHeight < MAX_GROW_HEIGHT) {
        if (y > 0 && grid[x][y - 1] == null) {
            // Ao crescer, substitui a posição atual por uma parte do caule.
            grid[x][y] = new SeedTree(); // Utiliza Plant para representar o caule.
            // Move a "semente" (agora parte do caule) para cima.
            grid[x][y - 1] = this;
            this.currentGrowHeight++;
        } else {
           // for(int i = 0; i<1;i++){
            //grid[x][y] = new Plant();
            //grid[x+i][y - 1] = this;
            // Se encontrar um obstáculo ou atingir o topo, prepara para crescer os ramos.
            //}
            this.state = 1;

        }
    }

    // Fase de crescimento dos ramos, após o caule atingir a altura máxima.
    if (this.state == 1) {
        // Tentativa de crescer ramos à esquerda e à direita.
        growBranches(grid, x, y - this.currentGrowHeight + 1-10);
        this.state = 2; // Marca o crescimento como completo após tentar crescer os ramos.
    }
}

private void growBranches(Particle[][] grid, int x, int y) {
    // Determina a altura máxima que os ramos podem crescer.
    int branchHeight = MAX_GROW_HEIGHT / 2;
    // Tenta crescer ramos à esquerda e à direita se houver espaço.
    growBranch(grid, x, y, branchHeight, -1); // Cresce para a esquerda.
    growBranch(grid, x, y, branchHeight, 1);  // Cresce para a direita.
}

private void growBranch(Particle[][] grid, int x, int y, int branchGrowHeight, int direction) {
    // Certifique-se de que 'grid' e 'random' foram inicializados.
    if (grid == null || random == null) {
        throw new IllegalStateException("Grid or random is not initialized.");
    }

    int verticalDirection = random.nextBoolean() ? -1 : 1;
    int HorizontalDirection = random.nextBoolean() ? -1 : 1;
    int ransposition = random.nextBoolean() ? -2 : 2;

    int randomNum = random.nextInt(15)-5 ;//faz com que por vezes o for não funcione

    for (int i = 1; i <= randomNum; i++) {
        int newX = x + i * direction ;
       // int newY = y + i * verticalDirection;
       int newY = y + i *-1;
        // Verifica se as novas coordenadas estão dentro dos limites do grid.
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
            // Verifica se o espaço está disponível e, se sim, coloca um novo ramo.
            if (grid[newX][newY] == null) {
                grid[newX][newY] = new Plant(); // Garanta que Plant() não retorna null.
            }
        }
    }
}

   

    

    
}