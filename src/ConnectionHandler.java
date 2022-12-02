import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Stack;

public class ConnectionHandler {

    DrawPanel drawPanel;
    private static ConnectionHandler instance = null;
    public ClassBox connectionBox1 = null;
    public ClassBox connectionBox2 = null;
    ArrayList<connectionRelationship> connections = new ArrayList<connectionRelationship>();
    private ConnectionHandler() {
    }

    private String getSide(ClassBox box1, ClassBox box2){
        String side = "";
        int xdif = box1.getX() - box2.getX();
        int ydif = box1.getY() - box2.getY();
        if (-85 < xdif && xdif < 85){
            if (ydif > 0){
                side = "Up";
                System.out.println(side);
            }
            if (ydif < 0){
                side = "Down";
                System.out.println(side);
            }
        }
        else if (xdif < -85){
            side = "Right";
            System.out.println(side);
        }
        else{
            side = "Left";
            System.out.println(side);
        }
        return side;
    }

    public static ConnectionHandler getInstance() {
        if (instance == null) {
            synchronized(ConnectionHandler.class) {
                if (instance == null) {
                    instance = new ConnectionHandler();
                }
            }
        }
        return instance;
    }

    public void setDrawPanel(DrawPanel d){
        drawPanel = d;
    }

    public void beginConnection(ClassBox box, String connectType){
        if(connectionBox1 == null){
            connectionBox1 = box;

        }
        else if(connectionBox2 == null){
            connectionBox2 = box;
            String side = getSide(connectionBox1, connectionBox2);
            connections.add(new connectionRelationship(connectionBox1, connectionBox2, connectType, side));
            System.out.println(connections);
            connectionBox1 = null;
            connectionBox2 = null;
        }
    }

    public void drawArrowHead(Graphics g, int x1, int x2, int y1, int y2, String otherSide){
        Polygon arrowhead = new Polygon();
        arrowhead.addPoint(x2 + 50, y2);
        arrowhead.addPoint(x2 + 45, y2 - 10);
        arrowhead.addPoint(x2 + 55, y2 - 10);
        g.setColor(Color.BLACK);
        g.fillPolygon(arrowhead);
        if (otherSide.equals("Up")){

        }
        else if (otherSide.equals("Down")){

        }
        else if (otherSide.equals("Left")){

        }
        else if (otherSide.equals("Right")){

        }
    }

    public void drawTriangleHead(Graphics g, int x1, int x2, int y1, int y2, String otherSide){
        if (otherSide.equals("Up")){

        }
        else if (otherSide.equals("Down")){

        }
        else if (otherSide.equals("Left")){

        }
        else if (otherSide.equals("Right")){

        }
    }

    public void drawDiamond(Graphics g, int x1, int x2, int y1, int y2, String otherSide){
        if (otherSide.equals("Up")){

        }
        else if (otherSide.equals("Down")){

        }
        else if (otherSide.equals("Left")){

        }
        else if (otherSide.equals("Right")){

        }
    }
    public void drawConnections(Graphics g){
        for(connectionRelationship c: connections){
            String connectType = c.getconnecType();
            String selfSide = c.getSide();
            String otherSide = "";
            switch (selfSide) {
                case "Up": {
                    if (connectType.equals("Association")){

                    }
                    else if (connectType.equals("Inheritance")){

                    }
                    else if (connectType.equals("Composition")){

                    }
                }
                case "Down": {
                    if (connectType.equals("Association")){
                        drawArrowHead(g, 0, 0, 0, 40, otherSide );
                    }
                    else if (connectType.equals("Inheritance")){

                    }
                    else if (connectType.equals("Composition")){

                    }

                }
                case "Left": {

                }
                case "Right": {

                }
            }
        }
    }
}