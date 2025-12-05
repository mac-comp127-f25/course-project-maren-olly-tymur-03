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

   
}
