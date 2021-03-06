package GameOfLife.View;

import GameOfLife.Common.Config;
import GameOfLife.Controller.Controller;
import GameOfLife.Model.Dot;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static GameOfLife.Common.Config.*;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

/**
 * JavaFX View Class implements ViewInterface class
 * For viewing passed board model and passing input from user to Observable Class
 */
public class JavaFXView implements ViewInterface {

    private Stage primaryStage;
    private Group group = new Group();
    private Scene scene = new Scene(group, WIDTH, HEIGHT, Color.BLACK);
    private InputHandler inputHandler = new InputHandler();
    private Rectangle[][] viewRectangleTable;
    private boolean ongoingUpdateFromModel = false;
    private boolean ongoingUpdateFromView = false;
    private int droppedFrames = 0;

    /**
     * Constructor that takes primary stage from caller
     *
     * @param primaryStage - takes primary stage from entry point of application
     */
    public JavaFXView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * viewInit method
     * Sets stages title, creates and initialises reflecting rectangle table for holding view`s side rectangles
     * Defines rectangle appearance
     * Calls scene set and view methods for showing window.
     * Calls attachListeners function to attach proper listeners to stage and scene
     */
    @Override
    public void viewInit() {
        System.out.println("JavaFX: Initialising Scene.");
        primaryStage.setTitle("Game Of Life  v " + VERSION);

        long startTime = System.currentTimeMillis();

        System.out.print("JavaFX: Initialising grid");
        initGrid();
        System.out.print(" Done. Initialising grid took " + (System.currentTimeMillis() - startTime) + " ms\n");

        System.out.println("JavaFX: Setting-up scene");
        primaryStage.setScene(scene);
        System.out.println("JavaFX: Finished setting-up scene");

        System.out.println("JavaFX: setting-up window");
        startTime = System.currentTimeMillis();
        primaryStage.show();
        System.out.println("JavaFX: Preparing window took " + (System.currentTimeMillis() - startTime) + " ms");
        attachListeners();

    }

    /**
     * Attaches listeners for mouse and keyboard input to scene
     * Attaches listeners for stage width and height and calls resizeGrid if needed
     */
    private void attachListeners(){
        int windowUpperBarTreshold = -30;
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> {
            Config.setRequestedWindowWidth(newValue.intValue());
            resizeGrid();
        });

        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> {
            Config.setRequestedWindowHeight(newValue.intValue()+windowUpperBarTreshold);
            resizeGrid();
        });

        scene.setOnKeyPressed(this::handleInput);
        scene.setOnMousePressed(this::handleInput);
    }

    private void resizeGrid(){
        for (int boardYposition = 0; boardYposition < Y_SIZE; boardYposition++) {
            for (int boardXposition = 0; boardXposition < X_SIZE; boardXposition++) {
                viewRectangleTable[boardYposition][boardXposition].setHeight(RECTANGLE_HEIGHT);
                viewRectangleTable[boardYposition][boardXposition].setWidth(RECTANGLE_WIDTH);
                viewRectangleTable[boardYposition][boardXposition].setX(RECTANGLE_WIDTH * boardXposition);
                viewRectangleTable[boardYposition][boardXposition].setY(RECTANGLE_HEIGHT * boardYposition);
                viewRectangleTable[boardYposition][boardXposition].setArcHeight(RECTANGLE_ARC_HEIGHT);
                viewRectangleTable[boardYposition][boardXposition].setArcWidth(RECTANGLE_ARC_WIDTH);
            }
        }
    }

    private void initGrid() {
        viewRectangleTable = new Rectangle[Y_SIZE][X_SIZE];
        for (int boardYposition = 0; boardYposition < Y_SIZE; boardYposition++) {
            if (boardYposition % 25 == 0) {
                System.out.print(".");
            }
            for (int boardXposition = 0; boardXposition < X_SIZE; boardXposition++) {

                Rectangle rectangleToAdd = new Rectangle
                        (boardXposition * RECTANGLE_WIDTH,
                                boardYposition * RECTANGLE_HEIGHT,
                                RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
                rectangleToAdd.setArcHeight(RECTANGLE_ARC_HEIGHT);
                rectangleToAdd.setArcWidth(RECTANGLE_ARC_WIDTH);
                viewRectangleTable[boardYposition][boardXposition] = rectangleToAdd;
                group.getChildren().add(viewRectangleTable[boardYposition][boardXposition]);
            }
        }
    }

    /**
     * handleInput method for handling view side and routing for InputHandler class
     *             and/or updateViewOnPos function
     *
     * @param event - any input event acceptable, mouse or key event are supported here, else is discarded
     */
    private void handleInput(InputEvent event) {
        inputHandler.handleInput(event);
        if (event.getEventType().equals(MOUSE_PRESSED)) {
            MouseEvent mouseEvent = (MouseEvent) event;
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                updateViewOnPos(mouseEvent);
            }
        }

    }


    /**
     * updateViewOnPos function
     * updates view side of model on-the-fly (before sending to model)
     * for immediate reaction to clicks
     *
     * ongoingUpdateFromView - variable for synchronising update rates - prevents flooding updates to view
     *
     * @param event - any input event acceptable, mouse or key event are supported here, else is discarded
     */
    private void updateViewOnPos(MouseEvent event) {
        ongoingUpdateFromView = true;
        Platform.runLater(() -> {
            if (event.getPickResult().getIntersectedNode() != null) {
                Rectangle rectangle = (Rectangle) event.
                        getPickResult().
                        getIntersectedNode();
                if (rectangle.getFill().equals(Color.WHITE)) {
                    rectangle.
                            setFill(DEAD_COLOR);
                } else {
                    rectangle.
                            setFill(Color.WHITE);
                }
                ongoingUpdateFromView = false;
            }
        });

    }


    /**
     * refresh method takes board as argument, scans model board and updates representing view board accordingly.
     *
     * ongoingUpdateFromModel and ongoingUpdateFromView must be set to false for update to take place
     * if not, function add a drop frame and discard this view update
     *
     * @param board - 2D board from model
     */
    @Override
    public void refresh(Dot[][] board) {
        if (!ongoingUpdateFromModel && !ongoingUpdateFromView) {
            ongoingUpdateFromModel = true;
            Platform.runLater(() -> {
                for (int i = 0; i < Y_SIZE; i++) {
                    for (int j = 0; j < X_SIZE; j++) {
                        Rectangle rectangle = viewRectangleTable[i][j];
                        if (board[i][j] != null) {
                            int generationColor = Math.max(255 - board[i][j].getGeneration() * 2, 0);
                            rectangle.setFill(Color.rgb(255, generationColor, 0));
                        } else {
                            rectangle.setFill(DEAD_COLOR);
                        }
                    }
                }
                ongoingUpdateFromModel = false;
            });
        } else {
            droppedFrames++;
        }
    }


    /**
     * getDroppedFrames function returns dropped frames count from last call
     * when called set droppedFrames to 0, and return count from call moment
     *
     * @return int count of dropped frames in time from last call to now
     */
    public int getDroppedFrames() {
        int droppedFramesCurrent = droppedFrames;
        droppedFrames = 0;
        return droppedFramesCurrent;
    }


    /**
     * attachObserver function
     * passes observer to observator class that handles input calls
     *
     * @param controller - controller of GameOfLife
     */
    @Override
    public void attachObserver(Controller controller) {
        inputHandler.addObserver(controller);
    }

}
