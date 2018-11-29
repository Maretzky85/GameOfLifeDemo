package GameOfLife.Controller;

import GameOfLife.Common.BoardTooSmallException;
import GameOfLife.Model.Board;
import GameOfLife.View.ConsoleView;
import GameOfLife.View.JavaFXView;
import GameOfLife.View.ViewInterface;

import java.util.Observable;
import java.util.Observer;

import static GameOfLife.Common.Config.*;

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
        System.out.print(" ...done. Took " + (System.currentTimeMillis() - startTime) + " ms\n");

        System.out.print("Initialising GameOfLife.View...");
        startTime = System.currentTimeMillis();
        if (CONSOLE_VIEW) {
            view = new ConsoleView();
        } else {
            view = new JavaFXView();
        }

        System.out.print(" ...done. Took " + (System.currentTimeMillis() - startTime) + " ms\n");

        loop = new FrameControlLoop(this::updateModel);

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
        boolean argIsPosition = false;
        try {
            int[] position = (int[]) arg;
            argIsPosition = true;
            model.updateOnPosition(position[0], position[1]);
        } catch (ClassCastException ignored) {
        }

        if (!argIsPosition) try {
            String key = (String) arg;
            switch (key) {
                case "1":
                    model.setRules(1);
                    break;
                case "2":
                    model.setRules(2);
                    break;
                case "3":
                    model.setRules(3);
                    break;
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

    private void updateModel() {
        model.nextGen();
        view.refresh(model);
    }

    public void initExampleBoard() {
        model.initExampleBoard();
    }
}
