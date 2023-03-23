import java.util.ArrayList;
import java.util.Vector;

/**
 * Custom Parser for the toString of the UML tutor
 *
 * @author Van Park
 * @version 1.0
 */

public class Parser {
    int current = 0;
    ArrayList<String> classes = new ArrayList<>();
    ArrayList<String> methods = new ArrayList<>();
    ArrayList<String> variables = new ArrayList<>();

    /**
     * Takes the vector of tokens and parses them into classes, methods, and variables
     * @param tokens the vector of tokens obtained from the lexer
     */
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

    /**
     * Takes the vector of tokens and parses specifically the methods and variables.
     * @param tokens the vector of tokens obtained from the lexer
     */
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

    public int numClasses()
    {
        return classes.size();
    }

    public int numMethods()
    {
        return methods.size();
    }

    public int numVars()
    {
        return variables.size();
    }
}