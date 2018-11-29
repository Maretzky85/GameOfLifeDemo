package GameOfLife.View;

import GameOfLife.Model.Board;

public abstract class AbstractView {

    public abstract void viewInit();

    public abstract void refresh(Board board);

}
