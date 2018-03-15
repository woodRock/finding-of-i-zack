package TheFindingOfIZack.Items;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Ben Allan
 */
public class Armour extends Item implements Savable {

    private static transient Image armourImage;

    private int armour = 5;

    public Armour(Player p) {
        super("armour", p);
        this.armourImage = ImageLoader.loadImage("/armour.png");
    }

    /**
     * Method for drawing the item
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(armourImage, (int) location.getX(), (int) location.getY(), null);
    }

    /**
     * Updates the Item
     * If the player is touching the item then the item calls its method on the player
     * Collected is set to true so it can be removed from the room.
     */
    @Override
    public void update() {
        if (box.intersects(player.getLocation().getX(), player.getLocation().getY(), player.width, player.width) && !collected) {
            if (player.getArmour() == player.getMaxArmour()) {return;}
            collected = true;
            player.addArmour(armour);
        }
    }

}
