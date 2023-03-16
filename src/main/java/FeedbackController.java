

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

public class FeedbackController implements ActionListener {
    /**
     * Handles menu functionality and connection selections
     * 
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Submit":
                if (problemCompare()) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Incorrect");
                }
                break;

            case "Hint":
                // to be implemented
                break;

            case "Check":
                // to be implemented
                break;

            case "Get Proficiency":
                int prof = 0;
                try {
                    prof = Blackboard.getInstance().getDb().getProficiency(Blackboard.getInstance().getUser());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Your Proficiency is: " + prof);

            default:
                break;
        }
    }

    public Problem SampleProblemCreator() {
        Stack<ClassBox> probStack = new Stack<ClassBox>();
        ArrayList<connectionRelationship> probConnections = new ArrayList<connectionRelationship>();

        ClassBox probBox1 = new ClassBox("A", 0, 0);
        ClassBox probBox2 = new ClassBox("B", 0, 0);
        ClassBox probBox3 = new ClassBox("C", 0, 0);
        ClassBox probBox4 = new ClassBox("D", 0, 0);

        probStack.add(probBox1);
        probStack.add(probBox2);
        probStack.add(probBox3);
        probStack.add(probBox4);

        Problem testProblem = new Problem();
        testProblem.initProblemStack(probStack);
        testProblem.initProblemString("");

        return testProblem;
    }

    public Boolean problemCompare() {
        Problem x = SampleProblemCreator();
        Blackboard.getInstance().setProblem(x);
        Boolean matching = true;
        if (Blackboard.getInstance().getStack().size() != x.getUML().size()) {
            return false;
        }
        for (ClassBox p : Blackboard.getInstance().getStack()) {
            boolean nameFound = false;
            for (ClassBox pp : x.getUML()) {
                if (p.getName().equals(pp.getName())) {
                    nameFound = true;
                }
            }
            matching = nameFound;
        }
        return matching;
    }
}
