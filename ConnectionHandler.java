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

    public void beginConnection(ClassBox box){
        if(connectionBox1 == null){
            connectionBox1 = box;

        }
        else if(connectionBox2 == null){
            connectionBox2 = box;
            connections.add(new connectionRelationship(connectionBox1, connectionBox2, "ASSOCIATION"));
            drawConnections();
            System.out.println(connections);
            connectionBox1 = null;
            connectionBox2 = null;
        }
    }
    public void drawConnections(){
        for(connectionRelationship c: connections){
            if(c.getConnection().equals("ASSOCIATION")){
            }
            else if(c.getConnection().equals("INHERITANCE")){

            }
            else if(c.getConnection().equals("IMPLIMENTATION")){

            }
            else if(c.getConnection().equals("DEPENDANCY")){

            }
            else if(c.getConnection().equals("AGGREGATION")){

            }
            else if(c.getConnection().equals("COMPOSITION")){

            }
        }
    }
}