package GameOfLife;

import GameOfLife.Common.Config;
import GameOfLife.Common.SettingsMenu;
import GameOfLife.Controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    private static boolean startExampleModels = false;

    public static void main(String[] args) {
        if (args.length > 0) {
            handleArgs(args);
        } else {
            SettingsMenu.settingMenu();
            launch();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting Game...");
        Controller controller = new Controller();
        controller.controllerInit();
        if (startExampleModels) {
            controller.initExampleBoard();
        }
        controller.startLoop();
    }

    private static void handleArgs(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "-w":
                    Config.setConsoleView(false);
                    break;
                case "-e":
                    startExampleModels = true;
                    break;
                default:
                    System.out.println("Illegal argument\n" +
                            "List of available arguments:\n" +
                            "-w - start app in window with default configuration");
                    Platform.exit();
                    break;
            }
        }
        launch();
    }
}
