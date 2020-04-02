package TheFindingOfIZack.Items;
import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.View.Drawable;
import javafx.geometry.BoundingBox;
import TheFindingOfIZack.Util.GameDimensions;


import java.awt.*;


/**
 * Created by allanbenj1 on 26/09/17.
 */
public abstract class Item implements Drawable, Savable{

    protected Point location;
    protected String type;
    protected transient BoundingBox box;
    protected int width = 40;
    protected boolean collected = false;

    protected Player player;

    public Item(String type, Player p) {
        this.type = type;
        location = new Point((GameDimensions.GAME_WIDTH/2)-(width/2), (GameDimensions.GAME_HEIGHT/2)-(width/2));
        box = new BoundingBox(location.getX(), location.getY(), width, width);
        this.player = p;
    }

    public Point getLocation() {
        return location;
    }

    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), width, width);
    }

    public void update() {}

    public void setLocation(Point p) {
        this.location = p;
    }

    public void draw(Graphics g) {}

    public boolean isCollected() {
        return collected;
    }



}
