import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel
{
    public TextPanel()
    {
        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        Font f = new Font("Helvetica", Font.PLAIN, 20);
        textArea.setFont(f);
        JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scroll);
        setVisible (true);
    }
}
