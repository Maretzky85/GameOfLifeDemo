package GameOfLife.View;

import GameOfLife.Controller.Controller;
import GameOfLife.Model.Dot;


/**
 * View interface for GameOfLife
 */
public interface ViewInterface {

    void viewInit();

    void refresh(Dot[][] board);

    void attachObserver(Controller controller);

    int getDroppedFrames();
}
