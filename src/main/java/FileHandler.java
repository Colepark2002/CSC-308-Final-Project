

import java.io.*;
import java.util.Stack;

/**
 * @author Bret Craig
 * @version 1.99
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
     * Deserializes the given file and loads UML data
     * @param fileName
     * @return List
     */
    static Stack<ClassBox> loadUML(String fileName) {
        Stack<ClassBox> list = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (Stack<ClassBox>) in.readObject();
            System.out.println("\nUML Problem loaded" + fileName);
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * Deserializes the given file and loads code data
     * @param fileName
     * @return List
     */
    static String loadCode(String fileName) {
        String code = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            code = (String) in.readObject();
            System.out.println("\nCode problem loaded:" + fileName);
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return code;

    }

}
