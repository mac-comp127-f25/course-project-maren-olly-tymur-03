package slimekoban;

import java.awt.Color;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;

public class Slime {
    private Point slimePos;
    private final double SLIME_SIZE = 20;
    private double width;
    private double height;
    private Rectangle slimeRectangle;

    public Slime(Point slimePos) {
        this.slimePos = slimePos;
        slimeRectangle = new Rectangle(slimePos.getX(), slimePos.getY(), SLIME_SIZE, SLIME_SIZE);
        slimeRectangle.setFillColor(Color.WHITE);
        slimeRectangle.setFillColor(Color.WHITE);
    }

}
