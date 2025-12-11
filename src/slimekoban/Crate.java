package slimekoban;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;
import edu.macalester.graphics.Point;


public class Crate extends Rectangle {

    private static final Color CRATE_COLOR = new Color(139, 69, 19); 
    private int gridX;
    private int gridY;
    private GameBoard gameBoard;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;

    public Crate(int gridX, int gridY, GameBoard gameBoard) {
        super(gridX * MainGame.CELL_SIZE, gridY * MainGame.CELL_SIZE, MainGame.CELL_SIZE, MainGame.CELL_SIZE);
        this.gridX = gridX;
        this.gridY = gridY;
        this.gameBoard = gameBoard;
        setFillColor(CRATE_COLOR);
        setStrokeColor(Color.BLACK);
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public Integer getDownNeighbor() {
        return downNeighbor;
    }

    public Integer getLeftNeighbor() {
        return leftNeighbor;
    }
    public Integer getRightNeighbor() {
        return rightNeighbor;
    }

    public Integer getUpNeighbor() {
        return upNeighbor;
    }

    public void setDownNeighbor(Integer downNeighbor) {
        this.downNeighbor = downNeighbor;
    }

    public void setLeftNeighbor(Integer leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public void setRightNeighbor(Integer rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public void setUpNeighbor(Integer upNeighbor) {
        this.upNeighbor = upNeighbor;
    }

    
   public void moveLeftOnce() {
        if (leftNeighbor != 1 && getX() > 0) {
            Point newPos = new Point(getX() - MainGame.CELL_SIZE, getY());
            gameBoard.updateCrateInGrid(gridX, gridY, gridX - 1, gridY);
            setPosition(newPos);
        }
    }

    public void moveRightOnce() {
        if (rightNeighbor != 1 && getX() < MainGame.getCANVAS_WIDTH() - MainGame.CELL_SIZE) {
            Point newPos = new Point(getX() + MainGame.CELL_SIZE, getY());
            gameBoard.updateCrateInGrid(gridX, gridY, gridX + 1, gridY);
            setPosition(newPos);
        }
    }

    public void moveUpOnce() {
        if (upNeighbor != 1) {
            if (getY() > 0) {
                Point newPos = new Point(getX(), getY() - MainGame.CELL_SIZE);
                gameBoard.updateCrateInGrid(gridX, gridY, gridX, gridY - 1);
                setPosition(newPos);
            }
        }
    }

    public void moveDownOnce() {
        if(downNeighbor != 1) {
            if (getY() < MainGame.getCANVAS_HEIGHT() - MainGame.CELL_SIZE) {
                Point newPos = new Point(getX(), getY() + MainGame.CELL_SIZE);
                gameBoard.updateCrateInGrid(gridX, gridY, gridX, gridY + 1);
                setPosition(newPos);
            }
        }
    }
}
