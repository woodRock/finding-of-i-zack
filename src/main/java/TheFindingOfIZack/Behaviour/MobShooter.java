package TheFindingOfIZack.Behaviour;


import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.World.Rooms.Room;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static TheFindingOfIZack.Entities.Entity.DEFAULT_WIDTH;

/**
 * Created by gordontheo on 29/09/17.
 */
public class MobShooter extends Mob implements Savable{
    private final int PROJECTILE_TICK = 30;
    private final int MOVE_BUFFER = 60;
    private int tick;
    private int stopDistance = 200;
    private List<MobProjectile> projectiles;
    public static Image image;
    static {
        image = ImageLoader.loadImage("/weeOctoBoi.png").getScaledInstance(DEFAULT_WIDTH, DEFAULT_WIDTH, Image.SCALE_DEFAULT);
    }

    public MobShooter(){
        this.viewRange = 600;
        this.speed = 3;
        this.health = 50;
        this.damage = 5;
        this.projectiles = Collections.synchronizedList(new ArrayList<MobProjectile>());
        this.tick = (int)(Math.random()*PROJECTILE_TICK);
    }

    @Override
    /**
     * Shifts the mob's location but stops before getting to player and shoots
     * @param location point containing the mobs location
     * @param player point containing the players location
     * @return new mob Point
     */
    public Point step(Point location, Point player, Room r){
        this.room = r;
        popProjectiles();
        tick++;
        projectile(location,player);
        double range = distanceBetween(location,player);
        if (range < viewRange && range > stopDistance+MOVE_BUFFER || range < stopDistance) {

            double changeX = (player.getX() - location.getX());
            double changeY = (player.getY() - location.getY());

            if(changeX == 0 && changeY == 0) return location;   //Prevent divide by 0 issues

            double h = Math.hypot(changeX, changeY);
            double a = h / speed;
            double newX = changeX / a;
            double newY = changeY / a;

            if(range < stopDistance){
                newX = -newX;
                newY = -newY;
            }

            location.setLocation((newX + location.getX()), (newY + location.getY()));
            }
        return location;
    }

    @Override
    public String toString() {
        String string = "A shooting mob Damage = " + this.damage + " health = " + this.health + " speed = " + this.speed;
        return string;
    }

    public  List<MobProjectile> getProjectile(){
        return projectiles;
    }

    public void popProjectiles() {
        synchronized (projectiles) {
            ArrayList<MobProjectile> temp = new ArrayList<>();
            for (MobProjectile p : projectiles) {
                if (p.isDestroyed()) {
                    temp.add(p);
                }
            }

            for (MobProjectile p : temp) {
                projectiles.remove(p);
            }
        }
    }

    private void projectile(Point location, Point player){
        if(tick > PROJECTILE_TICK){
            projectiles.add(new MobProjectile(location, player, room));
            tick = 0;
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}
