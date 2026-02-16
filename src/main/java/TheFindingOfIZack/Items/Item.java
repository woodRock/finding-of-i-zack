package TheFindingOfIZack.Items;
import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.SimpleBoundingBox;
import TheFindingOfIZack.View.Drawable;
import TheFindingOfIZack.Util.GameDimensions;


import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * Created by allanbenj1 on 26/09/17.
 */
public abstract class Item implements Drawable, Savable{

    protected Point location;
    protected String type;
    protected transient SimpleBoundingBox box;
    protected int width = 40;
    protected boolean collected = false;

    protected Player player;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setBox();
    }

    public Item(String type, Player p) {
        this.type = type;
        location = new Point((GameDimensions.GAME_WIDTH/2)-(width/2), (GameDimensions.GAME_HEIGHT/2)-(width/2));
        box = new SimpleBoundingBox(location.getX(), location.getY(), width, width);
        this.player = p;
    }

    public Point getLocation() {
        return location;
    }

    public void setBox() {
        this.box = new SimpleBoundingBox(location.getX(), location.getY(), width, width);
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
