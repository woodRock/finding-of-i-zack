package TheFindingOfIZack.World;

import TheFindingOfIZack.Entities.AbstractPlayer;

public interface Model {
    /**
     * Start the game generating any required resources
     */
    void beginNewGame();

    /**
     * Allow access to the player of the game
     * @return Player
     */
    AbstractPlayer getPlayer();

    /**
     * Resume gameloops and interactions
     */
    void resumeGame();

    /**
     * Pause gameloops and interactions
     */
    void pauseGame();

    /**
     * Start or stop moving the player up
     * @param b boolean indicating to start or stop
     */
    void moveUp(boolean b);
    /**
     * Start or stop moving the player down
     * @param b boolean indicating to start or stop
     */
    void moveDown(boolean b);
    /**
     * Start or stop moving the player left
     * @param b boolean indicating to start or stop
     */
    void moveLeft(boolean b);
    /**
     * Start or stop moving the player right
     * @param b boolean indicating to start or stop
     */
    void moveRight(boolean b);

    /**
     * Start or stop shooting left
     * @param b boolean indicating to start or stop
     */
    void shootLeft(boolean b);

    /**
     * Start or stop shooting right
     * @param b boolean indicating to start or stop
     */
    void shootRight(boolean b);

    /**
     * Start or stop shooting up
     * @param b boolean indicating to start or stop
     */
    void shootUp(boolean b);

    /**
     * Start or stop shooting down
     * @param b boolean indicating to start or stop
     */
    void shootDown(boolean b);

    /**
     * Check if the game has been lost
     * @return  true if lost false otherwise
     */
    boolean isGameLost();

    /**
     * Check if the game has been won
     * @return true if won false otherwise
     */
    boolean isGameWon();
}
