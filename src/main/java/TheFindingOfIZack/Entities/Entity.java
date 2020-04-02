package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;

import java.awt.*;


/**
 * Created by Ben Allan
 */
public abstract class Entity implements Drawable, Savable{

    /**
     * Fields for location, bounding box, and width
     */

    protected TheFindingOfIZack.Util.Point location;
    protected transient BoundingBox box;

    public static int DEFAULT_WIDTH = 40;
    public int width = DEFAULT_WIDTH;

    public Entity(){}

    public Entity(Player p){
        this.location = p.location;
        setBox();
    }

    /**
     * Constructor for Entity
     * @param location  the location of the entity
     */
    public Entity(TheFindingOfIZack.Util.Point location) {
        this.location = location;
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    /**
     * Returns the location of the entity
     * @return  location
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Draw method for entity
     * @param g graphics object to draw on
     */
    public void draw(Graphics g) {}

    /**
     * Sets the bounding box
     */
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    /**
     * Returns the entity bounding box
     * @return  box
     */
    public BoundingBox getBoundingBox(){
        return box;
    }

    public abstract boolean isDestroyed();

}
