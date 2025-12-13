package slimekoban;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.Image;


public class Slime {
    private Point slimePos;
    private final double SLIME_SIZE = 30;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;
    public Image slimeImage;
    

    public Slime(Point slimePos) {
        this.slimePos = slimePos;
        slimeImage = new Image(slimePos.getX(), slimePos.getY(), "slime.png");
        slimeImage.setMaxWidth(SLIME_SIZE);
        slimeImage.setMaxHeight(SLIME_SIZE);
    }

    public Image getGraphics() {
        return slimeImage;
    }

    public Point getSlimePos() {
        return slimePos;
    }

    public void setSlimePos(Point slimePos) {
        this.slimePos = slimePos;
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
    
    /**
     * Returns the Y index of where the wall's location in the cells ArrayList.
     */
    public Integer getYGridCellLocation() {
        Integer yIndex = Integer.valueOf((int) slimePos.getY());
        if (yIndex >= 30) {
            yIndex /= 30;
        } else {
            yIndex = 0;
        }
        return yIndex;
   }

    /**
     * Returns the Y index of where the wall's location in the cells ArrayList.
     */
    public Integer getXGridCellLocation() {
        Integer xIndex = Integer.valueOf((int) slimePos.getX());
        if (xIndex >= 30) {
            xIndex /= 30;
        } else {
            xIndex = 0;
        }
        return xIndex;
   }
    
    /**
    * If slime's left neighbor is free, moves it one space to the left.
    */
    public void moveLeftOnce() {
        if (leftNeighbor != null && leftNeighbor == 0) {
            if (slimePos.getX() > 0) {
                slimePos = new Point(slimePos.getX() - SLIME_SIZE, slimePos.getY());
                slimeImage.setPosition(slimePos);
            }
        }
    }

    /**
    * If slime's right neighbor is free, moves it one space to the right.
    */
    public void moveRightOnce() {
        if (rightNeighbor != null && rightNeighbor == 0) {
            if (slimePos.getX() < MainGame.CANVAS_WIDTH - SLIME_SIZE) {
                slimePos = new Point(slimePos.getX() + SLIME_SIZE, slimePos.getY());
                slimeImage.setPosition(slimePos);
            }
        }
    }

    /**
    * If slime's above neighbor is free, moves it one space up.
    */
    public void moveUpOnce() {
        if (upNeighbor != null && upNeighbor == 0) {
            if (slimePos.getY() > 0) {
                slimePos = new Point(slimePos.getX(), slimePos.getY() - SLIME_SIZE);
                slimeImage.setPosition(slimePos);
            }
        }
    }

    /**
    * If slime's below neighbor is free, moves it one space down.
    */
    public void moveDownOnce() {
        if(downNeighbor != null && downNeighbor == 0) {
            if (slimePos.getY() < MainGame.CANVAS_HEIGHT - SLIME_SIZE) {
                slimePos = new Point(slimePos.getX(), slimePos.getY() + SLIME_SIZE);
                slimeImage.setPosition(slimePos);
            }
        }
    }

    @Override
    public String toString() {
        return "Slime [slimePos=" + slimePos + ", SLIME_SIZE=" + SLIME_SIZE + ", upNeighbor=" + upNeighbor
            + ", downNeighbor=" + downNeighbor + ", leftNeighbor=" + leftNeighbor + ", rightNeighbor=" + rightNeighbor
            + ", slimeImage=" + slimeImage + "]";
    }
    
} 

