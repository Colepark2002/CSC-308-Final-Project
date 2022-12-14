import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Works as our Controller for the Menu to handle actions when buttons are pressed in the menus
 * @author Cole Park
 */
public class MenuController implements ActionListener
{
    /**
     * Handles menu functionality and connection selections
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "New":
                FinalProject.start();
                break;

            case "Save":
                FileHandler.save(Blackboard.getInstance().getDp().getStack(),
                                JOptionPane.showInputDialog("Enter a File Name"));
                break;

            case "Load":
                FinalProject.start();
                Stack<ClassBox> s = FileHandler.load(JOptionPane.showInputDialog("Enter a File Name"));
                Blackboard.getInstance().getDp().setStack(s);
                for(ClassBox b : s)
                {
                    Blackboard.getInstance().getDp().add(b);
                }
                Blackboard.getInstance().getDp().repaint();

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
