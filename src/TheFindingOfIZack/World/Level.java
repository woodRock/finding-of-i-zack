package TheFindingOfIZack.World;


import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.Room;

import java.util.ArrayList;

/**
 * Created by fieldryan on 19/09/17.
 * stores information about the current level such as what rooms consist of this level
 */
public class Level implements Savable{

    ArrayList<Room> rooms;

    public Level(){
    this.rooms = new ArrayList<Room>();

    }

    public int size() { return rooms.size(); }

    public void addRoom(Room r){
    rooms.add(r);
    }

    public void update(){

        for(Room r: rooms){
            r.update();
        }
    }



}
