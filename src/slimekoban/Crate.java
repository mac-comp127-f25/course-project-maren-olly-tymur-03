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
    
//    public void moveLeftOnce() {
//         Integer leftNeighbor = gameBoard.getLeftNeighbor();
//         if (leftNeighbor != 1) {
//             if (slimePos.getX() > 0) {
//                 slimePos = new Point(slimePos.getX() - (30)SLIME_SIZE, slimePos.getY());
//                 slimeRectangle.setPosition(slimePos);
//             }
//         }
//     }

    // public void moveRightOnce() {
    //     Integer rightNeighbor = gameBoard.getRightNeighbor();
    //     if (rightNeighbor != 1) {
    //         if(rightNeighbor == 2) {
    //             //gameBoard.getRightNeighbor(). crate move right
    //         }
    //         if (slimePos.getX() < MainGame.getCANVAS_WIDTH() - SLIME_SIZE) {
    //             slimePos = new Point(slimePos.getX() + SLIME_SIZE, slimePos.getY());
    //             slimeRectangle.setPosition(slimePos);
    //         }
    //     }
    // }

    // public void moveUpOnce() {
    //     Integer upNeighbor = gameBoard.getUpNeighbor();
    //     if (upNeighbor != 1) {
    //         if (slimePos.getY() > 0) {
    //             slimePos = new Point(slimePos.getX(), slimePos.getY() - SLIME_SIZE);
    //             slimeRectangle.setPosition(slimePos);
    //         }
    //     }
    // }

    // public void moveDownOnce() {
    //     Integer downNeighnor = gameBoard.getDownNeighbor();
    //     if(downNeighnor != 1) {
    //         if (slimePos.getY() < MainGame.getCANVAS_HEIGHT() - SLIME_SIZE) {
    //             slimePos = new Point(slimePos.getX(), slimePos.getY() + SLIME_SIZE);
    //             slimeRectangle.setPosition(slimePos);
    //         }
    //     }
    // }
}
