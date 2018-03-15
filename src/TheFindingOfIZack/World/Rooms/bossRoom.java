package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Boss;
import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;

import java.awt.*;
import java.util.List;

public class bossRoom extends Room implements Savable {

    private Boss boss;
    private Player player;

    public bossRoom(){
        super();
        this.isCleared = false;
    }

    @Override
    public void populateRoom(Player p) {
        this.boss = new Boss(new Point(100,100),p);
        this.player = p;

    }


    public void draw(Graphics g){
        super.draw(g);
        enemiesInRoom.forEach(e -> e.draw(g));
        if(boss != null && !boss.isDestroyed()){boss.draw(g);}
    }



    @Override
    public void update() {

        synchronized (enemiesInRoom) {
            if (boss.isDestroyed() && enemiesInRoom.isEmpty()) {
                this.isCleared = true;
                player.setWon();
                if (this.northDoor != null) {
                    this.northDoor.isLocked = false;
                }
                if (this.eastDoor != null) {
                    this.eastDoor.isLocked = false;
                }
                if (this.southDoor != null) {
                    this.southDoor.isLocked = false;
                }
                if (this.westDoor != null) {
                    this.westDoor.isLocked = false;
                }


            }
            for (Enemy e : enemiesInRoom) {
                if (e.isDestroyed()) {
                    this.deadEnemies.add(e);
                }
            }
            if (!enemiesInRoom.isEmpty()) {
                for (Enemy e : deadEnemies) {
                    enemiesInRoom.remove(e);
                }
            }
            for (Enemy e : enemiesInRoom) {
                e.move();
            }
        if(!boss.isDestroyed()) {
            boss.move();
        }

            checkCollected();
        }

    }

    /**
     * used to add enemies to the room after the initial population such as the boss spawning enemies
     * @param enemy to be added
     */
    public void setEnemiesInRoom(Enemy enemy){
        synchronized (enemiesInRoom) {
            this.enemiesInRoom.add(enemy);
        }
    }

    /**
     * gets the boss within the room
     * @return the boss
     */
    public Boss getBoss(){
        return this.boss;
    }

    /**
     * gets all enemies in the room that are not a boss
     * @return enemies within the room
     */
    public List<Enemy> getEnemies(){
        return this.enemiesInRoom;
    }
}
