import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 *
 * @author Cole Park
 */
public class MenuController implements ActionListener
{
    int n = 1;
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "New":

                break;

            case "Save":
                FileHandler.save(Blackboard.getInstance().getDp().getStack(),
                                JOptionPane.showInputDialog("Enter a File Name"));
                break;

            case "Load":
                Stack<ClassBox> s = FileHandler.load(JOptionPane.showInputDialog("Enter a File Name"));
                Blackboard.getInstance().getDp().setStack(s);
                break;

            case "Association":
                Blackboard.getInstance().setConnection("Association");
                break;

            case "Inheritance":
                Blackboard.getInstance().setConnection("Inheritance");
                break;

            case "Composition":
                Blackboard.getInstance().setConnection("Composition");
                break;


            default:
                break;
        }
    }
}
