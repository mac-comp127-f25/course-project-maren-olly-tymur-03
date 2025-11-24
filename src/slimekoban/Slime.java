package slimekoban;

import java.awt.Color;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;

public class Slime {
    private Point slimePos;
    private final double SLIME_SIZE = 20;

    public Slime(Point slimePos) {
        this.slimePos = slimePos;
    }

    public void createSlime(GraphicsGroup graphicsGroup) {
        Rectangle slime = new Rectangle(slimePos.getX(), slimePos.getY(), SLIME_SIZE, SLIME_SIZE);
        slime.setFillColor(Color.WHITE);
        graphicsGroup.add(slime);
    }

}
