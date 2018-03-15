package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Items.Armour;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Items.Potion;
import TheFindingOfIZack.Items.Weapon;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.World.Rooms.itemRoom;

import java.awt.*;

/**
 * Created by allanbenj1 on 29/09/17.
 */
public class Urn extends Entity implements Savable{

    /**
     * Fields to store Urn image, player, item, if it is destroyed
     */

    private static Image urnsImage;

    private Player p;

    private Item item = null;

    private boolean destroyed = false;

    int health = 15;

    static {
        urnsImage = ImageLoader.loadImage("/pot.png").getScaledInstance(DEFAULT_WIDTH,DEFAULT_WIDTH,Image.SCALE_DEFAULT);
    }

    /**
     * Constructor takes a Point and a player
     * @param location  where the urn is
     * @param p the player associated with the urn
     */
    public Urn(Point location, Player p) {
        super(location);
        this.p = p;

        if (p.getRoom() instanceof itemRoom) {
            int random = (int) (Math.random()*100);
            if (random <= 10) {item = new Potion(p);}
            else if (random <= 55) {item = new Armour(p);}
            else {item = new Weapon(p);}
            item.setLocation(location);
            item.setBox();
        }
        else {
            int random = (int) (Math.random()*100);
            if (random >= 25) {
                random = (int) (Math.random()*100);
                if (random <= 33) {item = new Armour(p);}
                else if (random <= 66) {item = new Weapon(p);}
                else {item = new Potion(p);}
                item.setLocation(location);
                item.setBox();
            }
        }

    }

    /**
     * Damages the urn
     * If health <= 0 the urn is destroyed and the item is dropped (if the urn has an item)
     * @param damage    the amount of damage done to the urn
     */
    public void damage(int damage) {
        this.health -= damage;
        if (health <= 0) {
            health = 0;
            destroyed = true;
            if (item != null) {
                p.getRoom().getCollectibles().add(item);
            }
        }
    }

    /**
     * Draws the urn
     * @param g the graphics object that draws the urn
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(urnsImage, (int) location.getX(), (int) location.getY(), null);

    }

    /**
     * Returns whether the urn is destroyed
     * @return  boolean destroyed
     */
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }


    public int getHealth() {
        return health;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
