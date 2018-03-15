package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Entities.Boss;
import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.bossRoom;

import java.awt.*;

/**
 * Created by gordontheo on 11/10/17.
 * Boss Mob
 */
public class MobBoss extends Mob implements Savable{
    private final int CHANGE_TYPE = 100;
    private final double MAX_SPEED = 4;
    private int moveType = 0;

    private static Image image;
    static {
        image = ImageLoader.loadImage("/anEnemy.png").getScaledInstance(Boss.size,Boss.size, Image.SCALE_DEFAULT);;
    }

    public MobBoss(){
        this.viewRange = 50;
        this.speed = MAX_SPEED;
        this.health = 1000;
        this.damage = 20;
    }

    @Override
    public Point step(Point location, Point player, Room room){
        moveType ++;

        double changeX = (player.getX() - location.getX());
        double changeY = (player.getY() - location.getY());

        if(changeX == 0 && changeY == 0) return location;   //Prevent divide by 0 issues

        double h = Math.hypot(changeX, changeY);
        double a = h / speed;
        double newX = changeX / a;
        double newY = changeY / a;

        if(moveType == CHANGE_TYPE || moveType == CHANGE_TYPE*2 && room instanceof bossRoom){
            bossRoom r = (bossRoom) room;
            Point mobPoint = new Point(location.getX()+50,location.getY()+50);
            r.setEnemiesInRoom(new Enemy(mobPoint,room.getPlayer()));
        }
        if(moveType >  CHANGE_TYPE*2){
            speed = MAX_SPEED/2;
            newX = -newX;
            newY = -newY;
            if(moveType >  CHANGE_TYPE*3){
                moveType = 0;
                speed = MAX_SPEED;
            }
        }

        location.setLocation((newX + location.getX()),(newY + location.getY()));

        return location;
    }

    @Override
    public Image getImage() {
        return image;
    }


    @Override
    public String toString() {
        String string = "The Boss mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }
}
