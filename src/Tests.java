import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Tests {

    @Test
    void LexerTestEmpty(){
        Lexer t = new Lexer("");
        t.run();

        ArrayList<String> test = new ArrayList<String>();
        Assertions.assertArrayEquals(test.toArray(), t.getTokens().toArray());
    }

    @Test
    void LexerTestShort(){
        Lexer t = new Lexer("class Student{\n}");
        t.run();
        ArrayList<String> test = new ArrayList<String>(Arrays.asList("class", "Student", "{", "}"));

        int counter = 0;
        for (Token tok : t.getTokens()){
            Assertions.assertEquals(tok.getWord(), test.get(counter));
            counter += 1;
        }
    }

    @Test
    void LexerTestLong(){
        Lexer t = new Lexer("class Student extends Person{\n " +
                "Hello world(){\n    }\n}\nclass Jacobius extends Person{\n}");
        t.run();
        ArrayList<String> test = new ArrayList<String>(Arrays.asList("class", "Student", "extends", "Person",
                "{", "Hello", "world", "(", ")", "{", "}", "}", "class", "Jacobius", "extends", "Person", "{", "}"));

        int counter = 0;
        for (Token tok : t.getTokens()){
            Assertions.assertEquals(tok.getWord(), test.get(counter));
            counter += 1;
        }
    }


    @Test
    void LexerTestPrimitiveTypes() {
        Lexer t = new Lexer("class Student extends Person{\n    int num = 6 + 0xF3 + 0b01001 + 024 + 1244363547456" +
                " + 46.03404738;\n}");
        t.run();

        ArrayList<String> test = new ArrayList<String>(Arrays.asList("class", "Student", "extends", "Person", "{", "int",
                "num", "=", "6", "+", "0xF3", "+", "0b01001", "+", "024", "+", "1244363547456", "+", "46.03404738", ";", "}"));

        int counter = 0;
        for (Token tok : t.getTokens()) {
            Assertions.assertEquals(tok.getWord(), test.get(counter));
            counter += 1;
        }
    }

    @Test
    void LexerTestStrings(){
        Lexer t = new Lexer("class Student extends Person{\n    String s = \"Hello\";\n char c = 'c';\n}");
        t.run();

        ArrayList<String> test = new ArrayList<String>(Arrays.asList("class", "Student", "extends", "Person", "{", "String",
                    "s", "=", "\"Hello\"", ";","char", "c", "=", "'c'", ";", "}"));

        int counter = 0;
        for (Token tok : t.getTokens()){
            Assertions.assertEquals(tok.getWord(), test.get(counter));
            counter += 1;
        }
    }

    @Test
    void ParserClassesTests(){
        Lexer t = new Lexer("""
                class Student extends Person{
                    public int var;
                }
                class Person{
                }
                class Lauren extends Coder{
                }""");
        t.run();
        Parser p = new Parser();
        p.init(t.getTokens());

        ArrayList<String> answer = new ArrayList<>();
        answer.add("Student");
        answer.add("Person");
        answer.add("Lauren");
        answer.add("Coder");
        Assertions.assertArrayEquals(p.getClasses().toArray(), answer.toArray());
    }

    @Test
    void ParserVariablesTests(){
        Lexer t = new Lexer("""
                class Student extends Person{
                    private int var;
                    private int[] books;
                    private boolean correct;
                }""");
        t.run();
        Parser p = new Parser();
        p.init(t.getTokens());

        ArrayList<String> answer = new ArrayList<>();
        answer.add("var");
        answer.add("books");
        answer.add("correct");
        Assertions.assertArrayEquals(p.getVariables().toArray(), answer.toArray());
    }

    @Test
    void ParserMethodsTests(){
        Lexer t = new Lexer("""
                class Student extends Person{
                    private int var;
                    public static void main(String args[]){
                    }
                    public int getVar(){
                    }
                    public void setVar(int n){
                    }
                }""");
        t.run();
        Parser p = new Parser();
        p.init(t.getTokens());

        ArrayList<String> answer = new ArrayList<>();
        answer.add("main");
        answer.add("getVar");
        answer.add("setVar");
        Assertions.assertArrayEquals(p.getMethods().toArray(), answer.toArray());
    }

}
