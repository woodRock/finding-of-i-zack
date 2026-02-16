package TheFindingOfIZack.World.Rooms;

import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by fieldryan on 27/09/17.
 */
public class Door implements Drawable, Savable {

    private transient Image openDoorImage;
    private transient Image closedDoorImage;
    private transient Image lockedDoorImage;

    private Room entry;
    private Room destination;
    int position;
    public static final int height = 50;
    public boolean isLocked;
    public boolean bossDoor;
    public boolean needsKey;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initialiseOPenImage();
        initialiseClosedImage();
        if (destination instanceof bossRoom) {
            this.lockedDoorImage = ImageLoader.loadImage("/lockedDoor.png");
        }
    }



    public Door(Room entry, Room destination, int position){
        this.entry = entry;
        this.destination = destination;
        this.position = position;
        this.isLocked = true;
        if(destination instanceof bossRoom){
            this.bossDoor = true;
            this.needsKey = true;
           this.lockedDoorImage = ImageLoader.loadImage("/lockedDoor.png");
        }else{
            this.bossDoor = false;
            this.needsKey = false;
        }
        initialiseOPenImage();
        initialiseClosedImage();
    }

    public Door(Door d){
        if (d == null)
            return;
        this.entry = d.entry;
        this.destination = d.destination;
        this.position = d.position;
        this.isLocked = d.isLocked;
        if(destination instanceof bossRoom){
            this.bossDoor = true;
            this.needsKey = true;
            this.lockedDoorImage = ImageLoader.loadImage("/lockedDoor.png");
        }else{
            this.bossDoor = false;
            this.needsKey = false;
        }
        initialiseOPenImage();
        initialiseClosedImage();
    }

    /**
     * gets the destination room of the door
     * @return destination Room
     */
    public Room getDestination(){
        return this.destination;
    }

    /**
     * gets the position that is opposite to the current one eg if this was north this returns south
     * @return door that should be used in the destination room
     */
    public Door getOpposite(){
        Door d;
        int w = 0;
        if(this.position == 0){
            w = 2;
        }else if(this.position == 1){
            w = 3;
        }else if(this.position == 2){
            w = 0;
        }else if(this.position == 3){
            w = 1;
        }
    d = new Door(this.destination,this.entry,w);


        return d;
    }

    /**
     * gets the position of this door
     * @return the position of this door
     */
    public int getPosition(){
        return this.position;
    }

    @Override
    public void draw(Graphics g) {

        // if the door is locked but is not a boss door
        if(this.isLocked && !this.bossDoor){

            g.setColor(Color.red);
            if(this.position == 3){
                //g.fillRect(0,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(closedDoorImage,0, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);

            }
            if(this.position == 2){
                g.drawImage(closedDoorImage, GameDimensions.GAME_WIDTH/2 - height/2, GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH,null);
               // g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH, height,GameDimensions.WALL_WIDTH);
            }
            if(this.position == 0){

                //g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,0, height,GameDimensions.WALL_WIDTH);
                g.drawImage(closedDoorImage, GameDimensions.GAME_WIDTH/2 - height/2,0,null);
            }
            if(this.position == 1){
                //g.fillRect(GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(closedDoorImage, GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);
            }

         // if the door is not locked and is not a boss door
        }else if(!this.isLocked && !this.bossDoor){
            g.setColor(Color.green);
            if(this.position == 3){
                //g.fillRect(0,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(openDoorImage,0, GameDimensions.GAME_HEIGHT/2 - this.height/2,null);

            }
            if(this.position == 2){
                g.drawImage(openDoorImage, GameDimensions.GAME_WIDTH/2 - height/2, GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH,null);
                // g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH, height,GameDimensions.WALL_WIDTH);
            }
            if(this.position == 0){

                //g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,0, height,GameDimensions.WALL_WIDTH);
                g.drawImage(openDoorImage, GameDimensions.GAME_WIDTH/2 - height/2,0,null);
            }
            if(this.position == 1) {
                //g.fillRect(GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH,GameDimensions.GAME_HEIGHT/2 - this.height/2,GameDimensions.WALL_WIDTH,height);
                g.drawImage(openDoorImage, GameDimensions.GAME_WIDTH - GameDimensions.WALL_WIDTH, GameDimensions.GAME_HEIGHT / 2 - this.height / 2, null);
            }
         // if the door is a boss door and requires a key
        }else if(this.bossDoor){
            if(this.position == 2){
                g.drawImage(lockedDoorImage, GameDimensions.GAME_WIDTH/2 - height/2, GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH,null);
                // g.fillRect(GameDimensions.GAME_WIDTH/2 - height/2,GameDimensions.GAME_HEIGHT - GameDimensions.WALL_WIDTH, height,GameDimensions.WALL_WIDTH);
            }
        }



    }

    /**
     * should be called when a key is used to open the door meaning a key is no longer needed
     */
    public void useKey(){
        this.bossDoor = false;
    }




    private void initialiseOPenImage() {
        if(this.position == 0) {
            openDoorImage = ImageLoader.loadImage("/openDoorTop.png");
        }
        if(this.position == 1) {
            openDoorImage = ImageLoader.loadImage("/openDoorLeft.png");
        }
        if(this.position == 2) {
            openDoorImage = ImageLoader.loadImage("/openDoorDown.png");
        }
        if(this.position == 3) {
            openDoorImage = ImageLoader.loadImage("/openDoorRight.png");
        }
    }

    private void initialiseClosedImage() {
        if(this.position == 0) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorTop.png");
        }
        if(this.position == 1) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorRight.png");
        }
        if(this.position == 2) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorDown.png");
        }
        if(this.position == 3) {
            closedDoorImage = ImageLoader.loadImage("/closedDoorLeft.png");
        }
    }
}
