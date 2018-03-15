package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Util.Point;
import javafx.geometry.BoundingBox;

import java.awt.*;

/**
 * Created by allanbenj1 on 11/10/17.
 */
public class Boss extends Enemy {

    /**
     * Stores the field for the size of the Boss
     */
    public static int size = 120;

    private double armour = 500;
    private double MAX_ARMOUR = 500;

    /**
     * Constructor for the boss
     * @param location  the location of the boss
     * @param p the player associated with the boss
     */
    public Boss(TheFindingOfIZack.Util.Point location, Player p) {
        super(location, p);
        width = 120;
    }

    /**
     * Sets the bounding box for the boss
     */
    @Override
    public void setBox() {
        this.box = new BoundingBox(location.getX(), location.getY(), size, size);
    }

    /**
     * draws the boss
     * @param g the graphics object used to draw the boss
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(behaviour.getMob().getImage(), (int) location.getX(), (int) location.getY(), null);

        double healthBar = (health/MAX_HEALTH) * size;
        if (healthBar < 0) {healthBar = 0;}

        double armourBar = (armour/MAX_ARMOUR) * size;
        if (armourBar < 0) {armourBar = 0;}

        double red = ((MAX_HEALTH-health)/MAX_HEALTH)*255;
        if (red < 0) {red = 0;}
        else if (red > 255) {red = 255;}
        double green = (health/MAX_HEALTH)*255;
        if (green < 0) {green = 0;}
        else if (green > 255) {green = 255;}
        double blue = (armour/MAX_ARMOUR)*255;
        Color healthColor = new Color((int)red, (int) green, 0);
        Color armourColor = new Color(0, 255-(int)blue, (int) blue);

        g.setColor(healthColor);
        g.fillRect((int) location.getX(), (int) location.getY() - 8, (int) healthBar, 4);
        if (armour != 0) {
            g.setColor(armourColor);
            g.fillRect((int) location.getX(), (int) location.getY() - 8, (int) armourBar, 4);
        }
        g.setColor(Color.black);
        g.drawRect((int) location.getX(), (int) location.getY() - 8, size, 4);

    }

    /**
     * Damages the boss
     * @param damage    the amount of damage the boss receives
     */
    @Override
    public void damage(int damage) {
        if (damage <= armour) {armour -= damage; return;}
        else if (armour < damage && armour > 0) {health -= Math.abs(armour-damage); armour = 0; return;}
        this.health -= damage;
        if (this.health <= 0) {isDead = true;}
    }

    /**
     * Moves the boss.
     * This acts in a similar way to enemy however it has minor differences
     */
    @Override
    public void move() {
        tick ++;
        if(health < MAX_HEALTH){
            health += 0.3;
            if (health > MAX_HEALTH){health = MAX_HEALTH;}
        }
        TheFindingOfIZack.Util.Point playerPoint = player.getLocation();
        Point potentialStep = this.behaviour.step(location, playerPoint, r);
        //potentialStep represents the move which will take place if there are no obstacle,
        // also checks if mob is currently touching player
        if(!collision(this.getBoundingBox())) {
            this.location = potentialStep;
            this.box = new BoundingBox(potentialStep.getX(), potentialStep.getY(), this.size, this.size);
            setBox();
        }
        wallCheck();
        if(collision(this.getBoundingBox())){
            damagePlayer();
        }
    }

}
