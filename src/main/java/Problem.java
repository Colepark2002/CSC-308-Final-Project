

import java.io.Serializable;
import java.util.Stack;

/**
 * @author Jacob Shapero
 * @version 1.0
 */
public class Problem implements Serializable {
    Stack<ClassBox> stack;
    String problemString;
    int proficiency;

    public Problem() {
        stack = new Stack<ClassBox>();
        problemString = "";
        proficiency = 0;
    }

    public Stack<ClassBox> getUML() {
        return stack;
    }

    public String getCode() {
        return problemString;
    }

    public void initProblemStack(Stack<ClassBox> problemStack) {
        stack = problemStack;
    }

    public void initProblemString(String p) {
        problemString = p;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
}
