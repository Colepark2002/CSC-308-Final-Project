import javax.swing.*;
import java.util.Observable;
import java.util.Stack;


/**
 * Our Blackboard and Singleton class used to communicate between classes for the DrawPanel and TextPanel
 * @author Cole Park
 */
public class Blackboard extends Observable
{
    JTextArea text;
    String connection = "Association";
    Stack<ClassBox> stack = new Stack<ClassBox>();

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

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public void notifyObservers()
    {
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

}
