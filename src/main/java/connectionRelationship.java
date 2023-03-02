import java.io.Serializable;

/**
 * The format for the relationship between connected classes.
 * @author Van Park
 * @version 1.0
 */

public class connectionRelationship implements Serializable {
    ClassBox box1;
    ClassBox box2;
    String connecType;
    String side;
    public connectionRelationship(ClassBox firstBox, ClassBox secondBox, String connectionType, String side){
        box1 = firstBox;
        box2 = secondBox;
        connecType = connectionType;
        this.side = side;
    }
    public ClassBox getFirstBox(){
        return box1;
    }
    public ClassBox getSecondBox(){
        return box2;
    }
    public String getconnecType(){
        return connecType;
    }
    public String getSide() { return side;}
    public void setSide(String s){side = s;}
}
