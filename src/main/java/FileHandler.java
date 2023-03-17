
import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @author Bret Craig
 * @version 2.1
 */
public class FileHandler {

    /**
     * Serializes problem data to a given file name.
     *
     * @param fileName
     */
    static void saveProblem(String fileName) {
        try {
            Problem p = new Problem();
            p.setStack(Blackboard.getInstance().getStack());
            int prof = Integer.parseInt(JOptionPane.showInputDialog("Enter Problem Proficiency:"));
            p.setProficiency(prof);
            File f = new File(fileName);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();
            System.out.println("\nProblem saved in " + fileName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deserializes the given file and loads Problem data
     * 
     * @param fileName
     * @return List
     */
    static Problem loadProblem(String fileName) throws SQLException {
        Problem problem = new Problem();
        String user = Blackboard.getInstance().getUser();
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Problem potential_problem = (Problem) in.readObject();
            if (potential_problem.proficiency <= Blackboard.getInstance().getDb().getProficiency(user)) {
                System.out.println("\nProblem loaded: " + fileName);
                problem = potential_problem;
            } else {
                JOptionPane.showMessageDialog(null, "You do not meet the required proficiency for this problem.");
            }
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return problem;
    }

}
