package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.Entities.Urn;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Items.Key;

import java.awt.*;

/**
 * Created by fieldryan on 19/09/17.
 *
 */
public class itemRoom extends Room implements Savable {


    private boolean populated;


    public itemRoom(){
        super();
        this.populated = false;
    }

    @Override
    public void populateRoom(Player p) {
        if(populated){
            return;
        }
        int numUrns =  7;

        for(int i = 0; i < numUrns; i++){
            Point point = randomPoint();
            if (point == null) continue;    //point not created for some reason
            items.add(new Urn(point, p));
        }



        this.getCollectibles().add(new Key(p));
        this.populated = true;
    }

    public void draw(Graphics g){
        super.draw(g);

        synchronized (items) {
            items.forEach(e -> e.draw(g));
        }
    }




    @Override
    public void update() {
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

        for (Item i : this.getCollectibles()) {
            i.update();
        }

        checkCollected();

    }
}
