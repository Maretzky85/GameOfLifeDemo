package GameOfLife;

import GameOfLife.Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting Game...");
        Controller controller = new Controller();
        controller.controllerInit();
        controller.startLoop();
    }
}
