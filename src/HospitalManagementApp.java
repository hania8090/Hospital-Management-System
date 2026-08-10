import gui.MainFrame;
import java.awt.GraphicsEnvironment;
import javax.swing.SwingUtilities;

public class HospitalManagementApp {
    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("This application requires a graphical desktop environment.");
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}