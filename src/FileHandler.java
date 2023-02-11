import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Bret Craig
 */
public class FileHandler {

    /**
     * Serializes given list of boxes to given file name.
     * @param o
     * @param fileName
     */
     static void save(Object o, String fileName) {
        try {
            File f = new File(fileName);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.println("\nProject saved in " + fileName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the given file and loads data to list.
     * @param fileName
     * @return List
     */
    static Stack<ClassBox> load(String fileName) {
        Stack<ClassBox> list = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (Stack<ClassBox>) in.readObject();
            System.out.println("\nProject loaded from " + fileName);
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return list;

    }

}
