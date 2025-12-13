package slimekoban;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class WallBlock extends Rectangle {
    private static final Color WALL_COLOR = new Color (184, 146, 40);
    private int gridX;
    private int gridY;
    
    public WallBlock(int gridX, int gridY) {
        super(gridX * MainGame.CELL_SIZE, gridY * MainGame.CELL_SIZE, MainGame.CELL_SIZE, MainGame.CELL_SIZE);
        this.gridX = gridX;
        this.gridY = gridY;
        setFillColor(WALL_COLOR);
        setStrokeColor(Color.BLACK);
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    @Override
    public String toString() {
        return "WallBlock [gridX=" + gridX + ", gridY=" + gridY + "]";
    }
    
}
