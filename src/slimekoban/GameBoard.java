package slimekoban;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.Point;


public class GameBoard {
    private boolean neighborCellAvailable;
    private List<List<Integer>> cells = new ArrayList<>();
    private Slime slime;


    public GameBoard() {
        slime = new Slime(slime.getSlimePos());
        cells.add(new ArrayList<Integer>());
        cells.add(new ArrayList<Integer>());
        cells.add(new ArrayList<Integer>());
        cells.add(new ArrayList<Integer>());
        for(int i = 0; i < 4; i++) {
            for( int n = 0; n < 4; n++) {
                cells.get(i).add(n);
            }
        }
    }

    // public boolean checkCellAvailability() {

    // } 
}

//we need a convert to pixals method

// is there an existing cell where i want to go?
    // can i move there?
        // is there something in the neighbor cell?
        // is there something in the nc's nc?
        // 0 free, 1 wall, 2 slime
