import java.sql.SQLException;

/**
 * Main class
 * 
 * @author Bret Craig
 * @version 1.4
 */
public class Driver {

    private static LoginWindow loginWindow;


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

    /**
     * Open tutor window
     * @param user
     */
    public static void login(String user) {
        loginWindow.setVisible(false);
        TutorWindow tutorWindow = new TutorWindow(user);
    }

    /**
     * Open analytics window
     * @throws SQLException
     */
    public static void analytics() throws SQLException {
        loginWindow.setVisible(false);
        AnalyticsWindow analyticsWindow = new AnalyticsWindow();
    }
}
