

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Graphical representation of a class in UML format
 * 
 * @author Jacob Shapero
 * @author Lauren Allen
 * @author Javier Gonzalez Sanchez
 * @version 32.3
 */
public class ClassBox implements Serializable {
    protected String name;

    protected int type;
    private static final int TYPE_CLASS = 0;
    private static final int TYPE_INTERFACE = 1;

    private Point point;
    private int height, width;
    private ArrayList<String> methods;
    private ArrayList<String> variables;
    private boolean isSelected;
    private boolean isInterface;
    private final int varH = 20;

    ConnectionHandler connectionHandler = new ConnectionHandler();

    /**
     * getter for selection status
     *
     * @return
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * setter for selection status
     *
     * @param selected
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Checks if the passed coordinates are within the
     * boundries of the Box
     *
     * @param x int x axis
     * @param y int y axis
     *
     * @return if the passed coordinates are within the
     *         boundries of the Box
     */
    public boolean contains(int x, int y) {
        if (x > point.getX() && x < point.getX() + width &&
                y > point.getY() && y < point.getY() + height)
            return true;
        return false;
    }

    /**
     * Initializes the Box instance with a name and coordinates
     *
     * @param name String
     * @param x    int
     * @param y    int
     */
    public ClassBox(String name, int x, int y) {

        x = (x < 50) ? 50 : x - 50;
        y = (y < 25) ? 25 : y - 25;
        this.name = name;
        this.point = new Point(x, y);
        this.height = 50;
        this.width = 95 + 7 * (name.length());
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.isInterface = isInterface;
    }

    public boolean getInterface() {
        return isInterface;
    }

    /**
     * Draws the box on the passed Graphics panel
     *
     * @param g The Graphics panel
     */
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRect((int) getPoint().getX(), (int) getPoint().getY(), getWidth(), height);
        if (!isSelected) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect((int) getPoint().getX(), (int) getPoint().getY(), getWidth(), height);
        int hChange = 0;
        while ((hChange / varH) < variables.size()) {
            g.setColor(Color.GRAY);
            g.fillRect((int) getPoint().getX() + 5, (int) getPoint().getY() + hChange + (varH + 5), getWidth() - 10,
                    varH);
            g.setColor(Color.white);
            g.drawString(variables.get((hChange / varH)), (int) getPoint().getX() + 7,
                    (int) getPoint().getY() + hChange + (varH + 20));
            hChange += varH;
        }
        hChange += 5;
        int i = 0;
        while ((i) < methods.size()) {
            g.setColor(Color.GRAY);
            g.fillRect((int) getPoint().getX() + 5, (int) getPoint().getY() + hChange + (varH + 5), getWidth() - 10,
                    varH);
            g.setColor(Color.white);
            g.drawString(methods.get(i), (int) getPoint().getX() + 7, (int) getPoint().getY() + hChange + (varH + 20));
            hChange += varH;
            i += 1;
        }
        g.setColor(Color.BLACK);
        int w = g.getFontMetrics().stringWidth(getName());
        int xx = (int) getPoint().getX() + (getWidth() / 2) - w / 2;
        int yy = (int) (getPoint().getY() + 20);
        if (isInterface) {
            g.drawString("<<interface>>", (int) getPoint().getX() + (getWidth() / 2) - 50, yy - 10);
        }
        g.drawString(getName(), xx, yy);
    }

    public void addVar(String name) {
        variables.add(name);
        height += varH;

    }

    public void addMethod(String name) {
        methods.add(name);
        height += varH;
    }

    /**
     * Getter for box name
     *
     * @return String box name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for box name
     *
     * @param name
     */
    public void setName(String name) {
    }

    /**
     * getter for the coordinates
     *
     * @return
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Setter for the coordinates
     *
     * @param x
     * @param y
     */
    public void setPoint(int x, int y) {
        point = new Point(x - 50, y - 25);
    }

    /**
     * Getter for the width of the box
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Converts a ClassBox and connects to a string
     */
    public String toString() {

        String compString = "";
        String assocString = "";
        String inheritString = "";
        for (connectionRelationship c : this.connectionHandler.getConnections()) {
            if (c.getconnecType().equals("Association")) {
                assocString += "          " + c.getSecondBox().getName() + "\n";
            } else if (c.getconnecType().equals("Inheritance")) {
                inheritString += " extends " + c.getSecondBox().getName();
            } else if (c.getconnecType().equals("Composition")) {
                compString += "     " + c.getSecondBox().getName() + "\n";
            }
        }
        if (assocString != "") {
            assocString = "     " + "method() {" + "\n" + assocString + "     " + "}" + "\n";
        }
        String boxString = "Class " + name + inheritString + " {" + "\n";
        String mstring = "";
        String vstring = "";
        for (String method : methods) {
            System.out.println("meth");
            mstring += "     " + method + "()" + "{" + "\n" + "     " + "}" + "\n";
        }
        for (String var : variables) {
            System.out.println("var");
            vstring += "     " + var + "\n";
        }
        boxString += vstring;
        boxString += mstring;
        boxString += compString;
        boxString += assocString;
        boxString += "} \n\n";
        return boxString;
    }
}
