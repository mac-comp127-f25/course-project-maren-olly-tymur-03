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
    private GameBoard gameBoard;
    private Set<Key> previousKeys = new HashSet<>();

    public static void main(String[] args){
        new MainGame();
    }

    public MainGame() {
        CanvasWindow canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        canvas.setBackground(Color.GRAY);
        game = new GraphicsGroup();
        WallBlock wall = new WallBlock(90, 0, 30, 30);
        gameBoard = new GameBoard();
        slime = new Slime(new Point (0, 0), gameBoard);
        gameBoard.addWallBlockToGrid(wall.getXGridCellLocation(wall), wall.getYGridCellLocation(wall));
        game.add(slime.getGraphics());
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
            gameBoard.updateSlimeNeighbors(slime);
        }
        if (keys.contains(Key.RIGHT_ARROW) && !previousKeys.contains(Key.RIGHT_ARROW)) {
            slime.moveRightOnce();
            gameBoard.updateSlimeNeighbors(slime);
        }
        if (keys.contains(Key.UP_ARROW) && !previousKeys.contains(Key.UP_ARROW)) {
            slime.moveUpOnce();
            gameBoard.updateSlimeNeighbors(slime);
        }
        if (keys.contains(Key.DOWN_ARROW) && !previousKeys.contains(Key.DOWN_ARROW)) {
            slime.moveDownOnce();
            gameBoard.updateSlimeNeighbors(slime);
        }

        //gameBoard.updateSlimeNeighbors();

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

    public GameBoard getGameBoard() {
        return gameBoard;
    }

}
