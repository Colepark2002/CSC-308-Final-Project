import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Cole Park
 */
public class MenuController implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "New":
                //call the New method
                break;

            case "Save":
                //call the Save method
                break;

            case "Load":
                // call the Load method
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
