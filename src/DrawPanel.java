import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 * Represents the rightside panel which ClassBoxes are drawn on
 *
 * @author Jacob Shapero
 * @author Van Park
 * @version 1.0
 */
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    Stack<ClassBox> stack = new Stack<ClassBox>();

    public DrawPanel() {
        this.setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
        connectionHandler.setDrawPanel(this);
    }

    /**
     * Draws the various images stored on the stack.
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (ClassBox box : stack) {
            box.connectionHandler.drawConnections(g);
        }
        Blackboard.getInstance().setDp(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        ClassBox box = new ClassBox(e.getX(), e.getY());
        stack.push(box);
        this.add(box);
        Blackboard.getInstance().notifyObservers();
        super.repaint();
        super.revalidate();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Stack<ClassBox> getStack() {
        return stack;
    }

    public void setStack(Stack<ClassBox> stack) {
        this.stack = stack;
    }

}
