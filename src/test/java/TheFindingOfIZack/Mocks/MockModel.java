package TheFindingOfIZack.Mocks;

import TheFindingOfIZack.Entities.AbstractPlayer;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.World.Model;
import TheFindingOfIZack.World.Rooms.Room;


public class MockModel implements Model {
    private AbstractPlayer p = new AbstractPlayer(new Point(50, 50)) {

        @Override
        public boolean isDestroyed() {
            return false;
        }

        private Room r;
        @Override
        public void moveSouth() {

        }

        @Override
        public void moveNorth() {

        }

        @Override
        public void moveEast() {

        }

        @Override
        public void moveWest() {

        }

        @Override
        public void shootUp() {

        }

        @Override
        public void shootDown() {

        }

        @Override
        public void shootLeft() {

        }

        @Override
        public void shootRight() {

        }

        @Override
        public Room getRoom() {
            return r;
        }

        @Override
        public void setRoom(Room r) {
           this.r = r;
        }

        @Override
        public int getMaxHealth() {
            return 0;
        }

        @Override
        public int getHealth() {
            return 0;
        }

        @Override
        public int getArmour() {
            return 0;
        }

        @Override
        public int getMaxArmour() {
            return 0;
        }

        @Override
        public boolean getKey() {
            return false;
        }

        @Override
        public int getKeys() {
            return 0;
        }
        
    };

    public boolean sLeft = false;
    public boolean sRight = false;
    public boolean sDown = false;
    public boolean sUp = false;
    public boolean mLeft = false;
    public boolean mUp = false;
    public boolean mDown = false;
    public boolean mRight = false;

    public MockModel(){
        p.setRoom(new Room() {
            @Override
            public void populateRoom(Player p) {

            }

            @Override
            public void update() {

            }
        });
    }

    @Override
    public void beginNewGame() {

    }

    @Override
    public AbstractPlayer getPlayer() {
        return p;
    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void moveUp(boolean b) {
        mUp = b;
    }

    @Override
    public void moveDown(boolean b) {
        mDown = b;
    }

    @Override
    public void moveLeft(boolean b) {
        mLeft = b;
    }

    @Override
    public void moveRight(boolean b) {
        mRight = b;
    }

    @Override
    public void shootLeft(boolean b) {
        sLeft = b;
    }

    @Override
    public void shootRight(boolean b) {
        sRight = b;
    }

    @Override
    public void shootUp(boolean b) {
        sUp = b;
    }

    @Override
    public void shootDown(boolean b) {
        sDown = b;
    }

    @Override
    public boolean isGameLost() {
        return false;
    }

    @Override
    public boolean isGameWon() {
        return false;
    }
}
