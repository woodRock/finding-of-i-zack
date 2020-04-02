package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import java.awt.*;

import static TheFindingOfIZack.Entities.Entity.DEFAULT_WIDTH;

/**
 * Created by gordontheo on 19/09/17.
 * The staple enemy, will remain still until the player enters its field of view.
 * After this it will follow the player at slow speed and cause damage if it touches
 */
public class MobSlow extends Mob implements Savable{
    public static Image image;
    static {
        image = ImageLoader.loadImage("/painfulPointyPerson.png").getScaledInstance(DEFAULT_WIDTH,DEFAULT_WIDTH, Image.SCALE_DEFAULT);
    }
    MobSlow(){
        this.viewRange = 200;
        this.speed = 2;
        this.health = 100;
        this.damage = 20;
    }

    @Override
    public String toString() {
        String string = "A slow mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
    @Override
    public Image getImage() {
        return image;
    }
}
