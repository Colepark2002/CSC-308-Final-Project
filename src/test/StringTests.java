import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class StringTests {

  @Test
  void nameTest() {
    ClassBox cBox = new ClassBox("test", 0, 0);
    String result = cBox.toString();
    String expected = "Class test {" + "\n" + "} \n\n";
    Assertions.assertEquals(expected, result);
  }

  @Test
  void varTest() {
    ClassBox cBox = new ClassBox("test", 0, 0);
    cBox.addVar("variable");
    String result = cBox.toString();
    String expected = "Class test {" + "\n" + "     variable" + ";\n" + "} \n\n";
    Assertions.assertEquals(expected, result);
  }

  @Test
  void methodTest() {
    ClassBox cBox = new ClassBox("test", 0, 0);
    cBox.addMethod("tempMethod");
    String result = cBox.toString();
    String expected = "Class test {" + "\n" + "     tempMethod" + "()" + "{" + "\n" + "     " + "}" + "\n" + "} \n\n";
    Assertions.assertEquals(expected, result);
  }

  @Test
  void fullTest() {
    ClassBox cBox = new ClassBox("test", 0, 0);
    cBox.addVar("variable");
    cBox.addMethod("tempMethod");
    String result = cBox.toString();
    String expected = "Class test {" + "\n" + "     variable" + ";\n" + "     tempMethod" + "()" + "{" + "\n" + "     "
        + "}" + "\n" + "} \n\n";
    Assertions.assertEquals(expected, result);
  }

}
