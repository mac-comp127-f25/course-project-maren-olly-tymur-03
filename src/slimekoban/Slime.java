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
  


    public Slime(Point slimePos) {
        this.slimePos = slimePos;
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
    
    public void moveLeftOnce() {
        Integer leftNeighbor = gameBoard.getLeftNeighbor();
        if (leftNeighbor != 1) {
            if (slimePos.getX() > 0) {
                slimePos = new Point(slimePos.getX() - SLIME_SIZE, slimePos.getY());
                slimeRectangle.setPosition(slimePos);
            }
        }
    }

    public void moveRightOnce() {
        Integer rightNeighbor = gameBoard.getRightNeighbor();
        if (rightNeighbor != 1) {
            if (slimePos.getX() < MainGame.getCANVAS_WIDTH() - SLIME_SIZE) {
                slimePos = new Point(slimePos.getX() + SLIME_SIZE, slimePos.getY());
                slimeRectangle.setPosition(slimePos);
            }
        }
    }

    public void moveUpOnce() {
        Integer upNeighbor = gameBoard.getUpNeighbor();
        if (upNeighbor != 1) {
            if (slimePos.getY() > 0) {
                slimePos = new Point(slimePos.getX(), slimePos.getY() - SLIME_SIZE);
                slimeRectangle.setPosition(slimePos);
            }
        }
    }

    public void moveDownOnce() {
        Integer downInteger = gameBoard.getDownNeighbor();
        if(downInteger != 1) {
            if (slimePos.getY() < MainGame.getCANVAS_HEIGHT() - SLIME_SIZE) {
                slimePos = new Point(slimePos.getX(), slimePos.getY() + SLIME_SIZE);
                slimeRectangle.setPosition(slimePos);
            }
        }
    }

   
    
} 

