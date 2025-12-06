package slimekoban;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.Point;


public class GameBoard {
    private static boolean neighborCellAvailable;
    private List<List<Integer>> cells = new ArrayList<>();
    //private Slime slime;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;

    public GameBoard() {
        //this.slime = slime;
        for(int count = 0; count < 20; count++) {
            cells.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 20; i++) {
            for( int n = 0; n < 20; n++) {
                cells.get(i).add(n, 0);
            }
        }
    }

    public List<List<Integer>> getCells() {
        return cells;
    }
    public boolean checkCellAvailability() {
        return neighborCellAvailable;
    } 

    /**
     * Takes a WallBlock's position on the canvas and translates it to
     * its location in the grid array system, adds it as a wall (value of 1)
     * to the grid array.
     * @param xIndex call to getXGridCellLocation method from WallBlock
     * @param yIndex call to getYGridCellLocation method from WallBlock
     */
    public void addWallBlockToGrid(Integer xIndex, Integer yIndex) {
        cells.get((int) yIndex).add((int) xIndex, 1);
    }

    /**
     * Takes a crate's position on the canvas and translates it to
     * its location in the grid array system, adds it as a wall (value of 1)
     * to the grid array.
     * @param xIndex call to getXCrateLocation method from Crate
     * @param yIndex call to getYCrateLocation method from Crate
     */
    public void addCrateToGrid(Integer xIndex, Integer yIndex) {
        cells.get((int) yIndex).add((int) xIndex, 2);
    }

    public void updateSlimeNeighbors(Slime slime) {
        System.out.println(slime);
        System.out.println(slime.getSlimePos().getX() / 30);
        System.out.println(slime.getSlimePos().getY() / 30);
        int slimeGridX = ((int) slime.getSlimePos().getX()) / 30;
        int slimeGridY = ((int) slime.getSlimePos().getY()) / 30;
        
        if (slimeGridY == 0) {
            upNeighbor = 1;
        } else {
            upNeighbor = cells.get(slimeGridY - 1).get(slimeGridX);
            System.out.println("up " + upNeighbor + ", "   + cells.get(slimeGridY - 1).get(slimeGridX));
        }
        if (slimeGridY == 20) {
            downNeighbor = 1;
        } else {
            downNeighbor = cells.get(slimeGridY + 1).get(slimeGridX);
            System.out.println("down " + downNeighbor  + ", " + cells.get(slimeGridY + 1).get(slimeGridX));
        }
        if (slimeGridX == 0) {
           leftNeighbor = 1; 
        } else {
            leftNeighbor = cells.get(slimeGridY).get(slimeGridX - 1);
            System.out.println("left " + leftNeighbor  + ", " + cells.get(slimeGridY).get(slimeGridX - 1));
        }
        if(slimeGridX == 20) {
            rightNeighbor = 1;
        } else {
            rightNeighbor = cells.get(slimeGridY).get(slimeGridX + 1);
            System.out.println("right " + rightNeighbor + ", " + cells.get(slimeGridY).get(slimeGridX + 1));
        }
    }
    

    public Integer getUpNeighbor() {
        return upNeighbor;
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
}

//we need a convert to pixals method

// is there an existing cell where i want to go?
    // can i move there?
        // is there something in the neighbor cell?
        // is there something in the nc's nc?
        // 0 free, 1 wall, ~~(invalid) 2 slime~~ -> 2 crate

        // tymur's edit: slime is no longer recognized 
        // on par with non controlled objects

        // olly's edit: 2 now represents a Crate object 
