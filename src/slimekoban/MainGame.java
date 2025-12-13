package slimekoban;

import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

public class MainGame {
    public static final double CELL_SIZE = 30;
    public static final double CANVAS_HEIGHT = CELL_SIZE * 20;
    public static final double CANVAS_WIDTH = CELL_SIZE * 20;
    private Color backgroundColor = new Color(221, 200, 140);
    private Slime slime;
    private GraphicsGroup game;
    private CanvasWindow canvas;
    private GameBoard gameBoard;
    private Set<Key> previousKeys = new HashSet<>();
    private int winX;
    private int winY;
    private int levelIndex;
    // maze map legend: 
        // X = wallblock
        // [ ] = free space
        // C = crate
        // w = victory position
        // s = starting position
    private List<List<String>> levels = List.of(
        List.of(
            "XXXXXXXXXXsXXXXXXXXX",
            "X    X   C X C     X",
            "X XXXX XXX X C X XXX",
            "X  X   X   X C X   X",
            "XX X XXX XXX C XXX X",
            "XX X X     X C X   X",
            "XX  CXXXXX   C XCXXX",
            "XXXX X   XXXXXXX X X",
            "X  X X C  X  C X X X",
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
            "XXXXXXXXXwXXXXXXXXXX"
        ),
        List.of(
            "XXXXXXXXXXsXXXXXXXXX",
            "X X   X X  X C     X",
            "X X X   C XX XXXXXXX",
            "X X XX       X     X",
            "X X  XCCXXXX X  XX X",
            "X XX X    XX X C X X",
            "XC   X  X  X XXX X X",
            "XXXXXXXXX XX X   X X",
            "X         XX X XXX X",
            "X XXXXX XXXX X X  CX",
            "X     X C  X   X   X",
            "XXCXX XXXXXXXXXXXX X",
            "X   X      X       X",
            "X   XXXXC  XXXXXXXXX",
            "X X   X   X C   X  X",
            "X XXX X XXX X   X  X",
            "X X   C   C  X C   X",
            "X XXXXXXC XXXXXX X X",
            "X     C   X      X X",
            "XXXXXXXXXXXwXXXXXXXX"            
        )
    );

    public static void main(String[] args){
        MainGame mainGame = new MainGame(0);
        mainGame.run();
    }

    /**
     * Instatiates all game elements (slime, crates, walls, maze).
     * @param levelIndex Indicates which maze level of the game will be generated.
     */
    public MainGame(int levelIndex) {
        this.levelIndex = levelIndex;
        canvas = new CanvasWindow("Slimekoban!", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        canvas.setBackground(backgroundColor);
        game = new GraphicsGroup();
        slime = new Slime(new Point (CELL_SIZE * 10, 0));
        gameBoard = new GameBoard(slime);
        game.add(slime.getGraphics());
        makeMaze(levels.get(levelIndex));
        // initialize neighbor info after maze created
        gameBoard.updateCrateNeighbors();
        gameBoard.updateSlimeNeighbors();
        canvas.add(game); 
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Moves crate left if slime is pushing it from the left. Moves slime left once. Updates both 
     * slime and crate neighbors. Checks if slime is in winning position.
     */
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
        win();
    }

    /**
     * Moves crate right if slime is pushing it from the right. Moves slime right once. Updates both 
     * slime and crate neighbors. Checks if slime is in winning position.
     */
    public void right() {
        if(slime.getRightNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation() + 1, slime.getYGridCellLocation());
            if (crate != null) {
                int targetX = crate.getGridX() + 1;
                int targetY = crate.getGridY();
                if (targetX >= (int) CANVAS_WIDTH / MainGame.CELL_SIZE) {
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
        win();
    }

    /**
     * Moves crate up if slime is pushing it from the bottom. Moves slime rup once. Updates both 
     * slime and crate neighbors. Checks if slime is in winning position.
     */
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
        win();
    }

    /**
     * Moves crate down if slime is pushing it from above. Moves slime down once. Updates both 
     * slime and crate neighbors. Checks if slime is in winning position.
     */
    public void down() {
        if(slime.getDownNeighbor() == 2) {
            Crate crate = gameBoard.getCrateAt(slime.getXGridCellLocation(), slime.getYGridCellLocation() + 1);
            if (crate != null) {
                int targetX = crate.getGridX();
                int targetY = crate.getGridY() + 1;
                if (targetY >= (int) CANVAS_HEIGHT / MainGame.CELL_SIZE) {
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
        win();
    }

    /**
     * If the slime is in the winning position, displays win screen and resets to next level.
     */
    public void win() {
         if (slime.getYGridCellLocation() == winY && slime.getXGridCellLocation() == winX) {
            winScreen();
            resetGame();
        }
    }

    /**
     * Implements animation closure that calls movement methods based on arrow key presses.
     */
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

    /**
     * Iterates through given map, adding elements at specfied positions including slime's starting and 
     * ending positions. 
     * @param map List of strings containing maze info.
     */
    public void makeMaze(List<String> map) {
        for (int y = 0; y < map.size(); y++) {
            String rowData = map.get(y);
            for (int x = 0; x < rowData.length(); x++) {
                char c = rowData.charAt(x);
                if (c == 'X') {
                    WallBlock wall = new WallBlock(x, y);
                    gameBoard.addWallBlockToGrid(wall);
                    game.add(wall);
                } else if (c == 'C') {
                    Crate crate = new Crate(x, y, gameBoard);
                    gameBoard.addCrateToGrid(crate);
                    game.add(crate);
                } else if (c == 'w') {
                    winX = x;
                    winY = y;
                } else if (c == 's') {
                    slime.setSlimePos(new Point(x * CELL_SIZE, y * CELL_SIZE));
                }
            }
        }
    }

    /**
     * Displays message indicating completion of the level.
     */
    public void winScreen() {
        Rectangle win = new Rectangle(CANVAS_HEIGHT / 2, CANVAS_WIDTH / 2, 200, 200);
        win.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        win.setFillColor(Color.GREEN);
        GraphicsText winText = new GraphicsText("Level cleared!\nGenerating next maze...");
        winText.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        game.add(win);
        game.add(winText);
        canvas.draw();
        canvas.pause(4000);
    }

    /**
     * Creates a new game with the next maze level.
     */
    public void resetGame() {
        new MainGame(levelIndex + 1).run();
        canvas.closeWindow();
    }
}
