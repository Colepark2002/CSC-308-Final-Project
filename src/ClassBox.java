import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Represents a class box to be placed on the DrawPanel
 * 
 * @author Jacob Shapero
 * @version 1.0
 */
public class ClassBox extends JPanel implements MouseListener, MouseMotionListener, Serializable {
    JTextField classname;
    int width = 100;
    int height = 50;
    int topLeftX, topLeftY;
    ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
    String connectType = "Association";

    public ClassBox(int mouseClickX, int mouseClickY) {
        GridLayout grid = new GridLayout(2, 1);
        this.setLayout(grid);
        topLeftX = mouseClickX - (width / 2);
        topLeftY = mouseClickY - (height / 2);
        this.setBounds(topLeftX, topLeftY, width, height);
        this.setBackground(Color.YELLOW);
        classname = new JTextField(JOptionPane.showInputDialog("Enter a name"));
        classname.setBackground(Color.yellow);
        classname.setEditable(false);
        classname.setHorizontalAlignment(JTextField.CENTER);
        this.add(classname);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public String getClassName() {
        return classname.getText();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
        connectType = Blackboard.getInstance().getConnection();
        System.out.println(connectType);
        connectionHandler.beginConnection(this, connectType);
        Blackboard.getInstance().getDp().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        repaint();
        connectionHandler.updateSides();
        Blackboard.getInstance().getDp().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int ex = e.getX() - 50;
        int ey = e.getY() - 25;
        this.setBounds(topLeftX + 50 + ex, topLeftY + 25 + ey, width, height);
        topLeftX = topLeftX + ex + 50;
        topLeftY = topLeftY + ey + 25;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public String toString() {
        ArrayList<connectionRelationship> connections = connectionHandler.getInstance().connections;
        String compString = "";
        String assocString = "";
        String inheritString = "";
        for (connectionRelationship c : connections) {
            if (c.getFirstBox().equals(this)) {
                if (c.getconnecType().equals("Association")) {
                    assocString += "          " + c.getSecondBox().getClassName() + "\n";
                } else if (c.getconnecType().equals("Inheritance")) {
                    inheritString += " extends " + c.getSecondBox().getClassName();
                } else if (c.getconnecType().equals("Composition")) {
                    compString += "     " + c.getSecondBox().getClassName() + "\n";
                }
            }
        }
        if (assocString != "") {
            assocString = "     " + "method() {" + "\n" + assocString + "     " + "}" + "\n";
        }
        String boxString = "Class " + classname.getText() + inheritString + " {" + "\n";
        boxString += compString;
        boxString += assocString;
        boxString += "} \n\n";
        return boxString;
    }

}
