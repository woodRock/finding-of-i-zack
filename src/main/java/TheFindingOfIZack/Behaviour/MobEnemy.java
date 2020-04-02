package TheFindingOfIZack.Behaviour;

import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.Room;

/**
 * Created by gordontheo on 27/09/17.
 * This class is used to create new mobs and specify what type they are
 */
public class MobEnemy implements Savable {
    private Mob mob;


    /**
     * Create a new Mob Enemy containing a type of mob
     * @param m enum indicating the mob type
     */
    public MobEnemy(MobType m) {
        if (m == null){ mobTypeError("Null mob"); return;}
        this.mob = m.constructMob();
    }

    /**
     * Returns health f
     * @return health
     */
    public int getHealth(){
        return mob.getHealth();
    }

    public int getDamage(){
        return mob.getDamage();
    }

    public double getSpeed() {return mob.getSpeed();}

    /**
     * Throws an error if an invalid mobType is called
     * @param str the invalid type name entered
     */
    private void mobTypeError(String str){
        System.err.print("Error: Invalid mob type \"" + str + "\"\n");
    }

    public Point step(Point location, Point player, Room r){
        return(mob.step(location,player,r));
    }

    public Mob getMob(){
        return mob;
    }

}