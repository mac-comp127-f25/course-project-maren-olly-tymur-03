package slimekoban;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

import java.awt.Canvas;
import java.awt.Color;
import edu.macalester.graphics.Point;

public class Crate extends Rectangle {

    private static final Color CRATE_COLOR = new Color(139, 69, 19); 
    private Point position;

    public Crate(double width, double height, Point position) {
        super(position.getX(), position.getY(), width, height);
        setFillColor(CRATE_COLOR);
        setStrokeColor(Color.BLACK);
    }
    
}
