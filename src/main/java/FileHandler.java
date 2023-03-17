
import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @author Bret Craig
 * @version 2.0
 */
public class FileHandler {

    /**
     * Serializes given list of boxes to given file name.
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
     * Deserializes the given file and loads UML data
     * 
     * @param fileName
     * @return List
     */
    static Problem loadProblem(String fileName) throws SQLException {
        Problem problem = null;
        String user = Blackboard.getInstance().getUser();
        int proficiency = Blackboard.getInstance().getDb().getProficiency(user);
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            problem = (Problem) in.readObject();
            System.out.println("\nProblem loaded" + fileName);
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return problem;
    }

}
