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
                System.out.println(box1.getClassName() + " " + side);
            }
            if (ydif < 0){
                side = "Down";
                System.out.println(box1.getClassName() + " " + side);
            }
        }
        else if (xdif < -85){
            side = "Right";
            System.out.println(box1.getClassName() + " " + side);
        }
        else{
            side = "Left";
            System.out.println(box1.getClassName() + " " + side);
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
            for (connectionRelationship c : connections){
                System.out.print(c.getFirstBox().getClassName() + " is connected to " + c.getSecondBox().getClassName() + ", ");
            }
            System.out.println();
            connectionBox1 = null;
            connectionBox2 = null;
        }
    }

    public void updateSides(){
        for (connectionRelationship c : connections){
            c.setSide(getSide(c.getFirstBox(), c.getSecondBox()));
        }
    }

    public void drawArrowHead(Graphics g, int x1, int x2, int y1, int y2, String otherSide){
        switch (otherSide) {
            case "Up": {
                Point p1 = new Point(x2 + 50, y2);
                Point p2 = new Point(x2 + 45, y2 - 10);
                Point p3 = new Point(x2 + 55, y2 - 10);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1, y1, x2 + 50, y2);
                break;
            }

            case "Down": {
                Point p1 = new Point(x2 + 50, y2 + 50);
                Point p2 = new Point(x2 + 45, y2 + 60);
                Point p3 = new Point(x2 + 55, y2 + 60);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1, y1, x2 + 50, y2);
                break;
            }

            case "Left": {
                Point p1 = new Point(x2, y2 + 25);
                Point p2 = new Point(x2 - 5, y2 + 20);
                Point p3 = new Point(x2 - 5, y2 + 30);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1, y1, x2, y2 + 25);
                break;
            }
            case "Right": {
                Point p1 = new Point(x2 + 100, y2 + 25);
                Point p2 = new Point(x2 + 105, y2 + 20);
                Point p3 = new Point(x2 + 105, y2 + 30);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1, y1, x2, y2 + 25);

                break;
            }
        }

    }

    public void drawTriangleHead(Graphics g, int x1, int x2, int y1, int y2, String otherSide){
        Polygon arrowhead = new Polygon();
        switch (otherSide) {
            case "Up":
                arrowhead.addPoint(x2 + 50, y2);
                arrowhead.addPoint(x2 + 45, y2 - 10);
                arrowhead.addPoint(x2 + 55, y2 - 10);
                g.fillPolygon(arrowhead);
                g.drawLine(x1, y1, x2 + 50, y2 - 10);
            case "Down":
                arrowhead.addPoint(x2 + 50, y2 + 50);
                arrowhead.addPoint(x2 + 45, y2 + 60);
                arrowhead.addPoint(x2 + 55, y2 + 60);
                g.fillPolygon(arrowhead);
                g.drawLine(x1, y1, x2 + 50, y2 + 60);
            case "Left":
                arrowhead.addPoint(x2, y2 + 25);
                arrowhead.addPoint(x2 - 10, y2 + 20);
                arrowhead.addPoint(x2 - 10, y2 + 30);
                g.fillPolygon(arrowhead);
                g.drawLine(x1, y1, x2 - 10, y2 + 25);
            case "Right":
                arrowhead.addPoint(x2 + 100, y2 + 25);
                arrowhead.addPoint(x2 + 105, y2 + 20);
                arrowhead.addPoint(x2 + 105, y2 + 30);
                g.fillPolygon(arrowhead);
                g.drawLine(x1, y1, x2 + 100, y2 + 25);
        }
    }

    public void drawDiamond(Graphics g, int x1, int x2, int y1, int y2, String otherSide){
        Polygon diamond = new Polygon();
        switch (otherSide) {
            case "Up":
                diamond.addPoint(x1 - 5, y1 + 8);
                diamond.addPoint(x1, y1);
                diamond.addPoint(x1 + 5, y1 + 8);
                diamond.addPoint(x1, y1 + 16);
                g.drawPolygon(diamond);
                g.drawLine(x1, y1 + 16, x2 + 50, y2);

            case "Down":
                diamond.addPoint(x1 - 5, y1 - 8);
                diamond.addPoint(x1, y1);
                diamond.addPoint(x1 + 5, y1 - 8);
                diamond.addPoint(x1, y1 - 16);
                g.drawPolygon(diamond);
                g.drawLine(x1, y1 - 16, x2 + 50, y2 + 50);

            case "Left":
                diamond.addPoint(x1 + 8, y1 - 5);
                diamond.addPoint(x1, y1);
                diamond.addPoint(x1 + 8, y1 + 5);
                diamond.addPoint(x1 + 16, y1);
                g.drawPolygon(diamond);
                g.drawLine(x1 + 16, y1, x2, y2 + 25);

            case "Right":
                diamond.addPoint(x1 - 8, y1 - 5);
                diamond.addPoint(x1, y1);
                diamond.addPoint(x1 + 8, y1 + 5);
                diamond.addPoint(x1 - 16, y1);
                g.drawPolygon(diamond);
                g.drawLine(x1 - 16, y1, x2 + 100, y2 + 25);
        }
    }
    public void drawConnections(Graphics g){
        g.setColor(Color.BLACK);
        for(connectionRelationship c: connections){
            String connectType = c.getconnecType();
            String selfSide = c.getSide();
            String otherSide = getSide(c.getSecondBox(), c.getFirstBox());
            int x1 = c.getFirstBox().topLeftX;
            int y1 = c.getFirstBox().topLeftY;
            int x2 = c.getSecondBox().topLeftX;
            int y2 = c.getSecondBox().topLeftY;
            switch (selfSide) {
                case "Up":
                    switch (connectType) {
                        case "Association":
                            drawArrowHead(g, x1 + 50, x2, y1, y2, otherSide);
                            break;
                        case "Inheritance" :
                            drawTriangleHead(g, x1 + 50, x2, y1, y2, otherSide);
                            break;
                        case "Composition": drawDiamond(g, x1 + 50, x2, y1, y2, otherSide);
                    }
                    break;

                case "Down":
                    switch (connectType) {
                        case "Association":
                            drawArrowHead(g, x1 + 50, x2, y1 + 50, y2, otherSide);
                            break;
                        case "Inheritance":
                            drawTriangleHead(g, x1 + 50, x2, y1 + 50, y2, otherSide);
                            break;
                        case "Composition": drawDiamond(g, x1 + 50, x2, y1 + 50, y2, otherSide);
                    }
                    break;

                case "Left":
                    switch (connectType) {
                        case "Association":
                            drawArrowHead(g, x1, x2, y1 + 25, y2, otherSide);
                            break;
                        case "Inheritance":
                            drawTriangleHead(g, x1, x2, y1 + 25, y2, otherSide);
                            break;
                        case "Composition": drawDiamond(g, x1, x2, y1 + 25, y2, otherSide);
                    }
                    break;

                case "Right":
                    switch (connectType) {
                        case "Association":
                            drawArrowHead(g, x1 + 100, x2, y1 + 25, y2, otherSide);
                            break;
                        case "Inheritance":
                            drawTriangleHead(g, x1 + 50, x2, y1 + 25, y2, otherSide);
                            break;
                        case "Composition": drawDiamond(g, x1 + 100, x2, y1 + 25, y2, otherSide);
                    }
                    break;


            }
        }
    }
}