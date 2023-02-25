import javax.swing.*;

/**
 * Main class
 * @author Bret Craig
 * @version 1.0
 */
public class Driver {

    private static LoginWindow loginWindow;
    private static TutorWindow tutorWindow;


    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
//        loginWindow = new LoginWindow();
//        loginWindow.setSize(500, 500);
//        loginWindow.setVisible(true);
//        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        tutorWindow = new TutorWindow();
        tutorWindow.setSize(500, 500);
        tutorWindow.setVisible(true);
        tutorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
