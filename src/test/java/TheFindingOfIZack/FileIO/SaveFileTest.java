package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.Util.GameDimensions;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.World.Game;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by woodjess3 on 27/09/17.
 */
public class SaveFileTest {

    /**
     * This test ensures that a SaveFile can be created
     */
    @Test
    public void test01_createSaveFile(){
        try {
            Game game = new Game(new Player(new Point(GameDimensions.GAME_WIDTH /2 - 20, GameDimensions.GAME_HEIGHT/2 - 20)));
            game.createLevelOne();
            File tempFile = File.createTempFile("testSaveOnly", ".zack");
            SaveFile testSaveFile = new SaveFile(game, tempFile);
            assertTrue(tempFile.exists());
            tempFile.delete();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
