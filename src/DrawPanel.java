import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    Stack<ClassBox> stack = new Stack<ClassBox>();
    private ClassBox clickedBox;

    private ClassBox checkCollisionWithBoxes(int x, int y) {
        for(ClassBox c : stack)
            if (c.contains(x, y))
                return c;
        return null;
    }

    private void showPopUpMenu (int x, int y) {
        JPopupMenu pm = new JPopupMenu();
        JMenuItem l1 = new JMenuItem("delete");
        JMenuItem l2 = new JMenuItem("rename");
        pm.add(l1);
        pm.add(l2);
        l1.addActionListener(this);
        l2.addActionListener(this);
        pm.show(this, x, y);
    }

    private void createNewBox (int x, int y) {
        String name = JOptionPane.showInputDialog("Please input name");
        if(name != null){
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
        ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
        connectionHandler.setDrawPanel(this);
    }

    /**
     * Draws the various images stored on the stack.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ClassBox c : stack){
            c.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickedBox = checkCollisionWithBoxes (e.getX(), e.getY());
        if (clickedBox==null) {
            createNewBox(e.getX(), e.getY());
        } else {
            clickedBox.setSelected(true);
            if (SwingUtilities.isRightMouseButton(e)){
                showPopUpMenu (e.getX(), e.getY());
            } else {
                // something will happen here ...

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (clickedBox!=null) {clickedBox.setSelected(false);
            this.repaint();}
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(clickedBox != null){
            clickedBox.setPoint(e.getX(), e.getY());
        }
        this.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            //PLACEHOLDER
        } else if (e.getActionCommand().equals("rename")) {
            //PLACEHOLDER
        }
    }

    public Stack<ClassBox> getStack() {
        return stack;
    }

    public void setStack(Stack<ClassBox> stack) {
        this.stack = stack;
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
