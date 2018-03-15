package TheFindingOfIZack.Entities;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.World.Rooms.Room;

public abstract class AbstractPlayer extends Entity implements Savable {
    protected AbstractPlayer(Point location){
        super(location);
    }

    public AbstractPlayer(Player p){
        super(p);
    }


    /**
     * Move the player to the south / down
     */
    public abstract void moveSouth();

    /**
     * Move the player to the north / up
     */
    public abstract void moveNorth();

    /**
     * Move the player east / right
     */
    public abstract void moveEast();

    /**
     * Move the player west / left
     */
    public abstract void moveWest();

    /**
     * Tell the player to fire projectile North / Up
     */
    public abstract void shootUp();
    /**
     * Tell the player to fire projectile South / Down
     */
    public abstract void shootDown();
    /**
     * Tell the player to fire projectile West / Left
     */
    public abstract void shootLeft();
    /**
     * Tell the player to fire projectile East / right
     */
    public abstract void shootRight();

    /**
     * Get the room the player is in
     */
    public abstract Room getRoom();

    /**
     * Give the player a room it belongs to
     * @param r the Room to add
     */
    public abstract void setRoom(Room r);

    /**
     * Return the maximum health of the player
     */
    public abstract int getMaxHealth();

    /**
     * Get the current health of the player
     * @return int value of health
     */
    public abstract int getHealth();

    /**
     * Get the current armour of the player
     * @return int value of armour
     */
    public abstract int getArmour();

    /**
     * Get the max armour of the player
     * @return in value of max armour
     */
    public abstract int getMaxArmour();

    /**
     * Check if the player has a key
     * @return true if has key false otherwise
     */
    public abstract boolean getKey();

    /**
     * Check if the player is dead
     * @return  true if dead
     */
    public abstract boolean isDestroyed();

}
