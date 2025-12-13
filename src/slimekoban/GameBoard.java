package slimekoban;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<List<Integer>> cells = new ArrayList<>();
    public Slime slime;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;
    public List<Crate> crates = new ArrayList<>();
    public List<WallBlock> wallBlocks = new ArrayList<>();

    /**
     * Creates a 2D array representing the grid system used to handle internal game logic.
     * The ArrayLists of integers represent the Y and X axes. (Note that any interation through
     * the grid begins with the Y values representing rows and then the X values representing
     * columns.) Integers are used to denote object identification. 
     * Grid object identification legend:
        * 0 = free space
        * 1 = wall (either WallBlock object or canvas extremity)
        * 2 = Crate object
     * @param slime Takes the slime object created in MainGame.
     */
    public GameBoard(Slime slime) {
        this.slime = slime;
        for(int count = 0; count < 20; count++) {
            cells.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < 20; i++) {
            for( int n = 0; n < 20; n++) {
                cells.get(i).add(n, 0);
            }
        }
    }

    public List<Crate> getCrates() {
        return crates;
    }

    public List<WallBlock> getWallBlocks() {
        return wallBlocks;
    }

    public List<List<Integer>> getCells() {
        return cells;
    }

    /**
     * Returns Crate object if there is a crate at the specified (x, y) position.
     * @return Returns Crate if found, null if not.
     */
    public Crate getCrateAt(int x, int y) {
        if(cells.get(y).get(x) == 2) {
            for(Crate crate : crates) {
                if(crate.getGridX() == x && crate.getGridY() == y) {
                    return crate;
                }
            }
        }
        return null;
    }

    /**
     * Returns WallBlock object if there is a wall at the specified (x, y) position.
     * @return Returns WallBlock if found, null if not.
     */
    public WallBlock getWallBlockAt(int x, int y) {
        if(cells.get(y).get(x) == 1) {
            for(WallBlock wallBlock: wallBlocks) {
                if(wallBlock.getX() == x && wallBlock.getY() == y) {
                    return wallBlock;
                }
            }
        }
        return null;
    }

    /**
     * Takes a WallBlock's position on the canvas and translates it to
     * its location in the grid array system, adds it as a wall (value of 1)
     * to the grid array. also adds it to a wallblock array
     * @param xIndex call to getXGridCellLocation method from WallBlock
     * @param yIndex call to getYGridCellLocation method from WallBlock
     * @param wallblock is a wallblock
     */
    public void addWallBlockToGrid(WallBlock wallBlock) {
        cells.get(wallBlock.getGridY()).set(wallBlock.getGridX(), 1);
        wallBlocks.add(wallBlock);
    }

    /**
     * Takes a crate's position on the canvas and translates it to
     * its location in the grid array system, adds it as a crate (value of 2)
     * to the grid array. also adds it to a crate List
     * @param xIndex call to getXCrateLocation method from Crate
     * @param yIndex call to getYCrateLocation method from Crate
     * @param crate is a crate
     */
    public void addCrateToGrid(Crate crate) {
        cells.get(crate.getGridY()).set(crate.getGridX(), 2);
        crates.add(crate);
    }

    public void updateCrateInGrid(int oldX, int oldY, int newX, int newY) {
        cells.get((int) oldY).set((int) oldX, 0);
        cells.get((int) newY).set((int) newX, 2);
    }

    /**
     * Returns true if the cell at (x,y) is within bounds and currently empty (0).
     */
    public boolean isCellFree(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (y >= cells.size() || x >= cells.get(0).size()) return false;
        return cells.get(y).get(x) == 0;
    }

    /**
     * Checks Slime's position in the grid. If the Slime is at the canvas' edges,
     * it sets the directionally appropriate neighbor as a wall. Otherwise, it checks
     * the position of one grid cell above/below/left/right of the Slime and sets those
     * as the Slime's neighboring cells.
     */
    public void updateSlimeNeighbors() {
        int gridX = ((int) slime.getSlimePos().getX()) / 30;
        int gridY = ((int) slime.getSlimePos().getY()) / 30;
        // Checks bounds before accessing neighbor cells
        if (gridY <= 0) {
            slime.setUpNeighbor(1);
        } else {
            slime.setUpNeighbor(cells.get(gridY - 1).get(gridX));
        }
        if (gridY >= 19) {
            slime.setDownNeighbor(1);
        } else {
            slime.setDownNeighbor(cells.get(gridY + 1).get(gridX));
        }
        if (gridX <= 0) {
           slime.setLeftNeighbor(1); 
        } else {
            slime.setLeftNeighbor(cells.get(gridY).get(gridX - 1));
        }
        if(gridX >= 19) {
            slime.setRightNeighbor(1);
        } else {
            slime.setRightNeighbor(cells.get(gridY).get(gridX + 1));
        }
    }

    /**
     * Checks the position of every crate in the grid. If the Crate is at the canvas' edges,
     * it sets the directionally appropriate neighbor as a wall. Otherwise, it checks
     * the position of one grid cell above/below/left/right of the Crate and sets those
     * as the Crates's neighboring cells.
     */
    public void updateCrateNeighbors() {
        for(Crate crate: crates) {
            int gridX = (int) (crate.getX() / 30);
            int gridY = (int) (crate.getY() / 30);

            if(gridY <= 0) {
                crate.setUpNeighbor(1);
            } else {
                crate.setUpNeighbor(cells.get(gridY - 1).get(gridX));
            }
            if(gridY >= 19) {
                crate.setDownNeighbor(1);
            } else {
                crate.setDownNeighbor(cells.get(gridY + 1).get(gridX));
            }
            if (gridX <= 0) {
                crate.setLeftNeighbor(1);
            } else {
                crate.setLeftNeighbor(cells.get(gridY).get(gridX - 1));
            }
            if(gridX >= 19) {
                crate.setRightNeighbor(1);
            } else {
                crate.setRightNeighbor(cells.get(gridY).get(gridX + 1));
            }
        }   
    }

    @Override
    public String toString() {
        return "GameBoard [cells=" + cells + ", slime=" + slime + ", upNeighbor=" + upNeighbor + ", downNeighbor="
            + downNeighbor + ", leftNeighbor=" + leftNeighbor + ", rightNeighbor=" + rightNeighbor + ", crates="
            + crates + ", wallBlocks=" + wallBlocks + "]";
    }
    
}
