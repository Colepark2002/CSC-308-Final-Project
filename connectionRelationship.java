public class connectionRelationship {
    ClassBox box1;
    ClassBox box2;
    String connecTYpe;
    public connectionRelationship(ClassBox firstBox, ClassBox secondBox, String connectionType){
        box1 = firstBox;
        box2 = secondBox;
        connecTYpe = connectionType;
    }
    public ClassBox getFirstBox(){
        return box1;
    }
    public ClassBox getSecondBox(){
        return box2;
    }
    public String getConnection(){
        return connecTYpe;
    }
}
