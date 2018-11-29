package GameOfLife;

import GameOfLife.Common.Config;
import GameOfLife.Common.SettingsMenu;
import GameOfLife.Controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameOfLife extends Application {

    public static void main(String[] args) {
        if (args.length > 0) {
            handleArgs(args);
        } else {
            SettingsMenu.settingMenu();
            launch();
        }

    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Starting Game...");
        try {
            Controller controller = new Controller();
            controller.controllerInit();
            controller.startLoop();
        } catch (Exception e) {
            System.out.println("Internal program error");
            System.exit(404);
        }

    }

    private static void handleArgs(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "-w":
                    Config.setConsoleView(false);
                    break;
                case "-e":
                    Config.toggleStartExampleModels();
                    break;
                case "-c":
                    Config.setConsoleView(true);
                    break;
                case "-h":
                    SettingsMenu.showHelp();
                    Platform.exit();
                    break;
                default:
                    System.out.println("Illegal argument\n" +
                            "List of available arguments:\n" +
                            "-w - start app in window with default configuration" +
                            "-e - start app with example models" +
                            "-c - start app with console output" +
                            "-h - display help");
                    System.exit(0);
                    break;
            }
        }
        launch();
    }
}
