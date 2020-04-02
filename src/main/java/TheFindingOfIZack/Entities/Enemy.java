package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Behaviour.MobEnemy;
import TheFindingOfIZack.Behaviour.MobProjectile;
import TheFindingOfIZack.Behaviour.MobShooter;
import TheFindingOfIZack.Behaviour.MobType;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.World.Rooms.Room;
import javafx.geometry.BoundingBox;

import java.awt.*;

import static TheFindingOfIZack.Util.GameDimensions.*;


/**
 * Created by Ben Allan
 */
public class Enemy extends Entity {

    protected MobEnemy behaviour;
    protected double health;
    protected double MAX_HEALTH;
    protected Player player;
    protected boolean isDead = false;
    private final int DAMAGE_TICK = 20;
    protected int tick;
    protected Room r;

    /**
     * constructor for Enemy, randomly assigns the enemies' type, assigns health and behaviour accordingly
     * @param location where the mob is to be spawned
     * @param p the player
     */
    public Enemy(Point location, Player p) {
        super(location);
        this.player = p;
        this.r = p.getRoom();

        if (this instanceof Boss) {
            this.behaviour = new MobEnemy(MobType.Boss);
        }
        else {
            this.behaviour = new MobEnemy(MobType.generateRandomMob());
        }

        this.health = behaviour.getHealth();
        this.MAX_HEALTH = behaviour.getHealth();
    }

    public void damage(int damage) {
            this.health -= damage;
            if (this.health <= 0) {isDead = true;}
    }

    public void damagePlayer(){
        if(tick > DAMAGE_TICK) {
            this.player.damage(this.behaviour.getDamage()); //Takes the damage value from each mob type
            tick = 0;
        }
    }
    @Override
    public boolean isDestroyed() {return isDead;}

    @Override
    public void draw(Graphics g) {
        if (this.behaviour.getMob() instanceof MobShooter) {
            MobShooter m = (MobShooter) behaviour.getMob();
            drawProjectiles(m,g);
        }
        g.drawImage(behaviour.getMob().getImage(), (int) location.getX(), (int) location.getY(), null);


        double red = ((MAX_HEALTH-health)/MAX_HEALTH)*255;
        if (red < 0) {red = 0;}
        else if (red > 255) {red = 255;}
        double green = (health/MAX_HEALTH)*255;
        if (green < 0) {green = 0;}
        else if (green > 255) {green = 255;}
        Color c = new Color((int)red, (int) green, 0);

        double healthBar = ((double) health/(double) MAX_HEALTH) * (double) width;
        if (healthBar < 0) {healthBar = 0;}

        g.setColor(c);
        g.fillRect((int) location.getX(), (int) location.getY() - 8, (int) healthBar, 4);
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY() - 8, width, 4);

    }

    /**
     * Makes each mob run through its step method
     * Also checks for edge of map collisions
     * Also checks to see if the mob has touched the player
     */
    public void move() {
        tick ++;
        Point playerPoint = player.getLocation();
        wallCheck();
        location = this.behaviour.step(location, playerPoint, r);
        setBox();
        if(collision(this.getBoundingBox())){
            damagePlayer();
        }
    }


    /**
     * Determines if the mob has collided with a wall
     */
    protected void wallCheck(){
        double x = location.getX();
        double y = location.getY();

        if(y < TOP_WALL){y = TOP_WALL;}
        if(x < LEFT_WALL){x = LEFT_WALL;}
        if(y+width > BOTTOM_WALL){y = BOTTOM_WALL-width;}
        if(x+width > RIGHT_WALL){x = RIGHT_WALL-width;}
        location.move(x,y);
    }

    /**
     * Determines if the player and any mob have collided
     * @param b BoundingBox BondingBox of the Enemy
     */
    public boolean collision(BoundingBox b) {
        return b.intersects(this.player.getBoundingBox());
    }

    public void drawProjectiles(MobShooter m, Graphics g){
        synchronized (m.getProjectile()) {
            for (MobProjectile i : m.getProjectile()) {
                i.move();
                i.setBox();
                i.draw(g);
                if (collision(i.getBoundingBox())) {
                    i.pop();
                    damagePlayer();
                }
            }
        }
    }

    public MobEnemy getType(){
        return behaviour;
    }
}
