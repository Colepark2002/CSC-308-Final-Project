

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class FeedbackController implements ActionListener {
    /**
     * Handles menu functionality and connection selections
     * 
     * @param e action event
     */
    private TextArea txtArea;
    public FeedbackController(TextArea area){
        Font f = new Font("Helvetica", Font.PLAIN, 20);
        area.setFont(f);
        txtArea = area;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Submit":
                Problem x = Blackboard.getInstance().getProblem();
                if(problemCompare(x)){
                    txtArea.setText("CORRECT");
                    String user = Blackboard.getInstance().getUser();
                    try {
                        int currProf = Blackboard.getInstance().getDb().getProficiency(user);
                        if (currProf <= Blackboard.getInstance().getProblem().getProficiency()) {
                            Blackboard.getInstance().getDb().setProficiency(user, currProf+1);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else
                    txtArea.setText("INCORRECT");
                break;

            case "Hint":
                Stack<ClassBox> stack = Blackboard.getInstance().getStack();
                String res = "";
                for (ClassBox box : stack) {
                    res += box.toString();
                }
                Lexer personal = new Lexer(res);
                personal.run();
                Parser p = new Parser();
                p.init(personal.getTokens());

                Stack<ClassBox> problemStack = Blackboard.getInstance().getProblem().getUML();
                String probString = "";
                for (ClassBox box : problemStack) {
                    probString += box.toString();
                }
                Lexer problem = new Lexer(probString);
                problem.run();
                Parser p2 = new Parser();
                p2.init(problem.getTokens());

                String hint = """
                        Classes Missing: %d
                        Methods Missing: %d
                        Variables Missing: %d
                        """.formatted(p2.numClasses() - p.numClasses(),p2.numMethods() - p.numMethods(), p2.numVars() - p.numVars());
                txtArea.setText(hint);

                break;

            case "Check":
                Stack<ClassBox> boxList = Blackboard.getInstance().getStack();
                String fullString = "";
                for (ClassBox box : boxList) {
                    fullString += box.toString();
                }
                txtArea.setText(fullString);
                break;

            case "Get Proficiency":
                int prof = 0;
                try {
                    prof = Blackboard.getInstance().getDb().getProficiency(Blackboard.getInstance().getUser());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Your Proficiency is: " + prof);
                break;
            case "Clear":
                Blackboard.getInstance().setStack(new Stack<ClassBox>());
                break;

            default:
                break;
        }
    }

    //RETURNS TRUE IF CORRECT, RETURN FALSE IF INCORRECT
    public Boolean problemCompare(Problem x) {
        //400 line method incoming 😎
        if (Blackboard.getInstance().getStack().size() != x.getUML().size()) {
            return false;
        }
        Stack<ClassBox> unseen = (Stack)x.getUML().clone();
        Stack<ClassBox> unseen2 =(Stack)x.getUML().clone();

        //this bit checks for classes
        for (ClassBox p : Blackboard.getInstance().getStack()) {
            unseen.removeIf(pp -> p.getName().equals(pp.getName()));
        }
        if(unseen.size() > 0){
            return false;
        }

        //this bit checks for variables
        for (ClassBox p : Blackboard.getInstance().getStack()){
            for(ClassBox pp : unseen2){
                if(p.getName().equals(pp.getName())){
                    if(!(p.getVariables().equals(pp.getVariables()))){
                        System.out.println("VARIABLES DON'T MATCH");
                        return false;
                    }
                }
            }
        }

        //this bit checks for methods
        for (ClassBox p : Blackboard.getInstance().getStack()){
            for(ClassBox pp : unseen2){
                if(p.getName().equals(pp.getName())){
                    if(!(p.getMethods().equals(pp.getMethods()))){
                        System.out.println("METHODS DON'T MATCH");
                        return false;
                    }
                }
            }
        }

        //this bit checks for methods
        for (ClassBox p : Blackboard.getInstance().getStack()){
            for(ClassBox pp : unseen2){
                if(p.getName().equals(pp.getName())){
                    ArrayList<connectionRelationship> pC = p.connectionHandler.getConnections();
                    ArrayList<connectionRelationship> ppC = pp.connectionHandler.getConnections();
                    //LAMBDA POGCHAMP
                    Collections.sort(pC,(p1, p2)->{return p1.connecType.compareTo(p2.connecType);});
                    Collections.sort(ppC,(p1, p2)->{return p1.connecType.compareTo(p2.connecType);});
                    if(pC.size() != ppC.size()){
                        System.out.println("CONNECTIONS ARE PROPER BOGGED");
                        return false;
                    }
                    for(int i = 0; i < pC.size(); i++){
                        if(!(pC.get(i).box1.getName().equals(ppC.get(i).box1.getName()) && pC.get(i).box2.getName().equals(ppC.get(i).box2.getName())
                        && pC.get(i).getconnecType().equals(ppC.get(i).getconnecType()))){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}