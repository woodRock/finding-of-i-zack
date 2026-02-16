package TheFindingOfIZack.FileIO;

import TheFindingOfIZack.Entities.Player;
import TheFindingOfIZack.Util.Point;
import TheFindingOfIZack.FileIO.Util.Huffman;
import TheFindingOfIZack.View.ViewManager;
import TheFindingOfIZack.World.Game;

import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 *  This class captures the notion of a GameFile. The file itself will be
 *  a long string of text, this method will make it easier to manipulate, read
 *  and write to that file.
 *
 *  This class handles the various exceptions and errors and exceptions that may be
 *  encountered when reading a file. Abstracting the technical details of TheFindingOfIZack.FileIO
 *
 *  This class should be able to represent the entire state of the game in text, and load the entire
 *  from text. So that the game can be loaded and saved in between launches
 *
 */
public class GameFile {
    /**
     *  This is the the text to be encoded/decoded or saved/loaded
     */
    private String text = "";

    private enum FILE_STATE {
        ENCODED,
        DECODED,
    }

    /**
     *  This stores the state of the text for the GameFile, this determines whether or not
     *  the GameFile can be manipulated at this time
     */
    private FILE_STATE fileState = FILE_STATE.DECODED;

    /**
     *  This creates the ability for this file to be encoded using the Huffman encoding class
     */
    private Huffman huffman = new Huffman(text);

    /**
     *  This BufferedReader stores the reader that interacts with the files
     */
    protected BufferedReader in;

    protected BufferedOutputStream out;

    /**
     * This stores the location of the GameFile .txt file to be read or written
     *
     */
    protected File file = null;

    /**
     *  Ensures that different versions of the game are not saved and loaded
     */
    private static final String RELEASE = "/v0.0.1";

    protected JFrame parent =
            new ViewManager(new Game(new Player(new Point(0,0))));

    /**
     *  This stores the files extension for all of the GameFiles
     */
    protected static final String EXTENSION = ".zack";

    /**
     * This stores the directory for all of the .zack GameFiles
     */
    protected static final String DIRECTORY = System.getProperty("user.dir") + File.separator + "saves";

    /**
     * This stores the file header for each of the Gamefiles
     */
    protected static final String HEADER = "#ZACK/FILEIO";

    /**
     *  This GameFile constructor sets up the BufferedReader for a GameFile to
     *  be read, and the BufferedOutputStream to be written too.
     */
    public GameFile() {}

    public void writeHeader(BufferedOutputStream out){
        try {
            out.write(HEADER.getBytes());
            out.write(RELEASE.getBytes());
            out.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHeader(){
        try {
            in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  This method returns true if the end of the file has been reached
     *  @return true if EOF, false otherwise
     */
    public boolean isEOF(BufferedReader in) {
        boolean isEOF;
        try {
            isEOF = in.ready();
        }
        catch (IOException e)
        {
            //  The end of file has been reached
            fileError("End-of-file");
            isEOF = true;
        }
        return isEOF;
    }

    /**
     *  This method displays an a TheFindingOfIZack.FileIO error appropriately
     * @param str the error to be displayed
     */
    public static void fileError(String str){
        System.err.print("TheFindingOfIZack.FileIO Error: " + str + "\n");
    }

    /**
     *  This method closes BufferedReader after the GameFile is done with it
     * @return true if successful, false otherwise
     */
    public boolean close(BufferedReader in){
        try {
            in.close();
        } catch (IOException e) {
            fileError("Failed to close BufferedReader");
            return false;
        }
        return true;
    }

    /**
     *  This method closes BufferedReader after the GameFile is done with it
     * @return true if successful, false otherwise
     */
    public boolean exit(BufferedReader in, BufferedOutputStream out){
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            fileError("Failed to close BufferedReader");
            return false;
        }
        return true;
    }

    /**
     * This method encodes the GameFile using the Huffman class
     */
    public void encode(){
        // Only allow encoding if it is decoded
        if (fileState == FILE_STATE.DECODED)
            huffman.encode(text);
    }

    /**
     * This method decodes the GameFile using the Huffman class
     */
    public void decode(){
        //  Only allow decoding if it is encoded
        if (fileState == FILE_STATE.ENCODED)
            huffman.decode(text);
    }

    /**
     *  This  main method is for testing purposes of the class ...
     * @param args the arguments for the main method
     */
    public static void main(String [] args){

    }

}
