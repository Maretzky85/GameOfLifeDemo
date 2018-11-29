package GameOfLife.View;

import GameOfLife.Model.Board;

public class ConsoleView extends AbstractView {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void viewInit() {
        clearScreen();
    }

    @Override
    public void refresh(Board board) {
        clearScreen();

        for (int i = 0; i < board.getBoard().length; i++) {
            System.out.println();
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                if (board.getBoard()[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }

            }
        }
        System.out.println();
        System.out.println();
    }
}
