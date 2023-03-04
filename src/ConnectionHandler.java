import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * The handler that deals with all of the connections made between classes.
 * 
 * @author Van Park
 * @version 1.0
 */

public class ConnectionHandler implements Serializable {

    DrawPanel drawPanel;
    public ClassBox connectionBox1 = null;
    public ClassBox connectionBox2 = null;
    ArrayList<connectionRelationship> connections = new ArrayList<connectionRelationship>();

    public ConnectionHandler() {
    }

    /**
     * Gets the appropriate side for the connection to come out of
     * 
     * @param box1 the ClassBox that is making the connection
     * @param box2 the ClassBox that is getting connected
     * @return the appropriate side for the connection
     */
    private String getSide(ClassBox box1, ClassBox box2) {
        String side = "";
        int xdif = box1.getPoint().x - box2.getPoint().x;
        int ydif = box1.getPoint().y - box2.getPoint().y;
        int xCenterAvg = (((box1.getWidth() / 2) + box2.getWidth()) / 2);
        if (-xCenterAvg < xdif && xdif < xCenterAvg) {
            if (ydif > 0) {
                side = "Up";
            }
            if (ydif < 0) {
                side = "Down";
            }
        } else if (xdif < -xCenterAvg) {
            side = "Right";
        } else {
            side = "Left";
        }
        return side;
    }

    public void setDrawPanel(DrawPanel d) {
        drawPanel = d;
    }

    public void beginConnection(ClassBox box, String connectType) {
        if (connectionBox1 == null) {
            connectionBox1 = box;

        } else if (connectionBox2 == null && connectionBox1 != box) {
            connectionBox2 = box;
            String side = getSide(connectionBox1, connectionBox2);
            connections.add(new connectionRelationship(connectionBox1, connectionBox2, connectType, side));
            Blackboard.getInstance().hasChanged();
            Blackboard.getInstance().notifyObservers();
            connectionBox1 = null;
            connectionBox2 = null;
        }
    }

    public void resetConnection() {
        connectionBox1 = null;
    }

    /**
     * Updates the connections when a box is moved.
     */
    public void updateSides() {
        for (connectionRelationship c : connections) {
            c.setSide(getSide(c.getFirstBox(), c.getSecondBox()));
        }
    }

    /**
     * Draws the association connection between classes
     * 
     * @param g         the graphics necessary to draw on the panel
     * @param otherSide the side in which the connection is made for the second
     *                  ClassBox
     */
    public void drawArrowHead(Graphics g, String otherSide, ClassBox c1, ClassBox c2) {
        int xCenter1 = c1.getWidth() / 2;
        int yCenter1 = c1.getHeight() / 2;
        int xCenter2 = c2.getWidth() / 2;
        int yCenter2 = c2.getHeight() / 2;

        int x1 = c1.getPoint().x;
        int y1 = c1.getPoint().y;
        int x2 = c2.getPoint().x;
        int y2 = c2.getPoint().y;

        switch (otherSide) {
            case "Up": {
                Point p1 = new Point(x2 + xCenter2, y2);
                Point p2 = new Point(x2 + xCenter2 - 5, y2 - 10);
                Point p3 = new Point(x2 + xCenter2 + 5, y2 - 10);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 + xCenter2, y2 - 10);
                break;
            }
            case "Down": {
                Point p1 = new Point(x2 + xCenter2, y2 + (yCenter2 * 2));
                Point p2 = new Point(x2 + xCenter2 - 5, y2 + (yCenter2 * 2) + 10);
                Point p3 = new Point(x2 + xCenter2 + 5, y2 + (yCenter2 * 2) + 10);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 + xCenter2, y2 + (yCenter2 * 2) + 10);

                break;
            }
            case "Left": {
                Point p1 = new Point(x2, y2 + yCenter2);
                Point p2 = new Point(x2 - 10, y2 + yCenter2 - 5);
                Point p3 = new Point(x2 - 10, y2 + yCenter2 + 5);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 - 10, y2 + yCenter2);

