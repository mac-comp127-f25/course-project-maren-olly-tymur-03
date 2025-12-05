// package slimekoban;

// import java.util.ArrayList; 

// public class GridCell {

<<<<<<< HEAD
// private int cellNumber;
// private int x;
// private int y;
// private int objectIdentifier; /*
// define object identifiers:
// 0 - empty cell
// 1 - wall
// 2 - slime
// to be continued
// */
// private ArrayList <GridCell> CellGrid = new ArrayList<>();

// public GridCell(int cellNumber, int x, int y, int objectIdentifier) {
//     this.cellNumber = cellNumber;
//     this.x = x;
//     this.y = y;
//     this.objectIdentifier = objectIdentifier;
// }

// public int getObjectIdentifier() {
//     return objectIdentifier;
// }

// public void setObjectIdentifier(int objectIdentifier) {
//     this.objectIdentifier = objectIdentifier;
// } 

// public void generateCellGrid() {


// }

// public ArrayList<GridCell> getCellGrid() {
//     return CellGrid;
// }
// }
=======
    private int cellNumber;
    private int x;
    private int y;
    private int cellSize = 40;
    private int objectIdentifier; 
    /*
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
        int cellNumber = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < MainGame.getCANVAS_HEIGHT(); i+= 40) {
            GridCell cell = new GridCell(cellNumber, x, y, 0);
            cellNumber++;
            x += cellSize;
            y += cellSize;
            CellGrid.add(cell);
        }
    }

    public ArrayList<GridCell> getCellGrid() {
        return CellGrid;
    }
}
>>>>>>> 586c759b7d5d8701760d89e2bbf9649c9b3b0344
