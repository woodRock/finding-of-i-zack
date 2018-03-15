package TheFindingOfIZack.World.Rooms;


import TheFindingOfIZack.Entities.Enemy;
import TheFindingOfIZack.Entities.Entity;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.Items.Item;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.ImageLoader;
import TheFindingOfIZack.View.Drawable;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static TheFindingOfIZack.Entities.Entity.DEFAULT_WIDTH;

/**
 * Created by fieldryan on 19/09/17.
 * Interface for the general definition of a room
 */
public abstract class Room implements Drawable, Savable {

    private static Image roomImage;

    protected List<Item> collectibles;
    protected List<Enemy> enemiesInRoom;
    protected List<Enemy> deadEnemies;

    protected List<Entity> items;

    protected Door northDoor;
    protected Door eastDoor;
    protected Door southDoor;
    protected Door westDoor;

    public boolean isCleared;

    private Player player;

    static {
        roomImage = ImageLoader.loadImage("/room.png").getScaledInstance(GameDimensions.GAME_WIDTH, GameDimensions.GAME_HEIGHT,Image.SCALE_DEFAULT);
    }

    public Room() {
        this.enemiesInRoom = new ArrayList<>();
        this.deadEnemies = new ArrayList<>();
        this.items = Collections.synchronizedList(new ArrayList<Entity>());
        this.collectibles = Collections.synchronizedList(new ArrayList<Item>());

        this.player = null;
        this.northDoor = null;
        this.eastDoor = null;
        this.southDoor = null;
        this.westDoor = null;
    }

    public Room(Room r){
        this.collectibles = r.collectibles;
        this.player = r.getPlayer();
        this.northDoor = r.getNorthDoor();
        this.eastDoor = r.getEastDoor();
        this.southDoor = r.getSouthDoor();
        this.westDoor = r.getWestDoor();
        this.roomImage = ImageLoader.loadImage("/room.png").getScaledInstance(GameDimensions.GAME_WIDTH, GameDimensions.GAME_HEIGHT,Image.SCALE_DEFAULT);
        if (r.enemiesInRoom != null){
            this.enemiesInRoom = r.enemiesInRoom;
            this.deadEnemies = r.deadEnemies;
        }
    }

    public List<Item> getCollectibles(){
        return this.collectibles;
    }

    public List<Enemy> getEnemies() {return this.enemiesInRoom;}

    /**
     * Recreate bounding boxes for things in the rooms
     */
    public void recreateBoundingBoxes(){
        enemiesInRoom.forEach(e -> e.setBox());
        deadEnemies.forEach(e -> e.setBox());
        items.forEach(e -> e.setBox());
        collectibles.forEach( e -> e.setBox());
    }
    /**
     * adds a player to the room
     * @param p player to add to the room
     */
    public void addPlayer(Player p){
        this.player = p;
    }


    /**
     * gets the player that is currently in the room
     * @return the current player in the room
     */
    public Player getPlayer(){
        return this.player;
    }


    /**
     * removes the player from the room, should be used when the player exits a room
     */
    public void removePlayer(){
        this.player = null;
    }


    /**
     * draws the room and everything within it
     * @param g graphics object to draw on
     */
    public void draw(Graphics g){


        g.drawImage(roomImage,0,0,null);
        if(this.westDoor!= null){
            this.westDoor.draw(g);
        }
        if(this.southDoor != null){
            this.southDoor.draw(g);
        }
        if(this.northDoor != null){
            this.northDoor.draw(g);
        }
        if(this.eastDoor != null){
            this.eastDoor.draw(g);
        }

        synchronized (this.collectibles) {
            for (Item i : this.collectibles) {
                i.draw(g);
            }
        }

    }

    /**
     * returns the north door
     * @return the north door
     */
    public Door getNorthDoor(){
        return this.northDoor;
    }
    /**
     * returns the south door
     * @return the south door
     */
    public Door getSouthDoor(){
        return this.southDoor;
    }
    /**
     * returns the east door
     * @return the east door
     */
    public Door getEastDoor(){
        return this.eastDoor;
    }
    /**
     * returns the west door
     * @return the west door
     */
    public Door getWestDoor(){
        return this.westDoor;
    }

    public void setNorthDoor(Door d){ this.northDoor = d; }

    public void setEastDoor(Door d) { this.eastDoor = d; }

    public void setSouthDoor(Door d){ this.southDoor = d; }

