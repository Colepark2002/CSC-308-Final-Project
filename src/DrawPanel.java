import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

/**
 * Represents the rightside panel which ClassBoxes are drawn on
 *
 * @author Jacob Shapero
 * @author Van Park
 * @version 1.1
 */
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener, Observer {
    Stack<ClassBox> stack = new Stack<>();
    private ClassBox clickedBox;
    private ClassBox previousSelection;

    private ClassBox checkCollisionWithBoxes(int x, int y) {
        for (ClassBox c : stack)
            if (c.contains(x, y))
                return c;
        return null;
    }

    private void showPopUpMenu(int x, int y) {
        JPopupMenu pm = new JPopupMenu();
        JMenuItem l1 = new JMenuItem("delete");
        JMenuItem l2 = new JMenuItem("rename");
        JMenuItem l3 = new JMenuItem("add var");
        JMenuItem l4 = new JMenuItem("add method");
        pm.add(l1);
        pm.add(l2);
        pm.add(l3);
        pm.add(l4);
        l1.addActionListener(this);
        l2.addActionListener(this);
        l3.addActionListener(this);
        l4.addActionListener(this);
        pm.show(this, x, y);
    }

    private void varMenu(ClassBox box) {
        String varNam = JOptionPane.showInputDialog("Please input variable name");
        box.addVar(varNam);
        repaint();
        Blackboard.getInstance().notifyObservers();
    }

    private void methodMenu(ClassBox box) {
        String methNam = JOptionPane.showInputDialog("Please input method name");
        box.addMethod(methNam);
        repaint();
        Blackboard.getInstance().notifyObservers();
    }

    private void createNewBox(int x, int y) {
        String name = JOptionPane.showInputDialog("Please input name");
        if (name != null) {
            ClassBox box = new ClassBox(name, x, y);
            stack.push(box);
        }
        this.repaint();
        Blackboard.getInstance().setStack(this.getStack());
    }

    public DrawPanel() {
        this.setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        Blackboard.getInstance().addObserver(this);
    }

    /**
     * Draws the various images stored on the stack.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ClassBox c : stack) {
            c.connectionHandler.updateSides();
            c.connectionHandler.drawConnections(g);
            c.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickedBox = checkCollisionWithBoxes(e.getX(), e.getY());
        if (clickedBox == null) {
            createNewBox(e.getX(), e.getY());
        } else if (previousSelection == null) {
            clickedBox.setSelected(true);
            clickedBox.connectionHandler.beginConnection(clickedBox, Blackboard.getInstance().getConnection());
            previousSelection = clickedBox;
        } else {
            previousSelection.connectionHandler.beginConnection(clickedBox, Blackboard.getInstance().getConnection());
            previousSelection = null;
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            showPopUpMenu(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickedBox != null) {
            clickedBox.setSelected(false);
            this.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (clickedBox != null) {
            clickedBox.connectionHandler.resetConnection();
            previousSelection = null;
            clickedBox.setPoint(e.getX(), e.getY());
        }
        this.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            // PLACEHOLDER
        } else if (e.getActionCommand().equals("rename")) {
            // PLACEHOLDER
        } else if (e.getActionCommand().equals("add var")) {
            // PLACEHOLDER
            varMenu(clickedBox);
        } else if (e.getActionCommand().equals("add method")) {
            // PLACEHOLDER
            methodMenu(clickedBox);
        }
    }

    public Stack<ClassBox> getStack() {
        return stack;
    }

    public void setStack(Stack<ClassBox> stack) {
        this.stack = stack;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void update(Observable o, Object arg) {
        this.stack = Blackboard.getInstance().getStack();
        this.repaint();
    }
}
