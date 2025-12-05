package slimekoban;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;

public class MainGame {
    private static final double CANVAS_HEIGHT = 800;
    private static final double CANVAS_WIDTH = 800; 
    private Slime slime;
    private Crate crate;
    private GraphicsGroup game;
    private CanvasWindow canvas;

    public static void main(String[] args){
        new MainGame();
    }

    public MainGame() {
        CanvasWindow canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        canvas.setBackground(Color.GRAY);
        game = new GraphicsGroup();
        Slime slime = new Slime(new Point (0, 0));
        game.add(slime.getGraphics());
        canvas.add(game);
        //Crate crate = new Crate(20, 20, new Point ());
    }

    public void run() {
        
    }

    public static double getCANVAS_HEIGHT() {
        return CANVAS_HEIGHT;
    }

    public static double getCANVAS_WIDTH() {
        return CANVAS_WIDTH;
    }

}
