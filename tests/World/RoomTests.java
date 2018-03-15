package World;

import Mocks.MockModel;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Rooms.*;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fieldryan on 4/10/17.
 */
public class RoomTests {

    @Test
    public void testBlankRoom(){
        Room r = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };

        assert(r.getEastDoor() == null);
        assert(r.getNorthDoor() == null);
        assert(r.getSouthDoor() == null);
        assert(r.getWestDoor() == null);

    }
    /**
     * checks that a door to the north is added correctly
     */
    @Test
    public void addNorthDoor(){
        Room r1 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Room r2 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Door d = new Door(r1,r2,0);

        r1.addDoor(d,0);
        assert(r1.hasDoor(0));
        assert(r1.getNorthDoor() == d);


    }

    /**
     * checks that a door to the east is added correctly
     */
    @Test
    public void addEastDoor(){
        Room r1 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Room r2 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Door d = new Door(r1,r2,1);

        r1.addDoor(d,1);
        assert(r1.hasDoor(1));
        assert(r1.getEastDoor() == d);


    }
    /**
     * checks that a door to the south is added correctly
     */
    @Test
    public void addSouthDoor(){
        Room r1 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Room r2 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Door d = new Door(r1,r2,2);

        r1.addDoor(d,2);
        assert(r1.hasDoor(2));
        assert(r1.getSouthDoor() == d);


    }

    /**
     * checks that a door to the west is added correctly
     */
    @Test
    public void addWestDoor(){
        Room r1 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Room r2 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Door d = new Door(r1,r2,3);

        r1.addDoor(d,3);
        assert(r1.hasDoor(3));
        assert(r1.getWestDoor() == d);


    }

    /**
     * checks that east doors lead to a west door and vice versa
     */
    @Test
    public void testHorizontalOpposite(){
        Room r1 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Room r2 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Door d1 = new Door(r1,r2,1);
        Door d2 = d1.getOpposite();

        r1.addDoor(d1,1);
        r2.addDoor(d1.getOpposite(),d1.getOpposite().getPosition());

        assert (r2.getWestDoor().getPosition() == d2.getPosition());
        assert (r2.getWestDoor().getDestination() == r1);

        assert (r2.getWestDoor().getOpposite().getPosition() == d1.getPosition());
        assert (r2.getWestDoor().getOpposite().getDestination() == r2);

    }

    /**
     * checks that north doors lead to a south door and vice versa
     */
    @Test
    public void testVerticalOpposite(){
        Room r1 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Room r2 = new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        };
        Door d1 = new Door(r1,r2,0);
        Door d2 = d1.getOpposite();

        r1.addDoor(d1,d1.getPosition());
        r2.addDoor(d1.getOpposite(),d1.getOpposite().getPosition());

        assert (r2.getSouthDoor().getPosition() == d2.getPosition());
        assert (r2.getSouthDoor().getDestination() == r1);

        assert (r2.getSouthDoor().getOpposite().getPosition() == d1.getPosition());
        assert (r2.getSouthDoor().getOpposite().getDestination() == r2);

    }

    /**
     * check if  the objects in a standard room such as rocks and urns are drawn  after population
     * @throws InterruptedException
     */
    @Test
    public void testDrawStandardRoom() throws InterruptedException {
        standardRoom r = new standardRoom();
        Player p = new Player(new Point(100,100));
        r.addPlayer(p);
        r.populateRoom(p);
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> r.draw(panel.getGraphics())).start();
            new Timer(2000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

    /**
     * checks that the boss is drawn correctly after population
     * @throws InterruptedException
     */
    @Test
    public void testDrawBoss() throws InterruptedException {
        bossRoom r = new bossRoom();
        Player p = new Player(new Point(100,100));
        r.addPlayer(p);
        r.populateRoom(p);
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> r.draw(panel.getGraphics())).start();
            new Timer(2000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

    /**
     * checks that an item room is drawn correctly after population
     * @throws InterruptedException
     */
    @Test
    public void testDrawItemRoom() throws InterruptedException{
        itemRoom r = new itemRoom();
        Player p = new Player(new Point(100,100));
        r.addPlayer(p);
        r.populateRoom(p);

        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> r.draw(panel.getGraphics())).start();
            new Timer(2000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));

    }

    /**
     * checks that doors are drawn correctly in all 4 positions
     * @throws InterruptedException
     */
    @Test
    public void testDoorsDraw() throws InterruptedException{
        startRoom r = new startRoom();
        startRoom r2 = new startRoom();
        Door n = new Door(r,r2,0);
        Door e = new Door(r,r2,1);
        Door s = new Door(r,r2,2);
        Door w = new Door(r,r2,3);

        r.addDoor(n,0);
        r.addDoor(e,1);
        r.addDoor(s,2);
        r.addDoor(w,3);

        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,t-> r.draw(panel.getGraphics())).start();
            new Timer(2000,t -> f.dispose()).start();
        });
        Thread.sleep((3000));



    }

}