                break;
            }
            case "Right": {
                Point p1 = new Point(x2 + (xCenter2 * 2), y2 + yCenter2);
                Point p2 = new Point(x2 + (xCenter2 * 2) + 10, y2 + yCenter2 - 5);
                Point p3 = new Point(x2 + (xCenter2 * 2) + 10, y2 + yCenter2 + 5);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                g.drawLine(p1.x, p1.y, p3.x, p3.y);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 + (xCenter2 * 2) + 10, y2 + yCenter2);

                break;
            }
        }

    }

    /**
     * Draws the inheritance connection between classes
     * 
     * @param g         the graphics necessary to draw on the panel
     * @param otherSide the side in which the connection is made for the second
     *                  ClassBox
     */
    public void drawTriangleHead(Graphics g, String otherSide, ClassBox c1, ClassBox c2) {
        Polygon arrowhead = new Polygon();
        int xCenter1 = c1.getWidth() / 2;
        int yCenter1 = c1.getHeight() / 2;
        int xCenter2 = c2.getWidth() / 2;
        int yCenter2 = c2.getHeight() / 2;

        int x1 = c1.getPoint().x;
        int y1 = c1.getPoint().y;
        int x2 = c2.getPoint().x;
        int y2 = c2.getPoint().y;

        switch (otherSide) {
            case "Up": {
                arrowhead.addPoint(x2 + xCenter2, y2);
                arrowhead.addPoint(x2 + xCenter2 - 5, y2 - 10);
                arrowhead.addPoint(x2 + xCenter2 + 5, y2 - 10);
                g.fillPolygon(arrowhead);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 + xCenter2, y2 - 10);
                break;
            }
            case "Down": {
                arrowhead.addPoint(x2 + xCenter2, y2 + (yCenter2 * 2));
                arrowhead.addPoint(x2 + xCenter2 - 5, y2 + (yCenter2 * 2) + 10);
                arrowhead.addPoint(x2 + xCenter2 + 5, y2 + (yCenter2 * 2) + 10);
                g.fillPolygon(arrowhead);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 + xCenter2, y2 + (yCenter2 * 2) + 10);
                break;
            }
            case "Left": {
                arrowhead.addPoint(x2, y2 + yCenter2);
                arrowhead.addPoint(x2 - 10, y2 + yCenter2 - 5);
                arrowhead.addPoint(x2 - 10, y2 + yCenter2 + 5);
                g.fillPolygon(arrowhead);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 - 10, y2 + yCenter2);
                break;
            }
            case "Right": {
                arrowhead.addPoint(x2 + (xCenter2 * 2), y2 + yCenter2);
                arrowhead.addPoint(x2 + (xCenter2 * 2) + 10, y2 + yCenter2 - 5);
                arrowhead.addPoint(x2 + (xCenter2 * 2) + 10, y2 + yCenter2 + 5);
                g.fillPolygon(arrowhead);
                g.drawLine(x1 + xCenter1, y1 + yCenter1, x2 + (xCenter2 * 2) + 10, y2 + yCenter2);
            }
        }
    }

    /**
     * Draws the composition connection between classes
     * 
     * @param g        the graphics necessary to draw on the panel
     * @param selfSide the side in which the connection is made for the first
     *                 ClassBox
     */
    public void drawDiamond(Graphics g, String selfSide, ClassBox c1, ClassBox c2) {
        Polygon diamond = new Polygon();
        int xCenter1 = c1.getWidth() / 2;
        int yCenter1 = c1.getHeight() / 2;
        int xCenter2 = c2.getWidth() / 2;
        int yCenter2 = c2.getHeight() / 2;

        int x1 = c1.getPoint().x;
        int y1 = c1.getPoint().y;
        int x2 = c2.getPoint().x;
        int y2 = c2.getPoint().y;

        switch (selfSide) {
            case "Up":
                diamond.addPoint(x1 + xCenter1, y1);
                diamond.addPoint(x1 + xCenter1 + 8, y1 - 8);
                diamond.addPoint(x1 + xCenter1, y1 - 16);
                diamond.addPoint(x1 + xCenter1 - 8, y1 - 8);
                g.fillPolygon(diamond);
                g.drawLine(x1 + xCenter1, y1 - 16, x2 + xCenter2, y2 + yCenter2);
                break;

            case "Down":
                diamond.addPoint(x1 + xCenter1, y1 + (yCenter1 * 2));
                diamond.addPoint(x1 + xCenter1 + 8, y1 + (yCenter1 * 2) + 8);
                diamond.addPoint(x1 + xCenter1, y1 + (yCenter1 * 2) + 16);
                diamond.addPoint(x1 + xCenter1 - 8, y1 + (yCenter1 * 2) + 8);
                g.fillPolygon(diamond);
                g.drawLine(x1 + xCenter1, y1 + (yCenter1 * 2) + 16, x2 + xCenter2, y2 + yCenter2);
                break;

            case "Left":
                diamond.addPoint(x1, y1 + yCenter1);
                diamond.addPoint(x1 - 8, y1 + yCenter1 + 8);
                diamond.addPoint(x1 - 16, y1 + yCenter1);
                diamond.addPoint(x1 + -8, y1 + yCenter1 - 8);
                g.fillPolygon(diamond);
                g.drawLine(x1 - 16, y1 + yCenter1, x2 + xCenter2, y2 + yCenter2);
                break;

            case "Right":
                diamond.addPoint(x1 + (xCenter1 * 2), y1 + yCenter1);
                diamond.addPoint(x1 + (xCenter1 * 2) + 8, y1 + yCenter1 + 8);
                diamond.addPoint(x1 + (xCenter1 * 2) + 16, y1 + yCenter1);
                diamond.addPoint(x1 + (xCenter1 * 2) + 8, y1 + yCenter1 - 8);
                g.fillPolygon(diamond);
                g.drawLine(x1 + (xCenter1 * 2) + 16, y1 + yCenter1, x2 + xCenter2, y2 + yCenter2);
                break;
        }
    }

    public void drawConnections(Graphics g) {
        g.setColor(Color.BLACK);
        for (connectionRelationship c : connections) {
            String connectType = c.getconnecType();
            String selfSide = c.getSide();
            String otherSide = getSide(c.getSecondBox(), c.getFirstBox());

            switch (connectType) {
                case "Association":
                    drawArrowHead(g, otherSide, c.getFirstBox(), c.getSecondBox());
                    break;
                case "Inheritance":
                    drawTriangleHead(g, otherSide, c.getFirstBox(), c.getSecondBox());
                    break;
                case "Composition":
                    drawDiamond(g, selfSide, c.getFirstBox(), c.getSecondBox());
            }

        }
    }

    public ArrayList<connectionRelationship> getConnections() {
        return connections;
    }
}