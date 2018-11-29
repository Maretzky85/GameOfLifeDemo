package GameOfLife.Common;

import javafx.scene.paint.Color;

public class Config {
    /**
     * CONSOLE_VIEW - console printing if true, JavaFX Window if false
     * for console view limit x and y for console size
     */
    public static boolean CONSOLE_VIEW = false;


    /**
     * X_SIZE and Y_SIZE - size of grid for GameOfLife.GameOfLife board
     * FRAME_RATE - how many generations to render in one second
     */
    public static int X_SIZE = 50;
    public static int Y_SIZE = 30;
    public static int FRAME_RATE = 20;


    /**
     * JavaFX Configuration
     * ALIVE_COLOR - color for newly spawned dot
     * SECOND_COLOR - color for dot that lives 2 or more generations
     * DEAD_COLOR - color for inactive Dot
     */
    private static int REQUESTED_WINDOW_WIDTH = 800;
    private static int REQUESTED_WINDOW_HEIGHT = 800;
    public static Color ALIVE_COLOR = Color.YELLOW;
    public static Color DEAD_COLOR = Color.BLACK;
    public static Color SECOND_COLOR = Color.CRIMSON;


    /**
     * DO NOT EDIT!!!!
     * configuration for matching JavaFX Window for board size
     * if window width and heigth are not dividable by board size
     * window size cannot be smaller than board size
     */
    public static final int RECTANGLE_WIDTH = Math.max(1, Math.floorDiv(REQUESTED_WINDOW_WIDTH, X_SIZE));
    public static final int RECTANGLE_HEIGHT = Math.max(1, Math.floorDiv(REQUESTED_WINDOW_HEIGHT, Y_SIZE));
    public static final int WIDTH = RECTANGLE_WIDTH * X_SIZE;
    public static final int HEIGHT = RECTANGLE_HEIGHT * Y_SIZE;
}
