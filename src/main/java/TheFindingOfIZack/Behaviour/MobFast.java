package TheFindingOfIZack.Behaviour;


import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;

import java.awt.*;

import static TheFindingOfIZack.Entities.Entity.DEFAULT_WIDTH;

/**
 * Created by gordontheo on 19/09/17.
 */
public class MobFast extends Mob implements Savable{
    public static Image image;
    static {
        image = ImageLoader.loadImage("/kookyCrabbyKid.png").getScaledInstance(DEFAULT_WIDTH,DEFAULT_WIDTH,Image.SCALE_DEFAULT);
    }

    MobFast(){
        this.viewRange = 40;
        this.speed = 4.5;
        this.health = 20;
        this.damage = 5;
    }

    @Override
    public String toString() {
        String string = "A fast mob. Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
    @Override
    public Image getImage() {
        return image;
    }
}
