import javafx.scene.paint.Color;

class SandParticle {
    public int x, y;     // Posição da partícula
    public float velocity; // Velocidade da partícula
    public Color color ; 

    public SandParticle(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocity = 0;
        this.color = Color.YELLOW;
    }


 
/*
// Em algum lugar do seu código, você deve inicializar um array de SandParticle.
SandParticle[][] particles = new SandParticle[width/gridSize][height/gridSize];

private void updateSand() {
    for (int y = height/gridSize - 2; y >= 0; y--) {
        for (int x = 0; x < width/gridSize; x++) {
            SandParticle particle = particles[x][y];
            if (particle != null) {
                // Aumentar a velocidade devido à gravidade
                particle.velocity += 0.1; // Aceleração gravitacional

                // Calcula a nova posição baseada na velocidade
                int newY = (int) (y + particle.velocity);
                if (newY >= height/gridSize) {
                    newY = height/gridSize - 1;
                }

                // Verifica se o espaço está livre
                if (isClear(particle.x, newY)) {
                    moveSand(particle.x, particle.y, particle.x, newY);
                    particles[particle.x][newY] = particle;
                    particles[particle.x][particle.y] = null;
                    particle.y = newY;
                } else {
                    particle.velocity = 0; // Reset velocidade se colidir
                }
            }
        }
    }
}
*/
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
}

