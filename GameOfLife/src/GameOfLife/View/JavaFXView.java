package GameOfLife.View;

import GameOfLife.Controller.Controller;
import GameOfLife.Model.Board;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static GameOfLife.Common.Config.*;

public class JavaFXView implements ViewInterface {

    private Group group = new Group();
    private Scene scene = new Scene(group, WIDTH, HEIGHT);
    private InputHandler inputHandler = new InputHandler();
    private Stage primaryStage;

    @Override
    public void viewInit() {
        scene.setOnKeyPressed(this::handleKeyboard);

        scene.setOnMousePressed(this::handleClick);
        System.out.println("JavaFX: Initialising Scene.");

        Stage primaryStage = new Stage();

        long startTime = System.currentTimeMillis();

        System.out.print("JavaFX: Initialising grid");
        for (int i = 0; i < Y_SIZE; i++) {
            if (i % 25 == 0) {
                System.out.print(".");
            }
            for (int j = 0; j < X_SIZE; j++) {
                Rectangle rectangleToAdd = new Rectangle(j * RECTANGLE_WIDTH, i * RECTANGLE_HEIGHT, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
                rectangleToAdd.setArcHeight(2);
                rectangleToAdd.setArcWidth(2);
                group.getChildren().add(rectangleToAdd);
            }
        }
        System.out.print(" Done. Initialising grid took " + (System.currentTimeMillis() - startTime) + " ms\n");

        System.out.println("JavaFX: Setting-up scene");
        primaryStage.setScene(scene);
        System.out.println("JavaFX: Finished setting-up scene");

        System.out.println("JavaFX: setting-up window");
        startTime = System.currentTimeMillis();
        primaryStage.show();
        System.out.println("JavaFX: Preparing window took " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private void handleClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            int gridXposition = (int) (event.getX() - RECTANGLE_WIDTH / 2) / RECTANGLE_WIDTH;
            int gridYposition = (int) (event.getY() - RECTANGLE_HEIGHT / 2) / RECTANGLE_HEIGHT;
            Rectangle rectangle = (Rectangle) (group.getChildren().get(gridYposition * X_SIZE + gridXposition));
            if (rectangle.getFill().equals(Color.WHITE)) {
                rectangle.setFill(DEAD_COLOR);
            } else {
                rectangle.setFill(Color.WHITE);
            }

            inputHandler.toggleDotInModel(gridXposition, gridYposition);
        } else if (event.getButton() == MouseButton.MIDDLE) {
            int gridXposition = (int) (event.getX() - RECTANGLE_WIDTH / 2) / RECTANGLE_WIDTH;
            int gridYposition = (int) (event.getY() - RECTANGLE_HEIGHT / 2) / RECTANGLE_HEIGHT;
            Rectangle rectangle = (Rectangle) (group.getChildren().get(gridYposition * X_SIZE + gridXposition));
            System.out.println(rectangle);
        } else if (event.getButton() == MouseButton.SECONDARY) {
            inputHandler.requestGamePause();
        }
    }

    private void handleKeyboard(KeyEvent event) {
        inputHandler.handleKeyboardInput(event);
    }

    @Override
    public void refresh(Board board) {
        for (int i = 0 ; i < Y_SIZE ; i++){
            for (int j = 0 ; j < X_SIZE ; j++) {
                Rectangle rectangle = (Rectangle) (group.getChildren().get(i * X_SIZE + j));
                if (board.getBoard()[i][j] != null) {
                    int generationColor = Math.max(255 - board.getBoard()[i][j].getGeneration() * 40, 0);
                    rectangle.setFill(Color.rgb(255, generationColor, 0));
                } else {
                    rectangle.setFill(DEAD_COLOR);
                }
            }
        }
    }

    @Override
    public void attachObserver(Controller controller) {
        inputHandler.addObserver(controller);
    }

}
