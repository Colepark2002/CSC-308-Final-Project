package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 * Allows text to be displayed to screen describing the connections and relation
 * between boxes in the DisplayPanel
 *
 * @author Cole Park
 * @author Lauren Allen
 * @version 1.0
 */
public class TextPanel extends JPanel implements Observer {

    JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        Font f = new Font("Helvetica", Font.PLAIN, 20);
        textArea.setFont(f);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scroll);
        setVisible(true);

        Blackboard.getInstance().setText(textArea);
        Blackboard.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setTextArea();
    }

    /**
     * Updates text box with correct methods according to boxes in draw area
     */
    public void setTextArea() {
        Stack<ClassBox> boxList = Blackboard.getInstance().getStack();
        String fullString = "";
        for (ClassBox box : boxList) {
            fullString += box.toString();
        }
        Blackboard.getInstance().getText().setText(fullString);
        textArea = Blackboard.getInstance().getText();
    }
}
