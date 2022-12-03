import javax.swing.*;
import java.util.Observable;


/**
 * Our Blackboard and Singleton class used to communicate between classes for the DrawPanel and TextPanel
 * @author Cole Park
 */
public class Blackboard extends Observable
{
    JTextArea text;
    DrawPanel dp;
    String connection = "Association";

    private static Blackboard instance = null;

    private Blackboard()
    {
    }

    public static Blackboard getInstance()
    {
        if(instance == null)
        {
            instance = new Blackboard();
        }
        return instance;
    }

    public void setText(JTextArea t)
    {
        text = t;
    }

    public JTextArea getText() {
        return text;
    }

    public DrawPanel getDp() {
        return dp;
    }
    public void setDp(DrawPanel dp) {
        this.dp = dp;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
        System.out.println(connection);
    }

    public void notifyObservers()
    {
        setChanged();
        super.notifyObservers();
    }

}
