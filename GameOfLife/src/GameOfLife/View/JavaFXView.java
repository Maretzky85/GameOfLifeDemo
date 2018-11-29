package GameOfLife.View;
import GameOfLife.Model.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static GameOfLife.Common.Config.*;

public class JavaFXView extends AbstractView {

    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, WIDTH, HEIGHT);

    @Override
    public void viewInit() {

        System.out.println("JavaFX: Initialising Scene.");

        grid.setPrefSize(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        Stage primaryStage = new Stage();

        long startTime = System.currentTimeMillis();

        System.out.print("JavaFX: Initialising grid");
        for (int i = 0; i < Y_SIZE; i++) {
            if (i % 25 == 0) {
                System.out.print(".");
            }
            for (int j = 0; j < X_SIZE; j++) {
                grid.add(new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT, Color.GREY), j, i);
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

    @Override
    public void refresh(Board board) {
        for (int i = 0 ; i < Y_SIZE ; i++){
            for (int j = 0 ; j < X_SIZE ; j++) {
                Rectangle rectangle = (Rectangle) (grid.getChildren().get(i * X_SIZE + j));
                if (board.getBoard()[i][j] != null) {
                    if (!rectangle.getFill().equals(SECOND_COLOR)) {
                        if (rectangle.getFill().equals(ALIVE_COLOR)) {
                            rectangle.setFill(SECOND_COLOR);
                        } else {
                            rectangle.setFill(ALIVE_COLOR);
                        }
                    }

                } else {
                    rectangle.setFill(DEAD_COLOR);
                }
            }
        }
    }
}
