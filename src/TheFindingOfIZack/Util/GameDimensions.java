package TheFindingOfIZack.Util;

public class GameDimensions {
    public static final int PADDING = 100;

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 700;
    public static final int MENU_HEIGHT = 100;
    public static final int GAME_WIDTH = WINDOW_WIDTH - PADDING;
    public static final int GAME_HEIGHT = WINDOW_HEIGHT - MENU_HEIGHT - PADDING;

    public static final int WALL_WIDTH = 40;
    public static final int LEFT_WALL = WALL_WIDTH;
    public static final int RIGHT_WALL = GAME_WIDTH-WALL_WIDTH;
    public static final int TOP_WALL = WALL_WIDTH;
    public static final int BOTTOM_WALL = GAME_HEIGHT-WALL_WIDTH;

    public static final int VERT_DOOR_START = GAME_WIDTH/2 - 25;
    public static final int VERT_DOOR_END = GAME_WIDTH/2 + 25;
    public static final int HORZ_DOOR_START = GAME_HEIGHT/2 - 25;
    public static final int HORZ_DOOR_END = GAME_HEIGHT/2 + 25;

}
