package GameOfLife.Controller;

import GameOfLife.Common.BoardTooSmallExeption;
import GameOfLife.Model.Board;
import GameOfLife.Model.Dot;
import GameOfLife.View.AbstractView;
import GameOfLife.View.ConsoleView;
import GameOfLife.View.JavaFXView;

import static GameOfLife.Common.Config.*;

public class Controller {

    private Board model;
    private AbstractView view;
    private FrameControlLoop loop;


    public Controller() {

    }

    public void controllerInit() throws BoardTooSmallExeption {
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

        initDots();

        view.viewInit();
        if (!CONSOLE_VIEW) {
            loop.setDaemon(true);
        }

    }

    public void startLoop() {
        loop.start();
    }

    private void updateModel() {
        model.nextGen();
        view.refresh(model);
    }

    private void initDots() {
        int xOffset = 100;
        int yOffset = 30;
        int x2Offset = 80;
        int y2Offset = 100;
        int x3Offset = 0;
        int y3Offset = 0;

        //Box
//        model.getBoard()[2][1] = new Dot();
//        model.getBoard()[2][2] = new Dot();
//        model.getBoard()[3][1] = new Dot();
//        model.getBoard()[3][2] = new Dot();


        //        Glider
        model.getBoard()[2 + y3Offset][2 + x3Offset] = new Dot();
        model.getBoard()[2 + y3Offset][3 + x3Offset] = new Dot();
        model.getBoard()[2 + y3Offset][1 + x3Offset] = new Dot();
        model.getBoard()[1 + y3Offset][3 + x3Offset] = new Dot();
        model.getBoard()[0 + y3Offset][2 + x3Offset] = new Dot();

//        something
        model.getBoard()[8 + x2Offset][1 + y2Offset] = new Dot();
        model.getBoard()[8 + x2Offset][3 + y2Offset] = new Dot();
        model.getBoard()[7 + x2Offset][3 + y2Offset] = new Dot();
        model.getBoard()[6 + x2Offset][5 + y2Offset] = new Dot();
        model.getBoard()[5 + x2Offset][5 + y2Offset] = new Dot();
        model.getBoard()[4 + x2Offset][5 + y2Offset] = new Dot();
        model.getBoard()[5 + x2Offset][7 + y2Offset] = new Dot();
        model.getBoard()[4 + x2Offset][7 + y2Offset] = new Dot();
        model.getBoard()[3 + x2Offset][7 + y2Offset] = new Dot();
        model.getBoard()[4 + x2Offset][8 + y2Offset] = new Dot();

//        GliderGun
        model.getBoard()[8 + xOffset][1 + yOffset] = new Dot();
        model.getBoard()[7 + xOffset][1 + yOffset] = new Dot();
        model.getBoard()[8 + xOffset][2 + yOffset] = new Dot();
        model.getBoard()[7 + xOffset][2 + yOffset] = new Dot();
        model.getBoard()[8 + xOffset][12 + yOffset] = new Dot();
        model.getBoard()[7 + xOffset][12 + yOffset] = new Dot();
        model.getBoard()[6 + xOffset][12 + yOffset] = new Dot();
        model.getBoard()[9 + xOffset][13 + yOffset] = new Dot();
        model.getBoard()[5 + xOffset][13 + yOffset] = new Dot();
        model.getBoard()[4 + xOffset][14 + yOffset] = new Dot();
        model.getBoard()[10 + xOffset][14 + yOffset] = new Dot();
        model.getBoard()[5 + xOffset][15 + yOffset] = new Dot();
        model.getBoard()[9 + xOffset][15 + yOffset] = new Dot();
        model.getBoard()[8 + xOffset][16 + yOffset] = new Dot();
        model.getBoard()[7 + xOffset][16 + yOffset] = new Dot();
        model.getBoard()[6 + xOffset][16 + yOffset] = new Dot();
        model.getBoard()[8 + xOffset][17 + yOffset] = new Dot();
        model.getBoard()[7 + xOffset][17 + yOffset] = new Dot();
        model.getBoard()[6 + xOffset][17 + yOffset] = new Dot();
//        model.getBoard()[4][18] = new Dot();
//        model.getBoard()[10][18] = new Dot();
    }
}
