import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                FileHandler.save(Blackboard.getInstance().getDp().getStack(), "Save" + n++);
                break;

            case "Load":
                FileHandler.load(JOptionPane.showInputDialog("Enter a FileName"));
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
