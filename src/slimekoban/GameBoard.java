package slimekoban;

import java.util.ArrayList;
import java.util.List;


public class GameBoard {
    private boolean neighborCellAvailable;
    private GridCell gridCell;
    private List<GridCell> cells = new ArrayList<>(gridCell.getCellGrid());


    public GameBoard() {
        //cells = gridCell.getCellGrid();
    }

     public boolean checkCellAvailability() {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getObjectIdentifier() == 2) {
                return true;
            }
        }
        return false;
    } 
}



// is there an existing cell where i want to go?
    // can i move there?
        // is there something in the neighbor cell?
        // is there something in the nc's nc?
        // 0 wall, 1 cell, 2 slime
