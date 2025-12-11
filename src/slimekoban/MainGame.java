package slimekoban;

import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsGroup;

public class MainGame {
    public static final double CELL_SIZE = 30;
    private static final double CANVAS_HEIGHT = CELL_SIZE * 20;
    private static final double CANVAS_WIDTH = CELL_SIZE * 20;
    private Slime slime;
    private GraphicsGroup game;
    private CanvasWindow canvas;
    private GameBoard gameBoard;
    private Set<Key> previousKeys = new HashSet<>();
    // X = wallblock
    // [ ] = free space
    // c = crate
    private List<String> defaultMap = List.of(
        "XXXXXXXXXX XXXXXXXXX",
        "X    X   C X C     X",
        "X XXXX XXX X C X XXX",
        "X  X   X   X C X   X",
        "XX X XXX XXX C XXX X",
        "XX X X     X C X   X",
        "XX  CXXXXX   C X XXX",
        "XXXX X   XXXXXXX X X",
        "X  X X C  X    X X X",
        "X  XXX XXXX  X   X X",
        "X    C       X C X X",
        "XXXX X XXXXXXX XXX X",
        "X X  X       X X   X",
        "X X  XXXXX C XC  X X",
        "X    X   X   X  XX X",
        "X  XCXXX XXXXX   X X",
        "X  X         XXXXX X",
        "X XX XXXX      C   X",
        "X       X    C   C X",
        "XXXXXXXXX XXXXXXXXXX"
    );

    public static void main(String[] args){
        MainGame mainGame = new MainGame();
        mainGame.run();
    }

    public MainGame() {
        canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        canvas.setBackground(Color.GRAY);
        game = new GraphicsGroup();
        slime = new Slime(new Point (CELL_SIZE * 10, 0));
        gameBoard = new GameBoard(slime);
        game.add(slime.getGraphics());
        makeMaze(defaultMap);
        // initialize neighbor info after maze created
        gameBoard.updateCrateNeighbors();
        gameBoard.updateSlimeNeighbors();
        canvas.add(game); 
    }

    public void left() {
        if(slime.getLeftNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation() - 1, slime.getYGridCellLocation());
            if (crate != null) {
                int targetX = crate.getGridX() - 1;
                int targetY = crate.getGridY();
                // if crate would be pushed off the left edge -> reset
                if (targetX < 0) {
                    resetGame();
                } else if (gameBoard.isCellFree(targetX, targetY)) {
                    crate.moveLeftOnce();
                    gameBoard.updateCrateNeighbors();
                }
            }
        }
        // Ensure slime neighbor info reflects any crate movement
        gameBoard.updateSlimeNeighbors();
        slime.moveLeftOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void right() {
        if(slime.getRightNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation() + 1, slime.getYGridCellLocation());
            if (crate != null) {
                int targetX = crate.getGridX() + 1;
                int targetY = crate.getGridY();
                if (targetX >= (int)(MainGame.getCANVAS_WIDTH() / MainGame.CELL_SIZE)) {
                    resetGame();
                } else if (gameBoard.isCellFree(targetX, targetY)) {
                    crate.moveRightOnce();
                    gameBoard.updateCrateNeighbors();
                }
            }
        }
        // Ensure slime neighbor info reflects any crate movement
        gameBoard.updateSlimeNeighbors();
        slime.moveRightOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void up() {
        if(slime.getUpNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() - 1);
            if (crate != null) {
                int targetX = crate.getGridX();
                int targetY = crate.getGridY() - 1;
                if (targetY < 0) {
                    resetGame();
                } else if (gameBoard.isCellFree(targetX, targetY)) {
                    crate.moveUpOnce();
                    gameBoard.updateCrateNeighbors();
                }
            }
        }
        // Ensure slime neighbor info reflects any crate movement
        gameBoard.updateSlimeNeighbors();
        slime.moveUpOnce();
        gameBoard.updateSlimeNeighbors();
    }

    public void down() {
        if(slime.getDownNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() + 1);
            if (crate != null) {
                int targetX = crate.getGridX();
                int targetY = crate.getGridY() + 1;
                if (targetY >= (int)(MainGame.getCANVAS_HEIGHT() / MainGame.CELL_SIZE)) {
                    resetGame();
                } else if (gameBoard.isCellFree(targetX, targetY)) {
                    crate.moveDownOnce();
                    gameBoard.updateCrateNeighbors();
                }
            }
        }
        // Ensure slime neighbor info reflects any crate movement
        gameBoard.updateSlimeNeighbors();
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

    public void makeMaze(List<String> map) {
        for (int row = 0; row < map.size(); row++) {
            String rowData = map.get(row);
            for (int col = 0; col < rowData.length(); col++) {
                char c = rowData.charAt(col);
                if (c == 'X') {
                    WallBlock wall = new WallBlock(col, row);
                    gameBoard.addWallBlockToGrid(wall);
                    game.add(wall);
                } else if (c == 'C') {
                    Crate crate = new Crate(col, row, gameBoard);
                    gameBoard.addCrateToGrid(crate);
                    game.add(crate);
                }
            }
        }
    }

    public void resetGame() {
        game.removeAll();
        game = new GraphicsGroup();
        slime = new Slime(new Point (0, 0));
        gameBoard = new GameBoard(slime);
        game.add(slime.getGraphics());
        makeMaze(defaultMap);
    }
}
