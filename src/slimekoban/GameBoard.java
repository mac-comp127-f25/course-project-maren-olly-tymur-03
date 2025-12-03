package slimekoban;

import java.util.ArrayList;
import java.util.List;


public class GameBoard {
    private List<GridCell> cells;
    private boolean neighborCellAvailable;


    public GameBoard() {
        // cells = GridCell.getCells();
    }

   /*  public boolean checkCellAvailability() {
        for (int i = 0; i < cells.size; i++) {
            
        }
    } */
}



// is there an existing cell where i want to go?
    // can i move there?
        // is there something in the neighbor cell?
        // is there something in the nc's nc?
        // 0 wall, 1 cell, 2 slime
