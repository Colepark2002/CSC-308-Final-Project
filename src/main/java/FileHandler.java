
import javax.swing.*;
import java.io.*;
import java.util.Stack;

/**
 * @author Bret Craig
 * @version 1.99
 */
public class FileHandler {

    /**
     * Serializes given list of boxes to given file name.
     * 
     * @param problem
     * @param fileName
     */
    static void saveProblem(Problem problem, String fileName) {
        try {
            int prof = Integer.parseInt(JOptionPane.showInputDialog("Enter Problem Proficiency:"));
            problem.setProficiency(prof);
            File f = new File(fileName);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(problem);
            out.close();
            fileOut.close();
            System.out.println("\nProject saved in " + fileName + "\n");
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
    static Problem loadProblem(String fileName) {
        Problem problem = null;
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
