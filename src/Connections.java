import java.awt.*;

public class Connections {

    private int length;
    private String flag;

    private String getSide(int x1, int x2, int y1, int y2){
        String side = "";
        int xdif = x1 - x2;
        int ydif = y1 - y2;
        if (-85 < xdif && xdif < 85){
            if (ydif > 0){
                side = "Up";
                System.out.println(side);
            }
            if (ydif < 0){
                side = "Down";
                System.out.println(side);
            }
        }
        else if (xdif < -85){
            side = "Right";
            System.out.println(side);
        }
        else{
            side = "Left";
            System.out.println(side);
            }
        return side;
    }

    public void drawConnection(Graphics g){
        Blackboard objects = Blackboard.getInstance();
        Node box = objects.boxes;
        While (box.next != null ){
            String side = getSide(box.item.getx, box.next.getx, box.item.gety, box.next.gety);
            switch (flag) {
                case ("Association"):
                    if (side.equals("Up")) {

                    }
                case ("Inheritance"):
                    if (side.equals("Up")) {

                    }
                case ("Composition"):
                    if (side.equals("Up")) {

                    }
            }
        }
    }
}

