import javax.swing.*;
import java.awt.*;


/**
 * This class acts as our view in a model view controller architecture to create our window and see the project
 * @author Cole Park
 * @version 2.0
 */
public class FinalProject extends JFrame {
    private static FinalProject win = null;

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        start();
    }

    /**
     * start function used to reset project when a new one is created
     */
    public static void start() {
        if (win != null) {
            win.setVisible(false);
            win.dispose();
        }
        win = new FinalProject();
        win.setSize(500, 500);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Project Constructor
     */
    public FinalProject() {
        GridLayout grid = new GridLayout(1, 2);
        this.setLayout(grid);

        JMenuBar mb = new JMenuBar();
        MenuController mc = new MenuController();

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu connection = new JMenu("Connections");

        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem newButton = new JMenuItem("New");
        load.addActionListener(mc);
        save.addActionListener(mc);
        newButton.addActionListener(mc);

        JMenuItem a1 = new JMenuItem("Cole P.");
        JMenuItem a2 = new JMenuItem("Van P.");
        JMenuItem a3 = new JMenuItem("Bret C.");
        JMenuItem a4 = new JMenuItem("Lauren A.");
        JMenuItem a5 = new JMenuItem("Jacob S.");

        JMenuItem c1 = new JMenuItem("Association");
        JMenuItem c2 = new JMenuItem("Inheritance");
        JMenuItem c3 = new JMenuItem("Composition");
        c1.addActionListener(mc);
        c2.addActionListener(mc);
        c3.addActionListener(mc);

        file.add(newButton);
        file.add(save);
        file.add(load);

        help.add(a1);
        help.add(a2);
        help.add(a3);
        help.add(a4);
        help.add(a5);

        connection.add(c1);
        connection.add(c2);
        connection.add(c3);

        mb.add(file);
        mb.add(help);
        mb.add(connection);
        setJMenuBar(mb);

        TextPanel tp = new TextPanel();
        DrawPanel dp = new DrawPanel();
        Blackboard.getInstance().setDp(dp);
        tp.setBackground(Color.DARK_GRAY);
        dp.setBackground(new Color(40, 100, 40));
        add(tp);
        add(dp);
    }
}
