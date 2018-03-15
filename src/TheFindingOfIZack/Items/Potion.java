package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Ben Allan
 */
public class Potion extends Item implements Savable{

    private static Image potionImage;

    private int health = 25;

    public Potion(Player p) {
        super("potion", p);
        this.potionImage = ImageLoader.loadImage("/potion.png");
    }

    /**
     * Method for drawing the item
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(potionImage, (int) location.getX(), (int) location.getY(), null);
    }

    /**
     * Updates the Item
     * If the player is touching the item then the item calls its method on the player
     * Collected is set to true so it can be removed from the room.
     */
    @Override
    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width)) {
            if (player.getHealth() == player.getMaxHealth()) {return;}
            collected = true;
            player.heal(health);
        }
    }

}
