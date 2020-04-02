package TheFindingOfIZack.Entities;

import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.World.Rooms.Door;
import TheFindingOfIZack.World.Rooms.itemRoom;
import TheFindingOfIZack.World.Rooms.standardRoom;
import TheFindingOfIZack.Util.Point;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static TheFindingOfIZack.Entities.Entity.DEFAULT_WIDTH;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ben Allan
 */
public class PlayerTests {

    private int x = 100;
    private int y = 100;
    private Point location = new Point(x, y);

    @Test
    public void testMovement() {
        Player p = new Player(location);
        assertTrue(p!=null);
        p.moveUp();
        assertTrue(p.getLocation().getY()==y-p.getSpeed());
        p.moveDown();
        assertTrue(p.getLocation().getY()==y);
        p.moveLeft();
        assertTrue(p.getLocation().getX()==x-p.getSpeed());
        p.moveRight();
        assertTrue(p.getLocation().getX()==x);

    }

    @Test
    public void testProjectiles() {
        Player p = new Player(location);
        p.shootLeft();
        p.shootRight();
        p.shootDown();
        p.shootUp();

        assertTrue(!p.getProjectiles().isEmpty());
        // Size should be 1 since cool down is in effect
        assertTrue(p.getProjectiles().size() == 1);

    }

    @Test
    public void testLockedDoorNorth() {
        Player p = new Player(new Point(GameDimensions.VERT_DOOR_START, GameDimensions.TOP_WALL));

        standardRoom r1 = createRoom();
        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 0);
        r1.addDoor(d, 0);

        p.setRoom(r1);
        assertTrue(p.getRoom().equals(r1));

        p.moveUp();

