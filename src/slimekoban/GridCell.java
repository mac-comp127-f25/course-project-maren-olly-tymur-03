package slimekoban;

import java.util.ArrayList; 

public class GridCell {

private int cellNumber;
private int x;
private int y;
private int objectIdentifier; /*
define object identifiers:
0 - empty cell
1 - wall
2 - slime
to be continued
*/
private ArrayList <GridCell> CellGrid = new ArrayList<>();

public GridCell(int cellNumber, int x, int y, int objectIdentifier) {
    this.cellNumber = cellNumber;
    this.x = x;
    this.y = y;
    this.objectIdentifier = objectIdentifier;
}

public int getObjectIdentifier() {
    return objectIdentifier;
}

public void setObjectIdentifier(int objectIdentifier) {
    this.objectIdentifier = objectIdentifier;
} 

public void generateCellGrid() {


}

public ArrayList<GridCell> getCellGrid() {
    return CellGrid;
}
}
