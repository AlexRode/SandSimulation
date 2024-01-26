import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SandSimulation ss = new SandSimulation();
            ss.setVisible(true);
        });
    }
}
