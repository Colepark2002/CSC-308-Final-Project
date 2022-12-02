import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                // Set Association flag
                break;

            case "Inheritance":
                // Set Inheritance flag
                break;

            case "Composition":
                // Set Composition flag
                break;


            default:
                break;
        }
    }
}
