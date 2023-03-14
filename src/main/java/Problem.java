package main.java;

import java.util.ArrayList;
import java.util.Stack;

public class Problem {
    Stack<ClassBox> stack;
    String problemString;

    public Problem(){
        stack = new Stack<ClassBox>();
        problemString = "";
    }

    public Stack<ClassBox> getUML(){
        return stack;
    }
    public String getCode(){
        return problemString;
    }
    public void initProblemStack(Stack<ClassBox> problemStack) {
        stack = problemStack;
    }
    public void initProblemString(String p){
        problemString = p;
    }
}
