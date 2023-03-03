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
        Blackboard.getInstance().addObserver(this);
    }

    /**
     * Draws the various images stored on the stack.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ClassBox c : stack){
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
        clickedBox = checkCollisionWithBoxes (e.getX(), e.getY());
        if (clickedBox==null) {
            createNewBox(e.getX(), e.getY());
        }
        else if (previousSelection == null) {
            clickedBox.setSelected(true);
            clickedBox.connectionHandler.beginConnection(clickedBox, Blackboard.getInstance().getConnection());
            previousSelection = clickedBox;
        }
        else {
            previousSelection.connectionHandler.beginConnection(clickedBox, Blackboard.getInstance().getConnection());
            previousSelection = null;
        }
            if (SwingUtilities.isRightMouseButton(e)){
                showPopUpMenu (e.getX(), e.getY());
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
            clickedBox.connectionHandler.resetConnection();
            previousSelection = null;
            clickedBox.setPoint(e.getX(), e.getY());
        }
        this.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            System.out.println(problemCompare());
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

    @Override
    public void update(Observable o, Object arg) {
        this.stack = Blackboard.getInstance().getStack();
        this.repaint();
    }



    public Problem SussyProblemCreator(){
        Stack<ClassBox> probStack = new Stack<ClassBox>();
        ArrayList<connectionRelationship> probConnections = new ArrayList<connectionRelationship>();

        ClassBox probBox1 = new ClassBox("A", 0, 0);
        ClassBox probBox2 = new ClassBox("B", 0, 0);
        ClassBox probBox3 = new ClassBox("C", 0, 0);
        ClassBox probBox4 = new ClassBox("D", 0, 0);

        probStack.add(probBox1);
        probStack.add(probBox2);
        probStack.add(probBox3);
        probStack.add(probBox4);

        Problem testProblem = new Problem();
        testProblem.initProblemStack(probStack);
        testProblem.initProblemString("");

        return testProblem;
    }
    public Boolean problemCompare(){
        Problem x = SussyProblemCreator();
        Boolean matching = true;
        if(stack.size() != x.getUML().size()){
            return false;
        }
        for(ClassBox p: stack){
            boolean nameFound = false;
            for(ClassBox pp: x.getUML()){
                if (p.getName().equals(pp.getName())){
                    nameFound = true;
                }
            }
            matching = nameFound;
        }
        return matching;
    }




}
