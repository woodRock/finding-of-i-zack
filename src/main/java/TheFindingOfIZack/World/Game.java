package TheFindingOfIZack.World;
import TheFindingOfIZack.Entities.AbstractPlayer;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.Savable;
import TheFindingOfIZack.World.Rooms.*;

import java.util.ArrayList;
import java.util.Observable;


/**
 * Created by fieldryan on 19/09/17.
 * Stores all the objects within the game world
 */

public class Game extends Observable implements Model,Savable{


    private transient Player player;
    private transient Level currentLevel;
    private boolean running = true;
    private int frameCount;
    private boolean north;
    private boolean east;
    private boolean south;
    private boolean west;

    private boolean shootnorth;
    private boolean shooteast;
    private boolean shootsouth;
    private boolean shootwest;


    private boolean paused = false;

    private boolean isWon = false;
    private boolean isLost = false;


    /**
     * Constructor for creating a brand new game from the beginning
     * @param p
     */
    public Game(Player p){
        this.isLost = false;
        this.isWon = false;
        this.player = p;
        this.frameCount = 0;
        this.north = false;
        this.east = false;
        this.south = false;
        this.west = false;

        this.shootnorth = false;
        this.shooteast = false;
        this.shootsouth = false;
        this.shootwest = false;

    }

    /**
     *Constructor for loading a new game from a file
     * @param
     */
    public Game(Game g, Player p, Level l, Room r, ArrayList<Door> d) {
        this.player = p;
        this.player.setRoom(r);
        this.currentLevel = l;
        this.frameCount = g.frameCount;
        this.north = g.north;
        this.east = g.east;
        this.south = g.south;
        this.west = g.west;
    }

    public Level getLevel(){ return currentLevel; }


    /**
     * begins the logic of a brand new game
     */
    public void beginNewGame(){
        createLevelOne();
        runGameLoop();
    }

    @Override
    public void resumeGame(){
        paused = false;
        runGameLoop();
    }

    @Override
    public void pauseGame() {
        paused = true;
    }

    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    /**
     *
     * @return the the player object
     */
    public AbstractPlayer getPlayer(){

        return this.player;
    }

    /**
     * Move the player Up and trigger update of view
     */
    private void moveUp() {
        player.moveUp();
        drawGame();
    }

    /**
     * Move the player down and trigger update of view
     */
    private void moveDown() {
    player.moveDown();
        drawGame();
    }

    /**
     * Move the player right and trigger update of view
     */
    private void moveRight() {
    player.moveRight();
        drawGame();
    }

    /**
     * Move the player left and trigger update of view
     */
    private void moveLeft() {
    player.moveLeft();
        drawGame();
    }

    @Override
    public void moveUp(boolean b) {
       this.north = b;

    }
    @Override
    public void moveRight(boolean b) {
        this.east = b;

    }
    @Override
    public void moveDown(boolean b) {
        this.south = b;

    }
    @Override
    public void moveLeft(boolean b) {
        this.west = b;

    }


    @Override
    public void shootLeft(boolean b) {
        this.shootwest = b;
        this.shootnorth = false;
        this.shooteast = false;
        this.shootsouth = false;
    }

    @Override
    public void shootUp(boolean b) {
        this.shootwest = false;
        this.shootnorth = b;
        this.shooteast = false;
        this.shootsouth = false;
    }

    @Override
    public void shootRight(boolean b) {
        this.shootwest = false;
        this.shootnorth = false;
        this.shooteast = b;
        this.shootsouth = false;
    }


    @Override
    public void shootDown(boolean b) {
        this.shootwest = false;
        this.shootnorth = false;
        this.shooteast = false;
        this.shootsouth = b;
    }

    @Override
    public boolean isGameLost() {
        return !running && isLost;
    }

    @Override
    public boolean isGameWon() {
        return !running && isWon;
    }

    /**
     * Start or stop shooting in the left direction
     */
    private void shootLeft() {
        player.shootLeft();
        drawGame();
    }

    /**
     * Start or stop shooting in the right direction
     */
    private void shootRight() {
        player.shootRight();
        drawGame();
    }

    /**
     * Start or stop shooting in the up direction
     */
    private void shootUp() {
        player.shootUp();
        drawGame();
    }

    /**
     * Start or stop shooting in the down direction
     */
    private void shootDown() {
        player.shootDown();
        drawGame();
    }




