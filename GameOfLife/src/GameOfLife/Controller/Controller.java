package GameOfLife.Controller;

import GameOfLife.CommonUsage.BoardTooSmallException;
import GameOfLife.CommonUsage.Config;
import GameOfLife.Model.Board;
import GameOfLife.View.ConsoleView;
import GameOfLife.View.JavaFXView;
import GameOfLife.View.ViewInterface;

import java.util.Observable;
import java.util.Observer;

import static GameOfLife.CommonUsage.Config.*;

public class Controller implements Observer {

    private Board model;
    private ViewInterface view;
    private FrameControlLoop loop;


    public Controller() {

    }

    public void controllerInit() throws BoardTooSmallException {


        long startTime = System.currentTimeMillis();

        System.out.print("Initialising model");
        model = new Board(Y_SIZE, X_SIZE);
        if (Config.isStartExampleModels()) {
            model.initExampleBoard();
        }
        System.out.print(" ...done. Took " + (System.currentTimeMillis() - startTime) + " ms\n");

        System.out.print("Initialising GameOfLife.View...");
        startTime = System.currentTimeMillis();
        if (CONSOLE_VIEW) {
            view = new ConsoleView();
        } else {
            view = new JavaFXView();
        }

        System.out.print(" ...done. Took " + (System.currentTimeMillis() - startTime) + " ms\n");

        loop = new FrameControlLoop(this::updateState);
        loop.attachStatisticTimer(this::showStatistics);

        view.viewInit();
        if (!CONSOLE_VIEW) {
            loop.setDaemon(true);
            view.attachObserver(this);
            view.refresh(model);
        } else {
            loop.togglePause();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        boolean argIsPosition = true;
        boolean argIsint = true;
        try {
            int[] position = (int[]) arg;
            model.changeOnPosition(position[0], position[1]);
        } catch (ClassCastException ignored) {
            argIsPosition = false;
        }

        try {
            int option = Integer.valueOf(arg.toString());
            model.setRules(option);
        } catch (NumberFormatException ignored) {
            argIsint = false;
        }

        if (!argIsPosition && !argIsint) try {
            String key = (String) arg;
            switch (key) {
                case "p":
                    loop.togglePause();
                    break;
                case "c":
                    model.clearBoard();
                    view.refresh(model);
                    break;
                case "n":
                    model.initExampleBoard();
                    view.refresh(model);
                    break;
                case "+":
                    loop.increaseSpeed();
                    break;
                case "-":
                    loop.decreaseSpeed();
                    break;
                default:
                    break;
            }
        } catch (ClassCastException ignored) {
        }
    }


    public void startLoop() {
        loop.start();
    }

    private void updateState() {
        model.nextGen();
        view.refresh(model);
    }

    private void showStatistics() {
        if (Config.isPrintStatistics()) {
            System.out.println("Current model FPS: " + loop.getFPS() + "\nDropped View frames: " + view.getDroppedFrames() + "\n");
        }
    }
}
