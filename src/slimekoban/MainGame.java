package slimekoban;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

public class MainGame {
    private final double CANVAS_HEIGHT = 600;
    private final double CANVAS_WIDTH = 600; 
    private Slime slime;
    private Crate crate;

    private CanvasWindow canvas;

    public static void main(String[] args){
        new MainGame();
    }

    public MainGame() {
        CanvasWindow canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        Slime slime = new Slime(new Point (50, 50));
        //Crate crate = new Crate(20, 20, new Point ());

    }


}
