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

    // reurns the Y index of where the crate is supossed to be the the cells arraylist
   public Integer getYCrateLocation() {
        Integer yIndex = Integer.valueOf((int) position.getY());
        if (yIndex >= 30) {
            yIndex /= 30;
        } else {
            yIndex = 0;
        }
        return yIndex;
   }

   // reurns the X index of where the crate is supossed to be the the cells arraylist
   public Integer getXCrateLocation() {
        Integer xIndex = Integer.valueOf((int) position.getX());
        if (xIndex >= 30) {
            xIndex /= 30;
        } else {
            xIndex = 0;
        }
        return xIndex;
   }
    
}
