package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import java.awt.*;

/**
 * Created by Ben Allan
 */
public class Weapon extends Item implements Savable {

    private static Image weaponImage;

    static {
        weaponImage = ImageLoader.loadImage("/sword.png");
    }

    public Weapon(Player p) {
        super("weapon", p);
    }

    /**
     * Method for drawing the item
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(weaponImage, (int) location.getX(), (int) location.getY(), null);
    }

    /**
     * Updates the Item
     * If the player is touching the item then the item calls its method on the player
     * Collected is set to true so it can be removed from the room.
     */
    @Override
    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            collected = true;
            player.weaponUpgrade();
        }
    }

}
