package View;

import Model.Board;

public class ConsoleView {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printBoard(Board board) {
        clearScreen();
        for (int i = 0; i < board.getBoard().length; i++) {
            System.out.println();
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                System.out.print(board.getBoard()[i][j]);
            }
        }
        System.out.println();
        System.out.println();
    }
}
