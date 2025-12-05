package slimekoban;

import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;

public class MainGame {
    private static final double CANVAS_HEIGHT = 600;
    private static final double CANVAS_WIDTH = 600; 
    private Slime slime;
    private Crate crate;
    private GraphicsGroup game;
    private CanvasWindow canvas;
    private Set<Key> previousKeys = new HashSet<>();

    public static void main(String[] args){
        new MainGame();
    }

    public MainGame() {
        CanvasWindow canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        canvas.setBackground(Color.GRAY);
        game = new GraphicsGroup();
        Slime slime = new Slime(new Point (0, 0));
        game.add(slime.getGraphics());
        WallBlock wall = new WallBlock(90, 0, 30, 30);
        game.add(wall);
        canvas.add(game);
        //Crate crate = new Crate(20, 20, new Point ());

        canvas.animate(() -> { // closure that runs the game loop

        // slime movement controls: trigger one move per key press (no holding)
        Set<Key> keys = canvas.getKeysPressed();

        // the conditional checks if the key is currently pressed 
        // and wasn't pressed in the previous frame

        if (keys.contains(Key.LEFT_ARROW) && !previousKeys.contains(Key.LEFT_ARROW)) {
            slime.moveLeftOnce();
        }
        if (keys.contains(Key.RIGHT_ARROW) && !previousKeys.contains(Key.RIGHT_ARROW)) {
            slime.moveRightOnce();
        }
        if (keys.contains(Key.UP_ARROW) && !previousKeys.contains(Key.UP_ARROW)) {
            slime.moveUpOnce();
        }
        if (keys.contains(Key.DOWN_ARROW) && !previousKeys.contains(Key.DOWN_ARROW)) {
            slime.moveDownOnce();
        }

        // update previousKeys for edge detection
        previousKeys.clear();
        previousKeys.addAll(keys);
        });
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
