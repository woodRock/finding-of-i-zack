package TheFindingOfIZack.View;

import java.awt.*;

/**
 *  This allows an object to be drawn by the view
 *
 *  @author Bryn Bennett bennetbryn@ecs.vuw.ac.nz
 */
public interface Drawable {

    /**
     * Draw the object
     * @param g graphics object to draw on
     */
    void draw(Graphics g);
}