    public void setWestDoor(Door d){ this.westDoor = d; }



    /**
     * populates the room the first time the player enters it
     * @param p the player entering the room
     */
    public abstract void populateRoom(Player p);

    /**
     * should call the corresponding update method for everything within the room every tick
     */
    public abstract void update();

    /**
     * Checks for any collected items in the room and removes them from the room
     */
    protected void checkCollected() {
        ArrayList<Item> collected = new ArrayList<Item>();
        for (Item i : this.collectibles) {
            if (i.isCollected()) {
                collected.add(i);
            }
        }
        for (Item i : collected) {
            this.collectibles.remove(i);
        }
    }

    /**
     * adds a door to the room
     * @param d the door to add
     * @param n the position of the door 0 = north then increasing clockwise
     */
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

    /**
     * checks if there is a door in the desired directions
     * @param dir the desired direction with 0 being north and increasing clockwise
     * @return true if there is a door in that direction or false if not
     */
    public boolean hasDoor(int dir) {
        //door south
        if(dir == 2){
            if(this.southDoor != null){
                return true;
            }
        }
        //door north
        if(dir == 0){
            if(this.northDoor != null){
                return true;
            }
        }
        //door east
        if(dir == 1){
            if(this.eastDoor != null){
                return true;
            }
        }
        //door west
        if(dir == 3){
            if(this.westDoor != null){
                return true;
            }
        }
        return false;
    }

    protected Point randomPoint(){
        Random r = new Random();

        int left = GameDimensions.LEFT_WALL;  //low x boundary
        int right = GameDimensions.RIGHT_WALL - DEFAULT_WIDTH; //high x boundary, allowing for space after object
        int top = GameDimensions.TOP_WALL;    //low y boundary
        int bottom = GameDimensions.BOTTOM_WALL - DEFAULT_WIDTH;   //high y boundary, allowing for space after object

        int x = r.nextInt(right - left) + left;
        int y = r.nextInt(bottom - top) + top;

        boolean suitable = false;
        int attempts = 0;
        int MAXATTEMPS = 10000;
        while (!suitable && attempts < MAXATTEMPS) {
            //Check if point is blocking a door or potential door
            if (checkPointBlockingDoor(x,y) && checkPointNotOverlap(x,y)){
                suitable = true;
                continue;
            }
            x = r.nextInt(right - left) + left;
            y = r.nextInt(bottom - top) + top;
            attempts++;
        }
        if (attempts >= MAXATTEMPS){
            return null;
        }
        return new Point(x,y);
    }

    /**
     * Check if the given point would block a doorway if it belonged to an entity
     * @param x the x coordinate of point
     * @param y the y coordinate of point
     * @return  true if the door is not blocked false otherwise
     */
    private boolean checkPointBlockingDoor(int x, int y){
        if (x >= GameDimensions.GAME_WIDTH/2 - Door.height/2 - DEFAULT_WIDTH &&
                x <= GameDimensions.GAME_WIDTH/2 + Door.height/2){     //potentially blocking north or south door
            if (y >= GameDimensions.BOTTOM_WALL - DEFAULT_WIDTH*2){  //Blocking south door
                return false;
            }
            else if (y <= GameDimensions.TOP_WALL + DEFAULT_WIDTH){    //Blocking North door
                return false;
            }

        }else if(y >= GameDimensions.GAME_HEIGHT/2 - Door.height/2 - DEFAULT_WIDTH &&
                y <= GameDimensions.GAME_HEIGHT/2 + Door.height/2){     //potentially blocking east or west door
            if (x <= GameDimensions.LEFT_WALL + DEFAULT_WIDTH){
                return false;
            }
            else if(x >= GameDimensions.RIGHT_WALL - DEFAULT_WIDTH*2){
                return false;
            }
        }
        return true;
    }

    /**
     * Check point will not create an entity that overlaps another
     * @param x x position
     * @param y y position
     * @return  true if overlaps with another item or mob
     */
    private boolean checkPointNotOverlap(int x, int y) {

        for (Entity i : items){
            if (i.getBoundingBox().intersects(x,y,x+DEFAULT_WIDTH,y+DEFAULT_WIDTH)) return false;
        }
        for (Entity i : enemiesInRoom){
            if (i.getBoundingBox().intersects(x,y,x+DEFAULT_WIDTH,y+DEFAULT_WIDTH)) return false;
        }
        return true;
    }

    public List<Entity> getItems() {
        return items;
    }
}
