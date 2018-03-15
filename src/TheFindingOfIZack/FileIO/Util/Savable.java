package TheFindingOfIZack.FileIO.Util;

import java.io.Serializable;

/**
 *  This abstract class captures the notion of a Save friendly object,
 *  objects like Levels, Players, Games, Items, ... etc need to be able
 *  to be saved and stored in the GameFile
 */
public interface Savable extends Serializable {}
