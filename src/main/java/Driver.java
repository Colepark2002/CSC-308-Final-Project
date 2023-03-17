package main.java;

/**
 * Main class
 * 
 * @author Bret Craig
 * @version 1.2
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

    public static void login(String user) {
        loginWindow.setVisible(false);
        TutorWindow tutorWindow = new TutorWindow(user);
    }
}
