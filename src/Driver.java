import javax.swing.*;
import java.sql.SQLException;

/**
 * Main class
 * 
 * @author Bret Craig
 * @version 1.0
 */
public class Driver {

    private static LoginWindow loginWindow;
    private static TutorWindow tutorWindow;

    /**
     * main method
     * 
     * @param args String[]
     */
    public static void main(String[] args) {
        loginWindow = new LoginWindow();
        UserAccountDB db = new UserAccountDB();
        Blackboard.getInstance().setDb(db);
    }

    public static void login() {
        loginWindow.setVisible(false);
        tutorWindow = new TutorWindow();
    }
}
