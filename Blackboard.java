import javax.swing.*;
import java.util.Observable;

public class Blackboard extends Observable
{
    JTextArea text;
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
}
