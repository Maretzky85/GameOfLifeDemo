package GameOfLife.View;
import GameOfLife.Model.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static GameOfLife.Common.Config.*;

public class JavaFXView {

    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, WIDTH, HEIGTH);


    public void initScene() {

        System.out.println("JavaFX: Initialising Scene.");

        int rectangleWidth = WIDTH / X_SIZE;
        int rectangleHeigth = HEIGTH / Y_SIZE;

        grid.setPrefSize(WIDTH, HEIGTH);
        Stage primaryStage = new Stage();

        long startTime = System.currentTimeMillis();
        System.out.print("JavaFX: Creating grid. ");
        for (int i = 0; i < Y_SIZE; i++) {
            if (i % 25 == 0) {
                System.out.print(".");
            }
            grid.addRow(Y_SIZE);
            for (int j = 0; j < X_SIZE; j++) {
                grid.addColumn(X_SIZE);
            }
        }
        System.out.print(" Done. Creating grid took " + (System.currentTimeMillis() - startTime) + " ms\n");

        startTime = System.currentTimeMillis();
        System.out.print("JavaFX: Initialising grid");
        for (int i = 0; i < Y_SIZE; i++) {
            if (i % 25 == 0) {
                System.out.print(".");
            }
            for (int j = 0; j < X_SIZE; j++) {
                grid.add(new Rectangle(rectangleWidth, rectangleHeigth, Color.BLACK), i, j);
            }
        }
        System.out.print(" Done.");
        System.out.print(" Initialising grid took " + (System.currentTimeMillis() - startTime) + " ms\n");

        System.out.println("JavaFX: Setting-up scene");
        primaryStage.setScene(scene);
        System.out.println("JavaFX: Finished setting-up scene");

        System.out.println("JavaFX: setting-up window");
        startTime = System.currentTimeMillis();
        primaryStage.show();
        System.out.println("JavaFX: Finished showing window");
        System.out.println("JavaFX: Preparing window took " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public void refreshViewBoard(Board board){
        for (int i = 0 ; i < Y_SIZE ; i++){
            for (int j = 0 ; j < X_SIZE ; j++) {
                Rectangle rectangle = (Rectangle) (grid.getChildren().get(i * Y_SIZE + j));
                if(board.getBoard()[i][j].isAlive()){
                    rectangle.setFill(ALIVE_COLOR);
                }
                else{
                    rectangle.setFill(DEAD_COLOR);
                }

            }
        }
    }
}
