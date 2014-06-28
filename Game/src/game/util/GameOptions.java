package game.util;

/**
 * Created by Max on 6/27/2014.
 */
public class GameOptions {
    public static int TILE_SIZE = 20;
    public static int MAP_WIDTH = 100; // убрать, когда будут реализованы уровни
    public static int MAP_HEIGHT = 100; //
    public static String[] TILE_FILENAMES = {"tile_wall.png", "tile_floor.png", "player.png"};
    public static String ASSETS_GRAPHICS_PATH = "assets/graphics/";
    public static String ASSETS_MAPS_PATH = "assets/maps/";

    private static GameOptions ourInstance = new GameOptions();

    public static GameOptions getInstance() {
        return ourInstance;
    }

    private GameOptions() {
    }


}
