import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TextPanel extends JPanel implements Observer
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

        Blackboard.getInstance().setText(textArea);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
