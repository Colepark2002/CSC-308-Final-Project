

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
        try {
          Blackboard.getInstance().setProblem(FileHandler.loadProblem("p1"));
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
        break;

      case "Problem Two":
        try {
          Blackboard.getInstance().setProblem(FileHandler.loadProblem("p2"));
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
        break;

      case "Problem Three":
        try {
          Blackboard.getInstance().setProblem(FileHandler.loadProblem("p3"));
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
        break;
    }
  }
}
