import javax.swing.*;

/**
 * Main class
 * @author Bret Craig
 * @version 1.0
 */
public class Driver {

    private static TutorWindow win;

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        win = new TutorWindow();
        win.setSize(500, 500);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
