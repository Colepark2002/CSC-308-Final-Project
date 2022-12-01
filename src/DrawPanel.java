import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class DrawPanel extends JPanel implements Observer, MouseListener, MouseMotionListener
{
    Stack<ClassBox> stack = new Stack<ClassBox>();
    public DrawPanel(){
        this.setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    /**
     * Draws the various images stored on the stack.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(ClassBox box: stack){
        }
    }
    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ClassBox box = new ClassBox(e.getX(),e.getY());
        stack.push(box);
        this.add(box);
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

}
