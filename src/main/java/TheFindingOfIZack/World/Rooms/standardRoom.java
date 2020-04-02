package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.*;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Items.Item;

import java.awt.*;
import java.util.List;


public class standardRoom extends Room implements Savable{


    public standardRoom(){
        super();
        isCleared = false;
    }

    public standardRoom(Room r){
        super(r);
    }

    @Override
    public  void populateRoom(Player player){
        if(this.isCleared){
            return;
        }

        int numRocks = (int) (Math.random() * 10) + 3;
        int numUrns =  (int) (Math.random() * 3) + 1;
        int numEnemies = (int) (Math.random() * 3 + 4);

        for(int i = 0; i < numRocks; i++){
            Point p = randomPoint();
            if (p == null) continue;    //point not created for some reason
            items.add(new Rock(p));
        }

        for(int i = 0; i < numUrns; i++){
            Point p = randomPoint();
            if (p == null) continue;    //point not created for some reason
            items.add(new Urn(p, getPlayer()));
        }


        for(int i = 0; i < numEnemies; i++){
            Point p = randomPoint();
            if (p == null) continue;    //point not created for some reason
            enemiesInRoom.add(new Enemy(p,getPlayer()));
        }
    }

    /**
     * gets all of the entities in the room that are not enemies
     * @return list of non enemy entities
     */
    public List<Entity> getItems() {
        return items;
    }

    @Override
    public void update() {
        if(this.enemiesInRoom.size() == 0){

            this.isCleared = true;
            if(this.northDoor != null){
                this.northDoor.isLocked = false;
            }
            if(this.eastDoor != null){
                this.eastDoor.isLocked = false;
            }
            if(this.southDoor != null){
                this.southDoor.isLocked = false;
            }
            if(this.westDoor != null){
                this.westDoor.isLocked = false;
            }




        }
        for(Enemy e : enemiesInRoom){
            if(e.isDestroyed()){
                this.deadEnemies.add(e);
            }
        }
        for(Enemy e : deadEnemies){
            enemiesInRoom.remove(e);
        }
        for(Enemy e : enemiesInRoom){
            e.move();
        }
        for(Item i: getCollectibles()){
            i.update();
        }

        checkCollected();

    }

    public void draw(Graphics g){
        super.draw(g);
        synchronized (items) {
            items.forEach(e -> e.draw(g));
        }
        enemiesInRoom.forEach( e -> e.draw(g));
    }

    /**
     * gets the enemies in the room
     * @return a list of all enemies in the room
     */
    public List<Enemy> getEnemies(){
        return this.enemiesInRoom;
    }


}