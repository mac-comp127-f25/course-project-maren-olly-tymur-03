package slimekoban;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.Point;


public class GameBoard {
    private static boolean neighborCellAvailable;
    private List<List<Integer>> cells = new ArrayList<>();
    private Slime slime;
    

    public GameBoard() {
        slime = new Slime(slime.getSlimePos());
        for(int count = 0; count < 20; count++) {
            cells.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 20; i++) {
            for( int n = 0; n < 20; n++) {
                cells.get(i).add(n);
            }
        }
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
        xIndex /= 30;
        yIndex /= 30;
        cells.get((int) yIndex).add((int) xIndex, 1);
    }
}

//we need a convert to pixals method

// is there an existing cell where i want to go?
    // can i move there?
        // is there something in the neighbor cell?
        // is there something in the nc's nc?
        // 0 free, 1 wall, (invalid) 2 slime

        // tymur's edit: slime is no longer recognized 
        // on par with non controlled objects
