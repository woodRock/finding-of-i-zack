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
public class LoadFileTest {

    /**
     *  This test ensures that a LoadFile can be created
     */
    @Test
    public void test01_createLoadFileModifiedPlayerFieldValues(){
        try {
            Player p = new Player(new Point(GameDimensions.GAME_WIDTH /2 - 20, GameDimensions.GAME_HEIGHT/2 - 20));
            p.damage(10);
            p.addArmour(2);
            Game game = new Game(p);
            game.createLevelOne();
            File tempFile = File.createTempFile("testSave", ".zack");
            SaveFile saveFile = new SaveFile(game, tempFile);

            LoadFile testLoadFile = new LoadFile(tempFile);
            assertTrue("There should be a game",testLoadFile.getGame() != null);
            assertTrue("There should be a player",testLoadFile.getGame().getPlayer() != null);
            assertTrue("Player Health should be" + p.getHealth(),testLoadFile.getGame().getPlayer().getHealth() == p.getHealth());
            assertTrue( "Player Armour should be" + p.getArmour(), testLoadFile.getGame().getPlayer().getArmour() ==  p.getArmour());

            if (p.getRoom() != null){
                assertTrue("If player previously had a room it should still have a room",testLoadFile.getGame().getPlayer().getRoom() != null);
            }
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    @Test
    public void test02_createLoadFileDefaultPlayerFieldValues(){
        try {
            Player p = new Player(new Point(GameDimensions.GAME_WIDTH /2 - 20, GameDimensions.GAME_HEIGHT/2 - 20));
            Game game = new Game(p);
            game.createLevelOne();
            File tempFile = File.createTempFile("testSaveDefault", ".zack");
            SaveFile saveFile = new SaveFile(game, tempFile);

            LoadFile testLoadFile = new LoadFile(tempFile);
            assertTrue("There should be a game",testLoadFile.getGame() != null);
            assertTrue("There should be a player",testLoadFile.getGame().getPlayer() != null);
            assertTrue("Player Health should be" + p.getHealth(),testLoadFile.getGame().getPlayer().getHealth() == p.getHealth());
            assertTrue( "Player Armour should be" + p.getArmour(), testLoadFile.getGame().getPlayer().getArmour() ==  p.getArmour());
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