    /**
     * updates all of the components within the game
     */
    public void updateGame(){


        checkMovement();
        checkShooting();
        player.getRoom().update();
        player.update();

        checkDead();
        checkWon();
    }
    private void checkWon(){
        if(player.hasWon()){
            running = false;
            this.isWon = true;
        }
    }
    private void checkDead(){
        if(player.isDestroyed()){
            running = false;
            this.isLost = true;
        }
    }

    /**
     * checks the movement fields to determine which direction the player needs to move during the current tick
     */
    private void checkMovement(){
        if(north){
            moveUp();
        }
        if(east){
            moveRight();
        }
        if(south){
            moveDown();
        }
        if(west){
            moveLeft();
        }
    }

    /**
     * checks the shooting fields to determine which direction the player needs to shoot during the current tick
     */
    private void checkShooting(){
        if(shootnorth){
            shootUp();
        }
        if(shooteast){
            shootRight();
        }
        if(shootsouth){
            shootDown();
        }
        if(shootwest){
            shootLeft();
        }

    }

    /**
     * creates the first level of the game
     */
    public void createLevelOne(){
        this.currentLevel = new Level();

        Room r1 = new startRoom();
        Room r2 = new standardRoom();
        Room r3 = new standardRoom();
        Room r4 = new itemRoom();
        Room r5 = new standardRoom();
        Room r6 = new standardRoom();
        Room r7 = new standardRoom();
        Room r8 = new bossRoom();

        //Door connecting rooms 1 and 2
        Door r1t2 = new Door(r1,r2,3);
        r1.addDoor(r1t2,r1t2.getPosition());
        r2.addDoor(r1t2.getOpposite(),r1t2.getOpposite().getPosition());

        //Door connecting rooms 1 and 3
        Door r1t3 = new Door(r1,r3,2);
        r1.addDoor(r1t3,r1t3.getPosition());
        r3.addDoor(r1t3.getOpposite(),r1t3.getOpposite().getPosition());

        //Door connecting rooms 3 and 4
        Door r3t4 = new Door(r3,r4,1);
        r3.addDoor(r3t4,r3t4.getPosition());
        r4.addDoor(r3t4.getOpposite(),r3t4.getOpposite().getPosition());


        //Door connecting rooms 3 and 5
        Door r3t5 = new Door(r3,r5,2);
        r3.addDoor(r3t5,r3t5.getPosition());
        r5.addDoor(r3t5.getOpposite(),r3t5.getOpposite().getPosition());

        //Door connecting rooms 5 and 6
        Door r5t6 = new Door(r5,r6,2);
        r5.addDoor(r5t6,r5t6.getPosition());
        r6.addDoor(r5t6.getOpposite(),r5t6.getOpposite().getPosition());

        //Door connecting rooms 5 and 7
        Door r5t7 = new Door(r5,r7,1);
        r5.addDoor(r5t7,r5t7.getPosition());
        r7.addDoor(r5t7.getOpposite(),r5t7.getOpposite().getPosition());

        //Door connecting rooms 7 and 8
        Door r7t8 = new Door(r7,r8,2);
        r7.addDoor(r7t8,r7t8.getPosition());
        r8.addDoor(r7t8.getOpposite(),r7t8.getOpposite().getPosition());


        player.setRoom(r1);
        currentLevel.addRoom(r1);
        currentLevel.addRoom(r2);
        currentLevel.addRoom(r3);
        currentLevel.addRoom(r4);
        currentLevel.addRoom(r5);
        currentLevel.addRoom(r6);
        currentLevel.addRoom(r7);
        currentLevel.addRoom(r8);


    }


    /**
     * begin the game ticks
      */
    public void runGameLoop() {
        Thread loop = new Thread()
        {
            public void run()
            {
                gameLoop();
            }
        };
        loop.start();
    }


    /**
     * Tick method created by Eli Delventhal at http://www.java-gaming.org/index.php?topic=24220.0
     * Tells the game what to do every tick and ensures everything runs smoothly
     */

    private void gameLoop()
    {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 1;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);


        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            //Do as many game updates as we need to, potentially playing catchup.
            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
            {

                updateGame();
                lastUpdateTime += TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
            {
                lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            //Render. To do so, we need to calculate interpolation for a smooth render.
            drawGame();

            lastRenderTime = now;

            //Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
               // System.out.println("NEW SECOND " + thisSecond + " " + frameCount);

                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
                Thread.yield();

                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                try {Thread.sleep(1);} catch(Exception e) {}

                now = System.nanoTime();
            }

            /* Stop the game loop if pause*/
            if(paused){
                return;
            }
        }
    }

    /**
     * draws everything within the game
     */
    private void drawGame(){
        this.setChanged();
        notifyObservers();

    }
}
