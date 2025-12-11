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
    private GraphicsGroup game;
    private CanvasWindow canvas;
    private GameBoard gameBoard;
    private Set<Key> previousKeys = new HashSet<>();

    public static void main(String[] args){
        MainGame mainGame = new MainGame();
        mainGame.run();
    }

    public MainGame() {
        canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        canvas.setBackground(Color.GRAY);
        game = new GraphicsGroup();
        slime = new Slime(new Point (0, 0));
        gameBoard = new GameBoard(slime);
        game.add(slime.getGraphics());
        makeMaze();
        canvas.add(game); 
    }

    public void left() {
        if(slime.getLeftNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation() - 1, slime.getYGridCellLocation());
            if(crate != null && crate.getLeftNeighbor() != null) {
                if(crate.getXCrateLocation() == 0) {
                    resetGame();
                }
                crate.moveLeftOnce();
            }
            gameBoard.updateCrateNeighbors();
        }
        slime.moveLeftOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void right() {
        if(slime.getRightNeighbor() == 2) {
            if(gameBoard.getCrateAt(slime.getXGridCellLocation() + 1, slime.getYGridCellLocation()).getRightNeighbor() != null) {
                gameBoard.getCrateAt(slime.getXGridCellLocation() + 1, slime.getYGridCellLocation()).moveRightOnce();
            }
            gameBoard.updateCrateNeighbors();
        }
        slime.moveRightOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void up() {
        if(slime.getUpNeighbor() == 2) {
            if(gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() - 1).getUpNeighbor() != null) {
                gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() - 1).moveUpOnce();
            }
            gameBoard.updateCrateNeighbors();
        }
        slime.moveUpOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void down() {
        if(slime.getDownNeighbor() == 2) {
            if(gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() + 1).getDownNeighbor() != null) {
                gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() + 1).moveDownOnce();
            }
            gameBoard.updateCrateNeighbors();
        }
        slime.moveDownOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void run() {
        canvas.animate(() -> { // closure that runs the game loop
        // slime movement controls: trigger one move per key press (no holding)
        Set<Key> keys = canvas.getKeysPressed();
        if (keys.contains(Key.LEFT_ARROW) && !previousKeys.contains(Key.LEFT_ARROW)) {
            left();
        }
        if (keys.contains(Key.RIGHT_ARROW) && !previousKeys.contains(Key.RIGHT_ARROW)) {
            right();
        }
        if (keys.contains(Key.UP_ARROW) && !previousKeys.contains(Key.UP_ARROW)) {
            up();
        }
        if (keys.contains(Key.DOWN_ARROW) && !previousKeys.contains(Key.DOWN_ARROW)) {
            down();
        }
        // update previousKeys for edge detection
        previousKeys.clear();
        previousKeys.addAll(keys);
        });
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
    
    public void makeMaze() {
        WallBlock wall = new WallBlock(90, 0, 30, 30);
        gameBoard.addWallBlockToGrid(wall.getXGridCellLocation(), wall.getYGridCellLocation(), wall);
        game.add(wall);
        Crate crate = new Crate(30, 30, new Point (90,30), gameBoard);
        gameBoard.addCrateToGrid(crate.getXCrateLocation(), crate.getYCrateLocation(), crate);  
        //OLLY ADD STUFF HERE PLZ :)
        game.add(crate);
    }

    public void resetGame() {
        game.removeAll();
        game = new GraphicsGroup();
        slime = new Slime(new Point (0, 0));
        gameBoard = new GameBoard(slime);
        game.add(slime.getGraphics());
        makeMaze();
    }
}
