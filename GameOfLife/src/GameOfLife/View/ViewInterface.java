package GameOfLife.View;

import GameOfLife.Controller.Controller;
import GameOfLife.Model.Board;

public interface ViewInterface {

    void viewInit();

    void refresh(Board board);

    void attachObserver(Controller controller);

}
