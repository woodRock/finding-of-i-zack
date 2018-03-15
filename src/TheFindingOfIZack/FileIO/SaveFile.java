package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.AbstractPlayer;
import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.FileIO.Util.InvalidFileException;
import TheFindingOfIZack.World.Game;
import TheFindingOfIZack.World.Level;
import TheFindingOfIZack.World.Rooms.Door;
import TheFindingOfIZack.World.Rooms.Room;
import TheFindingOfIZack.World.Rooms.standardRoom;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *  This class captures the notion of Saving a GameFile, it deals with storing
 *  all of the different components of a Game, and translating them all into a
 *  GameFile, that stores the Game in its current state. This class will return
 *  a GameFile, made from the current game.
 */
public class SaveFile extends GameFile {

    private Game game;

    public SaveFile(Game g) throws InvalidFileException{
        game = g;
        execute(game);
    }

    /**
     *  This method chooses the File to saved, and verifies
     *  the integrity of the .ZACK file
     */
    public void execute(Game g) throws InvalidFileException{
        ObjectOutputStream obOut = null;
        boolean isValidFile = saveFile(parent);
        if (!isValidFile)
            return;
        try {
            obOut = new ObjectOutputStream(new FileOutputStream(file+EXTENSION));
        } catch (IOException e) {
            fileError("Failed to execute" + e.getLocalizedMessage());
            throw new InvalidFileException("Object Output Stream is null");
        }
        if (obOut != null) {
            writeGame(g, obOut);
            writePlayer(g, obOut);
            writeLevel(g, obOut);
            writeRoom(g, obOut);
            writeDoors(g, obOut);
            try {
                obOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeDoors(Game g, ObjectOutputStream obOut) throws InvalidFileException {
        Door doorNorth, doorEast, doorSouth, doorWest;
        doorNorth = g.getPlayer().getRoom().getNorthDoor();
        doorEast = g.getPlayer().getRoom().getEastDoor();
        doorSouth = g.getPlayer().getRoom().getSouthDoor();
        doorWest= g.getPlayer().getRoom().getWestDoor();
        try {
            obOut.writeObject(doorNorth);
            obOut.writeObject(doorEast);
            obOut.writeObject(doorSouth);
            obOut.writeObject(doorWest);
        } catch (IOException e) {
            fileError("Writing doors" + e.getLocalizedMessage());
            throw new InvalidFileException("Writing Doors");
        }
    }

    /**
     *  This method writes the Level to the SaveFile
     * @param g the Game with the level to write to an
     * @param obOut object output stream
     */
    public void writeLevel(Game g, ObjectOutputStream obOut) throws InvalidFileException {
        Level l = g.getCurrentLevel();
        if (l == null)
            throw new InvalidFileException("Null Level Exception");
        try {
            obOut.writeObject(l);
            System.out.printf("Level Serialized data is saved in " + file.getName()  + EXTENSION + "\n");
        }   catch(IOException i) {
            GameFile.fileError("Writing Level: " + i.getLocalizedMessage());
            throw new InvalidFileException("Writing Level " + i.getLocalizedMessage());
        }
    }

    /**
     *  This method writes the Player to the SaveFile
     * @param g the Game with the player to write to an
     * @param obOut object output stream
     */
    public void writePlayer(Game g, ObjectOutputStream obOut) throws InvalidFileException {
        AbstractPlayer p = g.getPlayer();
        if (p == null)
            throw new InvalidFileException("Null Player Error");
        try {
            obOut.writeObject(p);
            System.out.printf("Player Serialized data is saved in " + file.getName() + EXTENSION + "\n");
        }   catch(IOException i) {
            fileError("Writing player" + i.getLocalizedMessage());
        }
    }

    /**
     *  This method writes the Room to a SaveFile
     * @param g the Game to write to an
     * @param obOut object output stream
     * @throws InvalidFileException thrown if errors are encountered
     */
    public void writeRoom(Game g, ObjectOutputStream obOut) throws InvalidFileException{
        Room r = g.getPlayer().getRoom();
        if (r == null){
            throw new InvalidFileException("Null Game Error");
        }
        try {
            obOut.writeObject(r);
            System.out.printf("Room Serialized data is saved in " + file.getName() + EXTENSION + "\n");
        }   catch(IOException i) {
            fileError("Writing Room: " + i.getLocalizedMessage());
            throw new InvalidFileException("Writing Room:" + i.getLocalizedMessage());
        }
    }

    /**
     * This method write the Game to a SaveFile
     * @param g the Game to write to an
     * @param obOut object output stream
     * @throws InvalidFileException thrown if errors are encountered
     */
    public void writeGame(Game g, ObjectOutputStream obOut) throws InvalidFileException {
        if (g == null)
            throw new InvalidFileException("Null Game Error");
        try {
            obOut.writeObject(g);
            System.out.printf("Game Serialized data is saved in " + file.getName() + EXTENSION + "\n");
        }   catch(IOException i) {
            fileError("Failed to write game" + i.getLocalizedMessage());
            throw new InvalidFileException("Writing Room:" + i.getLocalizedMessage());
        }
    }

    /**
     *  This method allows the user to select a GameFile from their computer
     *  and displays the valid file extensions
     *  @return true if valid, false otherwise
     */
    public boolean saveFile(JFrame parent) throws InvalidFileException {
        JFileChooser chooser = new JFileChooser();

        // This method only accepts .zack or .txt file extensions
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ZACK Files", EXTENSION);
        //chooser.setFileFilter(filter);

        // Sets the current directory to make navigation easier
        chooser.setCurrentDirectory(new File(DIRECTORY));

        int returnVal = chooser.showSaveDialog(parent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("saving...");
            try {
                file = chooser.getSelectedFile();
                return true;
            } catch (Exception ex) {//TODO SHOULD NOT CATCH ALL EXCEPTIONS
                fileError("Invalid saveFile " + ex.getLocalizedMessage());
                throw new InvalidFileException("Bad File");
            }
        }
        fileError("Invalid File chosen");
        throw new InvalidFileException("Bad File");
    }
}
