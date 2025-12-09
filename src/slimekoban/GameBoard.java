package slimekoban;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.Point;

        // 0 free, 1 wall, ~~(invalid) 2 slime~~ -> 2 crate

        // tymur's edit: slime is no longer recognized 
        // on par with non controlled objects

        // olly's edit: 2 now represents a Crate object 


public class GameBoard {
    private static boolean neighborCellAvailable;
    private List<List<Integer>> cells = new ArrayList<>();
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
    public void addWallBlockToGrid(Integer xIndex, Integer yIndex, WallBlock wallBlock) {
        cells.get((int) yIndex).set((int) xIndex, 1);
        wallBlocks.add(wallBlock);
    }

    /**
     * Takes a crate's position on the canvas and translates it to
     * its location in the grid array system, adds it as a crate (value of 2)
     * to the grid array.
     * @param xIndex call to getXCrateLocation method from Crate
     * @param yIndex call to getYCrateLocation method from Crate
     */
    public void addCrateToGrid(Integer xIndex, Integer yIndex, Crate crate) {
        cells.get((int) yIndex).set((int) xIndex, 2);
        crates.add(crate);
    }

    public void updateSlimeNeighbors() {
        System.out.println(slime);
        System.out.println(slime.getSlimePos().getX() / 30);
        System.out.println(slime.getSlimePos().getY() / 30);
        int slimeGridX = ((int) slime.getSlimePos().getX()) / 30;
        int slimeGridY = ((int) slime.getSlimePos().getY()) / 30;
        
        // Check bounds before accessing neighbor cells
        if (slimeGridY <= 0) {
            slime.setUpNeighbor(1);
        } else {
            slime.setUpNeighbor(cells.get(slimeGridY - 1).get(slimeGridX));
        }
        if (slimeGridY >= 19) {
            slime.setDownNeighbor(1);
        } else {
            slime.setDownNeighbor(cells.get(slimeGridY + 1).get(slimeGridX));
        }
        if (slimeGridX <= 0) {
           slime.setLeftNeighbor(1); 
        } else {
            slime.setLeftNeighbor(cells.get(slimeGridY).get(slimeGridX - 1));
        }
        if(slimeGridX >= 19) {
            slime.setRightNeighbor(1);
        } else {
            slime.setRightNeighbor(cells.get(slimeGridY).get(slimeGridX + 1));
        }
    }

    //NOT DONE WILL FINISH MONDAY NIGHT
    // public void updateCrateNeighbors() {
    //     for(Crate crate: crates) {
    //         //what x and y
    //         //look around
    //         //set values based on what around
    //         int gridX = (int) (crate.getX() / 30);
    //         int gridY = (int) (crate.getY() / 30);

    //         if(gridY <= 0) {
    //             crate.setUpNeighbor(1);
    //         } else {
    //             crate.setUpNeighbor(cells.get(gridY - 1).get(gridX));
    //         }
    //         if(gridY >= 19) {
    //             crate.setDownNeighbor(1);
    //         } else {
    //             crate.setDownNeighbor(cells.get(gridY + 1).get(gridX));
    //         }
    //     }
    // }
    
    // public Integer getUpNeighbor() {
    //     return upNeighbor;
    // }

    // public Integer getDownNeighbor() {
    //     return downNeighbor;
    // }

    // public Integer getLeftNeighbor() {
    //     return leftNeighbor;
    // }

    // public Integer getRightNeighbor() {
    //     return rightNeighbor;
    // }
}
