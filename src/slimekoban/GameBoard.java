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
    public Slime slime;
    public Integer upNeighbor = 0;
    public Integer downNeighbor = 0;
    public Integer leftNeighbor = 0;
    public Integer rightNeighbor = 0;
    public List<Crate> crates = new ArrayList<>();
    public List<WallBlock> wallBlocks = new ArrayList<>();

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
    public boolean checkCellAvailability() {
        return neighborCellAvailable;
    } 

    public Crate getCrateAt(int x, int y) {
        // int gridX = x / 30;
        // int gridY = y / 30;
        if(cells.get(y).get(x) == 2) {
            System.out.println("is crate");
            for(Crate crate : crates) {
                
                if((crate.getX() / 30) == x && (crate.getY()) / 30 == y) {
                    System.out.println("is THE crate");
                    return crate;
                }
            }
        }
        return null;
    }

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
    public void addWallBlockToGrid(Integer xIndex, Integer yIndex, WallBlock wallBlock) {
        cells.get((int) yIndex).set((int) xIndex, 1);
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
    public void addCrateToGrid(Integer xIndex, Integer yIndex, Crate crate) {
        cells.get((int) yIndex).set((int) xIndex, 2);
        crates.add(crate);
    }

    public void updateCrateInGrid(int oldX, int oldY, int newX, int newY) {
        cells.get((int) oldY).set((int) oldX, 0);
        cells.get((int) newY).set((int) newX, 2);
    }

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

    public void updateCrateNeighbors() {
        for(Crate crate: crates) {
            int gridX = (int) (crate.getX() / 30);
            int gridY = (int) (crate.getY() / 30);
            // Checks bounds before accessing neighbor cells
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
}
