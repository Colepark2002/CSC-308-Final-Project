import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Works as our Controller for problem selection from menu
 * 
 * @author Lauren Allen
 * @version 1.0
 */
public class ProblemController implements ActionListener {
  /**
   * Handles problem drop down menu functionality and problem selections
   * 
   * @param e action event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Problem One":
        break;

      case "Problem Two":
        break;

      case "Problem Three":
        break;
    }
  }
}
