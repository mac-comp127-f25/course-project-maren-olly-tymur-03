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

    /*  public void addWallToCell(int x, int y) {

        cells.get(x).set(new Point (x, y));
    } */
}

//we need a convert to pixals method

// is there an existing cell where i want to go?
    // can i move there?
        // is there something in the neighbor cell?
        // is there something in the nc's nc?
        // 0 free, 1 wall, (invalid) 2 slime

        // tymur's edit: slime is no longer recognized 
        // on par with non controlled objects