        assertTrue(p.getRoom().equals(r1));

    }

    @Test
    public void testLockedDoorEast() {
        Player p = new Player(new Point(GameDimensions.RIGHT_WALL - DEFAULT_WIDTH, GameDimensions.HORZ_DOOR_START));

        standardRoom r1 = createRoom();
        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 1);
        r1.addDoor(d, 1);

        p.setRoom(r1);
        assertTrue(p.getRoom().equals(r1));

        p.moveRight();

        assertTrue(p.getRoom().equals(r1));

    }

    @Test
    public void testLockedDoorSouth() {
        Player p = new Player(new Point(GameDimensions.VERT_DOOR_START, GameDimensions.BOTTOM_WALL - DEFAULT_WIDTH));
        assertTrue(p!=null);

        standardRoom r1 = createRoom();
        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 2);
        r1.addDoor(d, 2);

        p.setRoom(r1);
        assertTrue(p.getRoom().equals(r1));

        p.moveDown();

        assertTrue(p.getRoom().equals(r1));

    }

    @Test
    public void testLockedDoorWest() {
        Player p = new Player(new Point(GameDimensions.LEFT_WALL, GameDimensions.HORZ_DOOR_START));
        assertTrue(p!=null);

        standardRoom r1 = createRoom();
        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 3);
        r1.addDoor(d, 3);

        p.setRoom(r1);
        assertTrue(p.getRoom().equals(r1));

        p.moveRight();

        assertTrue(p.getRoom().equals(r1));

    }

    @Test
    public void testUnlockedDoorNorth() {
        Player p = new Player(new Point(GameDimensions.VERT_DOOR_START, GameDimensions.TOP_WALL));
        itemRoom r1 = new itemRoom();

        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 0);

        r1.addDoor(d, 0);

        p.setRoom(r1);
        assertTrue(p.getRoom().getNorthDoor().getDestination().equals(r2));


        r1.update();
        assertTrue(p.getRoom().equals(r1));
        assertFalse(p.getRoom().getNorthDoor().isLocked);
        p.moveUp();
        assertTrue(p.getRoom().equals(r2));

    }

    @Test
    public void testUnlockedDoorEast() {
        Player p = new Player(new Point(GameDimensions.RIGHT_WALL - DEFAULT_WIDTH, GameDimensions.HORZ_DOOR_START));
        itemRoom r1 = new itemRoom();

        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 1);

        r1.addDoor(d, 1);

        p.setRoom(r1);
        assertTrue(p.getRoom().getEastDoor().getDestination().equals(r2));


        r1.update();
        assertTrue(p.getRoom().equals(r1));
        assertFalse(p.getRoom().getEastDoor().isLocked);
        p.moveRight();
        assertTrue(p.getRoom().equals(r2));
    }

    @Test
    public void testUnlockedDoorSouth() {
        Player p = new Player(new Point(GameDimensions.VERT_DOOR_START, GameDimensions.BOTTOM_WALL - DEFAULT_WIDTH));
        itemRoom r1 = new itemRoom();

        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 2);

        r1.addDoor(d, 2);

        p.setRoom(r1);
        assertTrue(p.getRoom().getSouthDoor().getDestination().equals(r2));


        r1.update();
        assertTrue(p.getRoom().equals(r1));
        assertFalse(p.getRoom().getSouthDoor().isLocked);
        p.moveDown();
        assertTrue(p.getRoom().equals(r2));
    }

    @Test
    public void testUnlockedDoorWest() {
        Player p = new Player(new Point(GameDimensions.LEFT_WALL, GameDimensions.HORZ_DOOR_START));
        itemRoom r1 = new itemRoom();

        standardRoom r2 = createRoom();

        Door d = new Door(r1, r2, 3);

        r1.addDoor(d, 3);

        p.setRoom(r1);
        assertTrue(p.getRoom().getWestDoor().getDestination().equals(r2));


        r1.update();
        assertTrue(p.getRoom().equals(r1));
        assertFalse(p.getRoom().getWestDoor().isLocked);
        p.moveLeft();
        assertTrue(p.getRoom().equals(r2));
    }

    public standardRoom createRoom() {
        return new standardRoom();
    }

    @Test
    public void testBoundaries() {
        Player p = new Player(new Point(GameDimensions.LEFT_WALL, GameDimensions.TOP_WALL));
        p.setRoom(createRoom());
        Point point = new Point(GameDimensions.LEFT_WALL, GameDimensions.TOP_WALL);

        p.moveUp();
        assertTrue(p.getLocation().getY() == point.getY());

        p.moveLeft();
        assertTrue(p.getLocation().getX() == point.getX());

        p = new Player(new Point(GameDimensions.RIGHT_WALL-p.width, GameDimensions.BOTTOM_WALL-p.width));
        p.setRoom(createRoom());
        point = new Point(GameDimensions.RIGHT_WALL-p.width, GameDimensions.BOTTOM_WALL-p.width);

        p.moveDown();
        assertTrue(p.getLocation().getY() == point.getY());

        p.moveRight();
        assertTrue(p.getLocation().getX() == point.getX());
    }

    @Test
    public void testDamage() {
        Player p = new Player(location);
        p.damage(50);
        assertTrue(p.getHealth()<p.getMaxHealth());
        assertTrue(p.getHealth() == p.getMaxHealth()-50);
    }

    @Test
    public void win() {
        Player p = new Player(location);
        assertFalse(p.hasWon());

        p.setWon();
        assertTrue(p.hasWon());
    }

    @Test
    public void testHeal() {
        Player p = new Player(location);
        p.heal(10);
        assertTrue(p.getHealth() == p.getMaxHealth());

        p.damage(10);
        p.heal(10);
        assertTrue(p.getHealth() == p.getMaxHealth());

        p.damage(40);
        p.heal(10);
        assertTrue(p.getHealth() < p.getMaxHealth());
        assertTrue(p.getHealth() == p.getMaxHealth()-30);
    }

    @Test
    public void testArmour() {
        Player p = new Player(location);
        assertTrue(p.getArmour() == 0);

        p.damage(10);
        assertTrue(p.getArmour() == 0);
        p.heal(10);

        p.addArmour(p.getMaxArmour());
        assertTrue(p.getArmour() == p.getMaxArmour());

        p.addArmour(10);
        assertTrue(p.getArmour() == p.getMaxArmour());

        p.damage(10);
        assertTrue(p.getHealth() == p.getMaxHealth());
        assertTrue(p.getArmour() < p.getMaxArmour());
        assertTrue(p.getArmour() == p.getMaxArmour()-1);
    }

    @Test
    public void testWeapon() {
        Player p = new Player(location);
        assertTrue(p.getFireRate() > p.getMinFireRate());

        int currentFireRate = p.getFireRate();
        p.weaponUpgrade();
        assertTrue(p.getFireRate() < currentFireRate);

        while (p.getFireRate() > p.getMinFireRate()) {
            p.weaponUpgrade();
        }

        assertTrue(p.getFireRate() == p.getMinFireRate());
    }

    @Test
    public void testKey() {
        Player p = new Player(location);
        assertFalse(p.getKey());

        p.addKey();
        assertTrue(p.getKey());

        p.removeKey();
        assertFalse(p.getKey());
    }

    @Test
    public void testDrawPlayer() throws InterruptedException {
        Player p = new Player(location);
        SwingUtilities.invokeLater(()->{
            JFrame f = new JFrame();
            JPanel panel = new JPanel();
            f.add(panel);
            panel.setPreferredSize(new Dimension(GameDimensions.GAME_WIDTH,GameDimensions.GAME_HEIGHT));
            f.pack();
            f.setVisible(true);

            new Timer(1000,e-> p.draw(panel.getGraphics())).start();
            new Timer(3000,e -> f.dispose()).start();
        });
        Thread.sleep((3000));
    }

}
