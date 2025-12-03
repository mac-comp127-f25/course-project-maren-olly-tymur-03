package slimekoban;

import java.awt.Color;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;



public class Slime {
    private Point slimePos;
    private final double SLIME_SIZE = 40;
    private Rectangle slimeRectangle;

    public Slime(Point slimePos) {
        this.slimePos = slimePos;
        slimeRectangle = new Rectangle(slimePos.getX(), slimePos.getY(), SLIME_SIZE, SLIME_SIZE);
        slimeRectangle.setFillColor(Color.WHITE);
        slimeRectangle.setStrokeColor(Color.WHITE);
    }

    public Rectangle getGraphics() {
        return slimeRectangle;
    }

    public void moveSlime(Point position) {
        slimeRectangle.setPosition(position);
    }
} 

