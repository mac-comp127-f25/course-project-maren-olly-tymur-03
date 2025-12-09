package slimekoban;

import java.awt.Color;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;



public class Slime {
    private Point slimePos;
    private final double SLIME_SIZE = 30;
    private Rectangle slimeRectangle;
    private boolean moveLeft, moveRight, moveUp, moveDown;
    private GameBoard gameBoard;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;
    

    public Slime(Point slimePos, GameBoard gameBoard) {
        this.slimePos = slimePos;
        this.gameBoard = gameBoard;
        slimeRectangle = new Rectangle(slimePos.getX(), slimePos.getY(), SLIME_SIZE, SLIME_SIZE);
        slimeRectangle.setFillColor(Color.WHITE);
        slimeRectangle.setStrokeColor(Color.WHITE);
    }

    public Rectangle getGraphics() {
        return slimeRectangle;
    }

    public Point getSlimePos() {
        return slimePos;
    }

    public void setSlimePos(Point slimePos) {
        this.slimePos = slimePos;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
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
    // public void update() {
    //     if (moveLeft) {
    //         if (slimePos.getX() > 0)
    //         slimePos = new Point(slimePos.getX() - 30, slimePos.getY());
    //     }
    //     if (moveRight) {
    //         if (slimePos.getX() < MainGame.getCANVAS_WIDTH() - SLIME_SIZE)
    //         slimePos = new Point(slimePos.getX() + 30, slimePos.getY());
    //     }
    //     if (moveUp) {
    //         if (slimePos.getY() > 0)
    //         slimePos = new Point(slimePos.getX(), slimePos.getY() - 30);
    //     }
    //     if (moveDown) {
    //         if (slimePos.getY() < 600)
    //         slimePos = new Point(slimePos.getX(), slimePos.getY() + 30);
    //         System.out.println(slimePos.getY());
    //     }
    //     slimeRectangle.setPosition(slimePos);
    // }
    
    // reurns the Y index of where the wall is supossed to be the the cells arraylist
   public Integer getYGridCellLocation() {
        Integer yIndex = Integer.valueOf((int) slimePos.getY());
        if (yIndex >= 30) {
            yIndex /= 30;
        } else {
            yIndex = 0;
        }
        return yIndex;
   }

   // reurns the X index of where the wall is supossed to be the the cells arraylist
   public Integer getXGridCellLocation() {
        Integer xIndex = Integer.valueOf((int) slimePos.getX());
        if (xIndex >= 30) {
            xIndex /= 30;
        } else {
            xIndex = 0;
        }
        return xIndex;
   }
    
    public void moveLeftOnce() {
        //Integer leftNeighbor = gameBoard.getLeftNeighbor();
        if (leftNeighbor != 1) {
            if (slimePos.getX() > 0) {
                //if(gameBoard.getCrateAt(getXGridCellLocation() - 1, getYGridCellLocation()).getLeftNeighbor() != 1) {
                    slimePos = new Point(slimePos.getX() - SLIME_SIZE, slimePos.getY());
                    slimeRectangle.setPosition(slimePos);
                //}
            }
        }
    }

    public void moveRightOnce() {
        //Integer rightNeighbor = gameBoard.getRightNeighbor();
        if (rightNeighbor != 1) {
            if (slimePos.getX() < MainGame.getCANVAS_WIDTH() - SLIME_SIZE) {
                slimePos = new Point(slimePos.getX() + SLIME_SIZE, slimePos.getY());
                slimeRectangle.setPosition(slimePos);
            }
        }
    }

    public void moveUpOnce() {
        //Integer upNeighbor = gameBoard.getUpNeighbor();
        if (upNeighbor != 1) {
            if (slimePos.getY() > 0) {
                slimePos = new Point(slimePos.getX(), slimePos.getY() - SLIME_SIZE);
                slimeRectangle.setPosition(slimePos);
            }
        }
    }

    public void moveDownOnce() {
        //Integer downNeighnor = gameBoard.getDownNeighbor();
        if(downNeighbor != 1) {
            if (slimePos.getY() < MainGame.getCANVAS_HEIGHT() - SLIME_SIZE) {
                slimePos = new Point(slimePos.getX(), slimePos.getY() + SLIME_SIZE);
                slimeRectangle.setPosition(slimePos);
            }
        }
    }
} 

