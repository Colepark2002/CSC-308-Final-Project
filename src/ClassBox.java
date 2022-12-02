import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Represents a class box to be placed on the DrawPanel
 * @author Jacob Shapero
 * @version 1.0
 */
public class ClassBox extends JPanel implements MouseListener, MouseMotionListener {
    JTextField classname;
    int width = 100;
    int height = 50;
    int topLeftX, topLeftY;
    ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
    String connectType = "Association";
    Graphics panelGraphics;
    public ClassBox(int mouseClickX, int mouseClickY, Graphics panelGraphics){
        GridLayout grid = new GridLayout(2, 1);
        this.setLayout(grid);
        topLeftX = mouseClickX-(width/2);
        topLeftY = mouseClickY-(height/2);
        this.setBounds(topLeftX, topLeftY, width, height);
        this.setBackground(Color.YELLOW);
        classname = new JTextField(JOptionPane.showInputDialog("Enter a name"));
        classname.setBackground(Color.yellow);
        classname.setEditable(false);
        classname.setHorizontalAlignment(JTextField.CENTER);
        this.add(classname);
        this.panelGraphics = panelGraphics;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public String getClassName(){
        return classname.getText();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
        connectionHandler.beginConnection(this, connectType);
        connectionHandler.drawConnections(panelGraphics);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        repaint();
        connectionHandler.updateSides();
        connectionHandler.drawConnections(panelGraphics);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int ex = e.getX()-50;
        int ey = e.getY()-25;
        this.setBounds(topLeftX+50+ex, topLeftY+25+ey,width,height);
        topLeftX = topLeftX+ex + 50;
        topLeftY = topLeftY+ey + 25;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}


