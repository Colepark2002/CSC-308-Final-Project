import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileHandler {


    void save(Object o, String fileName) {
        try {
            File f = new File(fileName);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.println("\nProject saved in" + fileName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Object> load(String fileName) {
        List<Object> list = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (List<Object>) in.readObject();
            System.out.println("\nProject loaded from project.txt");
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    void newProject (String fileName) {
        File f = new File(fileName);
    }

}
