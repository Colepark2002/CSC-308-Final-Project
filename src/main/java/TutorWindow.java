package main.java;

import javax.swing.*;
import java.awt.*;

/**
 * Tutor window GUI
 * 
 * @author Cole Park
 * @author Bret Craig
 * @version 3.1
 */
public class TutorWindow extends JFrame {

    /**
     * Project Constructor
     */
    public TutorWindow(String user) {
        super("UML Tutor - " + user);
        GridLayout grid = new GridLayout(1, 2);
        this.setLayout(grid);

        JMenuBar mb = new JMenuBar();
        MenuController mc = new MenuController();
        ProblemController pc = new ProblemController();

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu problems = new JMenu("Problems");
        JMenu connection = new JMenu("Connections");
        JMenu proficiency = new JMenu("Proficiency");

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

        JMenuItem p1 = new JMenuItem("Problem One");
        JMenuItem p2 = new JMenuItem("Problem Two");
        JMenuItem p3 = new JMenuItem("Problem Three");

        problems.add(p1);
        problems.add(p2);
        problems.add(p3);

        p1.addActionListener(pc);
        p2.addActionListener(pc);
        p3.addActionListener(pc);

        connection.add(c1);
        connection.add(c2);
        connection.add(c3);

        mb.add(file);
        mb.add(help);
        mb.add(problems);
        mb.add(connection);
        mb.add(proficiency);
        setJMenuBar(mb);

        JPanel feedbackPanel = new JPanel();
        JMenuBar fMenuBar = new JMenuBar();
        JButton check = new JButton("Check");
        JButton hint = new JButton("Hint");
        JButton submit = new JButton("Submit");
        TextArea text = new TextArea();

        FeedbackController fc = new FeedbackController();

        check.addActionListener(fc);
        hint.addActionListener(fc);
        submit.addActionListener(fc);
        fMenuBar.add(check);
        fMenuBar.add(hint);
        fMenuBar.add(submit);
        feedbackPanel.add(fMenuBar);
        feedbackPanel.add(text);

        TextPanel tp = new TextPanel();
        DrawPanel dp = new DrawPanel();

        BorderLayout border = new BorderLayout();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(border);
        rightPanel.add(dp, BorderLayout.CENTER);
        rightPanel.add(feedbackPanel, BorderLayout.SOUTH);
        tp.setBackground(Color.DARK_GRAY);
        dp.setBackground(new Color(40, 100, 40));
        add(tp);
        add(rightPanel);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
