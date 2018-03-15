package TheFindingOfIZack.FileIO.Util;

import org.omg.CORBA.DynAnyPackage.Invalid;

/**
 *  This class captures the notion of an InvalidFile Exception, this
 *  occurs when an InvalidFile is created or loaded.
 */
public class InvalidFileException extends Exception {
    public InvalidFileException(String message){
        super(message);
    }
}
