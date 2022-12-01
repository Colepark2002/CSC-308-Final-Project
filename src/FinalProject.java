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
        JMenu menu = new JMenu();
    }
}
