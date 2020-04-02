package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.Savable;

/**
 * Created by fieldryan on 19/09/17.
 */
public class startRoom extends Room implements Savable{

    public startRoom(){
        super();
    }

    @Override
    public void populateRoom(Player p) {

    }

    public void update(){
        if(this.getNorthDoor() != null){
            this.getNorthDoor().isLocked = false;
        }
        if(this.getEastDoor() != null){
            this.getEastDoor().isLocked = false;
        }
        if(this.getSouthDoor() != null){
            this.getSouthDoor().isLocked = false;
        }
        if(this.getWestDoor() != null){
            this.getWestDoor().isLocked = false;
        }

        checkCollected();

    }

    @Override
    public void addDoor(Door d, int n) {
        if(this.northDoor == null && n == 0){
            this.northDoor = d;
        }
        if(this.eastDoor == null && n == 1){
            this.eastDoor = d;
        }
        if(this.southDoor == null && n == 2){
            this.southDoor = d;
        }
        if(this.westDoor == null && n == 3){
            this.westDoor = d;
        }

    }

}
