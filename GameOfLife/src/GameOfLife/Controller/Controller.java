package GameOfLife.Controller;

import GameOfLife.Common.BoardTooSmallExeption;
import GameOfLife.Model.Board;
import GameOfLife.View.JavaFXView;

import static GameOfLife.Common.Config.X_SIZE;
import static GameOfLife.Common.Config.Y_SIZE;

public class Controller {

    private Board model;
    private JavaFXView view;
    private FrameControlLoop loop;


    public Controller() throws BoardTooSmallExeption {
        long startTime = System.currentTimeMillis();
        System.out.print("Initialising model");
        model = new Board(Y_SIZE, X_SIZE);
        System.out.print(" ...done. Took " + (System.currentTimeMillis() - startTime) + " ms\n");

        System.out.print("Initialising GameOfLife.View...");
        startTime = System.currentTimeMillis();
        view = new JavaFXView();
        System.out.print(" ...done. Took " + (System.currentTimeMillis() - startTime) + " ms\n");
        loop = new FrameControlLoop(this::updateModel
        );

        initDots();

        view.initScene();
        model.nextGen();
        view.refreshViewBoard(model);
        loop.setDaemon(true);
    }

    public void startLoop() {
        loop.start();
    }

    private void updateModel() {
        model.nextGen();
        view.refreshViewBoard(model);
//        view.printBoard(model);
    }

    private void initDots() {
        //        Glider
//        model.getBoard()[2][2].toggleStatus();
//        model.getBoard()[2][3].toggleStatus();
//        model.getBoard()[2][1].toggleStatus();
//        model.getBoard()[1][3].toggleStatus();
//        model.getBoard()[0][2].toggleStatus();

//        something
//        model.getBoard()[8][1].toggleStatus();
//        model.getBoard()[8][3].toggleStatus();
//        model.getBoard()[7][3].toggleStatus();
//        model.getBoard()[6][5].toggleStatus();
//        model.getBoard()[5][5].toggleStatus();
//        model.getBoard()[4][5].toggleStatus();
//        model.getBoard()[5][7].toggleStatus();
//        model.getBoard()[4][7].toggleStatus();
//        model.getBoard()[3][7].toggleStatus();
//        model.getBoard()[4][8].toggleStatus();

//        GliderGun
        model.getBoard()[8][1].toggleStatus();
        model.getBoard()[7][1].toggleStatus();
        model.getBoard()[8][2].toggleStatus();
        model.getBoard()[7][2].toggleStatus();
        model.getBoard()[8][12].toggleStatus();
        model.getBoard()[7][12].toggleStatus();
        model.getBoard()[6][12].toggleStatus();
        model.getBoard()[9][13].toggleStatus();
        model.getBoard()[5][13].toggleStatus();
        model.getBoard()[4][14].toggleStatus();
        model.getBoard()[10][14].toggleStatus();

        model.getBoard()[5][15].toggleStatus();
        model.getBoard()[9][15].toggleStatus();
        model.getBoard()[8][16].toggleStatus();
        model.getBoard()[7][16].toggleStatus();
        model.getBoard()[6][16].toggleStatus();
        model.getBoard()[8][17].toggleStatus();
        model.getBoard()[7][17].toggleStatus();
        model.getBoard()[6][17].toggleStatus();
//        model.getBoard()[4][18].toggleStatus();
//        model.getBoard()[10][18].toggleStatus();
    }
}
