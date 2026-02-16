package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Level;
import TheFindingOfIZack.World.Rooms.Door;
import TheFindingOfIZack.World.Rooms.Room;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;

/**
 *  This class captures the notion of Loading a game file, it deals with
 *  interpreting the GameFile, and recreating the game that was stored.
 *  The class will return the Game, stored in the GameFile.
 */
public class LoadFile extends GameFile{

    private Game game = null;

    public LoadFile() throws InvalidFileException {
        if (openFile(parent)) {
            game = execute(file);
        }
    }

    public LoadFile(File f) throws InvalidFileException {
        game = execute(f);
    }

    /**
     *  This method chooses the File to be loaded, and verifies
     *  the integrity of the .ZACK file
     */
    public Game execute(File f) throws InvalidFileException{
        ObjectInputStream obIn = null;
        Game game;
        file = f;
        try {
            obIn = new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            fileError("Failed to execute: " + e.getLocalizedMessage());
            throw new InvalidFileException("Failed to execute");
        }

        if (obIn == null)
            throw new InvalidFileException("Object Input is null");

        game = readGame(obIn);
        Player player = readPlayer(obIn);
        Level level = readLevel(obIn);
        Room room= readRoom(obIn);
        ArrayList<Door> doors = readDoors(obIn);
        try {
            obIn.close();
        } catch (IOException e) {
            fileError("Failed to close object reader");
            throw new InvalidFileException("Failed to close object reader");
        }
        game = new Game(game, player, level, room, doors);

        return game;
    }

    /**
     * This method reads the Room object from an
     * @param obIn object input stream
     * @return the loaded Level
     */
    public Room readRoom(ObjectInput obIn) throws InvalidFileException {
        Room r = null;
        try {
            r = (Room) obIn.readObject();
        } catch (ClassNotFoundException e) {
            fileError("Reading Room: " + e.getLocalizedMessage());
            throw new InvalidFileException("Reading Room");
        } catch (IOException e) {
            fileError("Reading Room: " + e.getLocalizedMessage());
            throw new InvalidFileException("Reading Room");
        }
        return r;
    }

    /**
     * This method reads the Level object from an
     * @param obIn object input stream
     * @return the loaded Level
     */
    public Level readLevel(ObjectInput obIn) throws InvalidFileException{
        Level l = null;
        try {
            try {
                l = (Level) obIn.readObject();
            } catch (ClassNotFoundException e) {
                fileError("Reading Level: " + e.getLocalizedMessage());
                throw new InvalidFileException("Reading Level");
            }

        } catch (IOException e) {
            fileError("Reading Level: " + e.getLocalizedMessage());
            throw new InvalidFileException("Reading Leve;l");
        }
        return l;
    }

    /**
     * This method returns the Game object from an
     * @param obIn object input stream
     * @return the loaded Game
     */
    public Game readGame(ObjectInput obIn) throws InvalidFileException {
        Game g = null;
        try {
            try {
                g = (Game) obIn.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new InvalidFileException("Reading Game");
            }

        } catch (IOException e) {
            fileError("Reading Game: " + e.getLocalizedMessage());
            throw new InvalidFileException("Reading Game");
        }
        return g;
    }

    /**
     * This method returns the Player object from an
     * @param obIn object input stream
     * @return the loaded Player
     */
    public Player readPlayer(ObjectInput obIn) throws InvalidFileException {
        Player p = null;
        try {
            p = (Player) obIn.readObject();
        } catch (ClassNotFoundException e) {
            fileError("Reading Player: " + e.getLocalizedMessage());
            throw new InvalidFileException("Reading Player");
        } catch (IOException e) {
            fileError("Reading Game: " + e.getLocalizedMessage());
            throw new InvalidFileException("Reading Player");
        }
        return p;
    }

    public ArrayList<Door> readDoors(ObjectInputStream obIn) throws InvalidFileException {
        ArrayList<Door> doors = new ArrayList<>();
        try {
            doors.add((Door) obIn.readObject());
            doors.add((Door) obIn.readObject());
            doors.add((Door) obIn.readObject());
            doors.add((Door) obIn.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new InvalidFileException("Reading doors");
        }
        return doors;
    }

    /**
     *  This method allows the user to select a GameFile from their computer
     *  and displays the valid file extensions
     *  @return true if valid, false otherwise
     */
    public boolean openFile(JFrame parent) throws InvalidFileException {
        JFileChooser chooser = new JFileChooser();

        // This method only accepts .txt or .zack file extensions
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ZACK Files", EXTENSION);
        //chooser.setFileFilter(filter);

        // Sets the current directory to make navigation easier
        chooser.setCurrentDirectory(new File(DIRECTORY));

        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("opening...");
            file = chooser.getSelectedFile();
            return true;
        }
        fileError("Invalid File chosen");
        throw new InvalidFileException("No file chosen");
    }



    /**
     * This method returns the Game
     * @return the Game if it exists or
     * @throws InvalidFileException if the Game is null
     */
    public Game getGame() throws InvalidFileException{
        if (this.game == null)
            throw new InvalidFileException("Game is null");
        return game;
    }
}
