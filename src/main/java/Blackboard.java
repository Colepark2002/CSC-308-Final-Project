

import javax.swing.*;
import java.util.Observable;
import java.util.Stack;

/**
 * Our Blackboard and Singleton class used to communicate between classes for
 * the DrawPanel and TextPanel
 * 
 * @author Cole Park
 * @version 2.0
 */
public class Blackboard extends Observable {
    JTextArea text;
    String connection = "Association";
    Stack<ClassBox> stack = new Stack<>();
    UserAccountDB db;
    Problem problem = new Problem();
    String user;

    private static Blackboard instance = null;

    private Blackboard() {
    }

    public static Blackboard getInstance() {
        if (instance == null) {
            instance = new Blackboard();
        }
        return instance;
    }

    public void setText(JTextArea t) {
        text = t;
    }

    public JTextArea getText() {
        return text;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    public void setStack(Stack<ClassBox> stack) {
        this.stack = stack;
        this.notifyObservers();
    }

    public Stack<ClassBox> getStack() {
        return stack;
    }

    public void setDb(UserAccountDB d) {
        db = d;
    }

    public UserAccountDB getDb() {
        return db;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        notifyObservers();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setNew() {
        this.setStack(new Stack<>());
        this.setProblem(new Problem());
    }
}
