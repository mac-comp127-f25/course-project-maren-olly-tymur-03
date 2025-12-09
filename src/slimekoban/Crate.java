package slimekoban;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

import java.awt.Canvas;
import java.awt.Color;
import edu.macalester.graphics.Point;

public class Crate extends Rectangle {

    private static final Color CRATE_COLOR = new Color(139, 69, 19); 
    private Point position;
    private GameBoard gameBoard;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;

    public Crate(double width, double height, Point position, GameBoard gameBoard) {
        super(position.getX(), position.getY(), width, height);
        this.position = position;
        setFillColor(CRATE_COLOR);
        setStrokeColor(Color.BLACK);
        this.gameBoard = gameBoard;
    }

    public Point getCratePosition() {
        return position;
    }

    public void setCratePosition(Point position) {
        this.position = position;
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

    // reurns the Y index of where the crate is supossed to be the the cells arraylist
   public Integer getYCrateLocation() {
        Integer yIndex = Integer.valueOf((int) position.getY());
        if (yIndex >= 30) {
            yIndex /= 30;
        } else {
            yIndex = 0;
        }
        return yIndex;
   }

   // reurns the X index of where the crate is supossed to be the the cells arraylist
   public Integer getXCrateLocation() {
        Integer xIndex = Integer.valueOf((int) position.getX());
        if (xIndex >= 30) {
            xIndex /= 30;
        } else {
            xIndex = 0;
        }
        return xIndex;
   }
    
   public void moveLeftOnce() {
        if (leftNeighbor != 1) {
            if (position.getX() > 0) {
                position = new Point(position.getX() - 30, position.getY());
                setPosition(position);
            }
        }
    }

    public void moveRightOnce() {
        if (rightNeighbor != 1) {
            if (position.getX() < MainGame.getCANVAS_WIDTH() - 30) {
                position = new Point(position.getX() + 30, position.getY());
                setPosition(position);
            }
        }
    }

    public void moveUpOnce() {
        if (upNeighbor != 1) {
            if (position.getY() > 0) {
                position = new Point(position.getX(), position.getY() - 30);
                setPosition(position);
            }
        }
    }

    public void moveDownOnce() {
        if(downNeighbor != 1) {
            if (position.getY() < MainGame.getCANVAS_HEIGHT() - 30) {
                position = new Point(position.getX(), position.getY() + 30);
                setPosition(position);
            }
        }
    }
}
