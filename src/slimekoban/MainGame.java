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
        slime = new Slime(new Point (0, 0), gameBoard);
        gameBoard = new GameBoard(slime);
        gameBoard.addWallBlockToGrid(wall.getXGridCellLocation(), wall.getYGridCellLocation(), wall);
        game.add(slime.getGraphics());
        game.add(wall);
        crate = new Crate(30, 30, new Point (90,30), gameBoard);
        gameBoard.addCrateToGrid(crate.getXCrateLocation(), crate.getYCrateLocation(), crate);  
        game.add(crate);
        canvas.add(game);
        canvas.animate(() -> { // closure that runs the game loop

        // slime movement controls: trigger one move per key press (no holding)
        Set<Key> keys = canvas.getKeysPressed();

        // the conditional checks if the key is currently pressed 
        // and wasn't pressed in the previous frame

        //COMMENTED UNTIL I FINISH GAMEBORD
        if (keys.contains(Key.LEFT_ARROW) && !previousKeys.contains(Key.LEFT_ARROW)) {
            if(slime.getLeftNeighbor() == 2) {
                if(gameBoard.getCrateAt(slime.getXGridCellLocation() - 1, slime.getYGridCellLocation()).getLeftNeighbor() != null) {
                    // System.out.println(gameBoard.getCells());
                    gameBoard.getCrateAt(slime.getXGridCellLocation() - 1, slime.getYGridCellLocation()).moveLeftOnce();
                    // System.out.println(gameBoard.getCells());
                }
                // gameBoard.getCrateAt(slime.getXGridCellLocation() - 1, slime.getYGridCellLocation()).moveLeftOnce();
                gameBoard.updateCrateNeighbors();
            }
            slime.moveLeftOnce();
            gameBoard.updateSlimeNeighbors();
        }
        if (keys.contains(Key.RIGHT_ARROW) && !previousKeys.contains(Key.RIGHT_ARROW)) {
            if(slime.getRightNeighbor() == 2) {
                if(gameBoard.getCrateAt(slime.getXGridCellLocation() + 1, slime.getYGridCellLocation()).getRightNeighbor() != null) {
                    gameBoard.getCrateAt(slime.getXGridCellLocation() + 1, slime.getYGridCellLocation()).moveRightOnce();
                }
                gameBoard.updateCrateNeighbors();
            }
            slime.moveRightOnce();
            gameBoard.updateSlimeNeighbors();
        }
        if (keys.contains(Key.UP_ARROW) && !previousKeys.contains(Key.UP_ARROW)) {
            if(slime.getUpNeighbor() == 2) {
                if(gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() - 1).getUpNeighbor() != null) {
                    gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() - 1).moveUpOnce();
                }
                gameBoard.updateCrateNeighbors();
            }
            slime.moveUpOnce();
            gameBoard.updateSlimeNeighbors();
        }
        if (keys.contains(Key.DOWN_ARROW) && !previousKeys.contains(Key.DOWN_ARROW)) {
            if(slime.getDownNeighbor() == 2) {
                if(gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() + 1).getDownNeighbor() != null) {
                    gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() + 1).moveDownOnce();
                }
                gameBoard.updateCrateNeighbors();
            }
            slime.moveDownOnce();
            gameBoard.updateSlimeNeighbors();
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

    // public Crate getCrate() {
    //     return crate;
    // }

}
