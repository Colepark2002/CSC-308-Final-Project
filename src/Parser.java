import java.util.ArrayList;
import java.util.Vector;
// Made by Jacob Shapero, Bret Craig, Van Park

public class Parser {
    int current = 0;
    ArrayList<String> classes = new ArrayList<>();
    ArrayList<String> methods = new ArrayList<>();
    ArrayList<String> variables = new ArrayList<>();

    public void init(Vector<Token> tokens) {
        String className;
        while(current < tokens.size()) {
            if (tokens.get(current).getWord().equals("Class")) {
                className = tokens.get(current + 1).getWord();
                current += 2;
                if (!classes.contains(className)) {
                    classes.add(className);
                }
            }
            else if (tokens.get(current).getWord().equals("extends")) {
                    className = tokens.get(current + 1).getWord();
                    current += 2;
                    if (!classes.contains(className)) {
                        classes.add(className);
                    }
            } else if (tokens.get(current).getWord().equals("{")){
                current++;
                methodsAndVariables(tokens);
            }
            else{
                current++;
            }
        }
    }

    public void methodsAndVariables(Vector<Token> tokens){
        int numOpening = 0;
        int numClosing = 0;
        while(!(numClosing > numOpening)){
            if (tokens.get(current).getWord().equals("}")){
                numClosing++;
            }
            if (tokens.get(current).getWord().equals(";")){
                variables.add(tokens.get(current - 1).getWord());
            }
            else if (tokens.get(current).getWord().equals("{")){
                numOpening++;
                int i = current;
                while(!tokens.get(i).getWord().equals("(")){
                    i--;
                }
                methods.add(tokens.get(i-1).getWord());
            }
            current++;
        }
    }



    public ArrayList<String> getClasses() {
        return classes;
    }

    public ArrayList<String> getMethods() {
        return methods;
    }

    public ArrayList<String> getVariables(){
        return variables;
    }
}