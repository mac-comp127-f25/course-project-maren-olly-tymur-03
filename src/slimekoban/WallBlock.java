package slimekoban;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;
import edu.macalester.graphics.Point;

public class WallBlock extends Rectangle {
    private static final Color WALL_COLOR = Color.DARK_GRAY;
    public Point position;
    


    public WallBlock(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.position = new Point(x, y);
        setFillColor(WALL_COLOR);
        setStrokeColor(Color.BLACK);
    }

    public Point getPos() {
        return position;
    }

   // reurns the Y index of where the wall is supossed to be the the cells arraylist
   public Integer getYGridCellLocation() {
        Integer yIndex = Integer.valueOf((int) position.getY());
        if (yIndex >= 30) {
            yIndex /= 30;
        } else {
            yIndex = 0;
        }
        return yIndex;
   }

   // reurns the X index of where the wall is supossed to be the the cells arraylist
   public Integer getXGridCellLocation() {
        Integer xIndex = Integer.valueOf((int) position.getX());
        if (xIndex >= 30) {
            xIndex /= 30;
        } else {
            xIndex = 0;
        }
        return xIndex;
   }
}
