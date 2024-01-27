import java.awt.Graphics;
import java.lang.reflect.Modifier;

import javafx.scene.paint.Color;

class SandParticle extends Particle{
    public int x, y;     // Posição da partícula
    public float velocity; // Velocidade da partícula
    private Color color = Color.YELLOW; 
    protected ParticleType type;
    private SandSimulation simulation;


public SandParticle(SandSimulation simulation,int x, int y) {
    
        super(x, y, ParticleType.SAND);
        // Inicializar outras propriedades específicas de SandParticle
        this.x = x;
        this.y = y;
        this.velocity = 0;
        this.type = ParticleType.SAND;
        this.color = Color.YELLOW;
        this.simulation = simulation;
    }

public int getX() {
    return x;
}

public int getY() {
    return y;
}

public float getVelocity() {
    return velocity;
}

public void setX(int x) {
    this.x = x;
}

public void setY(int y) {
    this.y = y;
}

public void setVelocity(float d) {
    this.velocity = d;
}

public Color getColor() {
    return color;
}
@Override
public void update() {
    int newX = x;
    int newY = y + 1; // Tenta mover para baixo

    if (simulation.isSpaceFree(newX, newY)) {
        moveTo(newX, newY);
    } else {
        boolean moveLeft = Math.random() < 0.5;
        if (moveLeft && simulation.isSpaceFree(x - 1, y + 1)) {
            moveTo(x - 1, y + 1);
        } else if (!moveLeft && simulation.isSpaceFree(x + 1, y + 1)) {
            moveTo(x + 1, y + 1);
        }
        // Se não puder mover, a partícula fica no mesmo lugar
    }
}





private boolean canMoveTo(int newX, int newY) {
    // Implemente a lógica para verificar se a partícula pode se mover para a nova posição.
    // Isso normalmente envolve verificar se a nova posição está dentro dos limites
    // e se não está ocupada por outra partícula.
    return simulation.isSpaceFree(newX, newY);
}

private void moveTo(int newX, int newY) {
    simulation.clearSand(x, y);
    simulation.fillGridSquareWithSand(newX, newY);
    // Atualiza a posição da partícula.
    x = newX;
    y = newY;
}

@Override
public void draw(Graphics g) {
    g.setColor(java.awt.Color.YELLOW);
    g.fillRect(x * SandSimulation.gridSize, y * SandSimulation.gridSize, 
               SandSimulation.gridSize, SandSimulation.gridSize);
}

}

