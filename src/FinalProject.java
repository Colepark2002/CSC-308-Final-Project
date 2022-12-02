import javax.swing.*;
import java.awt.*;

public class FinalProject extends JFrame
{
    public static void main(String[] args) {
        FinalProject win = new FinalProject();
        win.setSize(500,500);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public FinalProject()
    {
        GridLayout grid = new GridLayout(1,2);
        this.setLayout(grid);

        JMenuBar mb = new JMenuBar();
        MenuController mc = new MenuController();

        JMenu File = new JMenu("File");
        JMenu Help = new JMenu("Help");
        JMenu connection = new JMenu("Connections");

        JMenuItem Load = new JMenuItem("Load");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem New = new JMenuItem("New");
        Load.addActionListener(mc);
        Save.addActionListener(mc);
        New.addActionListener(mc);

        JMenuItem A1 = new JMenuItem("Cole P.");
        JMenuItem A2 = new JMenuItem("Van P.");
        JMenuItem A3 = new JMenuItem("Bret C.");
        JMenuItem A4 = new JMenuItem("Lauren A.");
        JMenuItem A5 = new JMenuItem("Jacob S.");

        JMenuItem C1 = new JMenuItem("Association");
        JMenuItem C2 = new JMenuItem("Inheritance");
        JMenuItem C3 = new JMenuItem("Composition");
        C1.addActionListener(mc);
        C2.addActionListener(mc);
        C3.addActionListener(mc);

        File.add(New);
        File.add(Save);
        File.add(Load);

        Help.add(A1);
        Help.add(A2);
        Help.add(A3);
        Help.add(A4);
        Help.add(A5);

        connection.add(C1);
        connection.add(C2);
        connection.add(C3);

        mb.add(File);
        mb.add(Help);
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
