package GameOfLife.CommonUsage;

import javafx.scene.paint.Color;

public class Config {
    public static double VERSION = 0.96;

    private static boolean printStatistics = false;

    /**
     * CONSOLE_VIEW - console printing if true, JavaFX Window if false
     * for console view limit x and y for console size
     */
    public static boolean CONSOLE_VIEW = false;
    /**
     * X_SIZE and Y_SIZE - size of grid for GameOfLife.GameOfLife board
     * FRAME_RATE - how many generations to render in one second
     */
    public static int X_SIZE = 100;
    public static int Y_SIZE = 100;
    public static int FRAME_RATE = 20;

    /**
     * JavaFX Configuration
     * DEAD_COLOR - color for inactive Dot
     */
    private static int REQUESTED_WINDOW_WIDTH = 400;

    private static int REQUESTED_WINDOW_HEIGHT = 300;
    public static Color DEAD_COLOR = Color.BLACK;

    private static boolean startExampleModels = false;

    /**
     * DO NOT EDIT!!!!
     * configuration for matching JavaFX Window for board size
     * if window width and heigth are not dividable by board size
     * window size cannot be smaller than board size
     */
    public static int RECTANGLE_WIDTH = Math.max(1, Math.floorDiv(REQUESTED_WINDOW_WIDTH, X_SIZE));

    public static int RECTANGLE_HEIGHT = Math.max(1, Math.floorDiv(REQUESTED_WINDOW_HEIGHT, Y_SIZE));
    public static int WIDTH = RECTANGLE_WIDTH * X_SIZE;
    public static int HEIGHT = RECTANGLE_HEIGHT * Y_SIZE;

    /*
    =============================================================================
    setters and getters for console menu
     */

    static void togglePrintStatistics() {
        printStatistics = !printStatistics;
    }

    public static void setConsoleView(boolean consoleView) {
        CONSOLE_VIEW = consoleView;
    }

    static void setxSize(int xSize) {
        X_SIZE = xSize;
        if (REQUESTED_WINDOW_WIDTH < X_SIZE) {
            REQUESTED_WINDOW_WIDTH = X_SIZE;
        }
        resize();
    }

    static void setySize(int ySize) {
        Y_SIZE = ySize;
        if (REQUESTED_WINDOW_HEIGHT < Y_SIZE) {
            REQUESTED_WINDOW_HEIGHT = Y_SIZE;
        }
        resize();
    }

    static void setFrameRate(int frameRate) {
        FRAME_RATE = frameRate;
    }

    static boolean setRequestedWindowWidth(int requestedWindowWidth) {
        boolean ok = false;
        if (requestedWindowWidth < X_SIZE) {
            REQUESTED_WINDOW_WIDTH = X_SIZE;
            System.out.println("Requested window size is smaller than board\nWindow X size set to: " + X_SIZE);
        } else {
            REQUESTED_WINDOW_WIDTH = requestedWindowWidth;
            ok = true;
        }
        resize();
        return ok;
    }

    static boolean setRequestedWindowHeight(int requestedWindowHeight) {
        boolean ok = false;
        if (requestedWindowHeight < Y_SIZE) {
            REQUESTED_WINDOW_HEIGHT = Y_SIZE;
            System.out.println("Requested window size is smaller than board\nWindow Y size set to: " + Y_SIZE);
        } else {
            REQUESTED_WINDOW_HEIGHT = requestedWindowHeight;
            ok = true;
        }
        resize();
        return ok;
    }

    public static void toggleStartExampleModels() {
        Config.startExampleModels = !Config.startExampleModels;
    }

    private static void resize() {
        RECTANGLE_WIDTH = Math.max(1, Math.floorDiv(REQUESTED_WINDOW_WIDTH, X_SIZE));
        WIDTH = RECTANGLE_WIDTH * X_SIZE;
        RECTANGLE_HEIGHT = Math.max(1, Math.floorDiv(REQUESTED_WINDOW_HEIGHT, Y_SIZE));
        HEIGHT = RECTANGLE_HEIGHT * Y_SIZE;
    }

    public static boolean isPrintStatistics() {
        return printStatistics;
    }

    static boolean isConsoleView() {
        return CONSOLE_VIEW;
    }

    static int getxSize() {
        return X_SIZE;
    }

    static int getySize() {
        return Y_SIZE;
    }

    static int getFrameRate() {
        return FRAME_RATE;
    }

    static int getRequestedWindowWidth() {
        return REQUESTED_WINDOW_WIDTH;
    }

    static int getRequestedWindowHeight() {
        return REQUESTED_WINDOW_HEIGHT;
    }

    public static boolean isStartExampleModels() {
        return startExampleModels;
    }
}
