

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Stack;

/**
 * Works as our Controller for the Menu to handle actions when buttons are
 * pressed in the menus
 * 
 * @author Cole Park
 * @author Bret Craig
 * @version 1.5
 */
public class MenuController implements ActionListener {
    /**
     * Handles menu functionality and connection selections
     * 
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                Blackboard.getInstance().setNew();
                break;

            case "Save":
                FileHandler.saveProblem(JOptionPane.showInputDialog("Enter a File Name"));
                break;

            case "Load":
                Problem p = null;
                try {
                    p = FileHandler.loadProblem(JOptionPane.showInputDialog("Enter a File Name"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Blackboard.getInstance().setProblem(p);
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
