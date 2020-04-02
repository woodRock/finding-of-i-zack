package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import java.awt.*;

/**
 * Created by allanbenj1 on 26/09/17.
 */
public class Key extends Item implements Savable {

    private static Image keyImage;

    public Key(Player p) {
        super("key", p);
        this.keyImage = ImageLoader.loadImage("/doorKey.png");
    }

    /**
     * Method for drawing the item
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(keyImage, (int) location.getX(), (int) location.getY(), null);
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
            player.addKey();
        }
    }
}
