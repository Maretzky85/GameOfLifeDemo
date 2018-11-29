package GameOfLife.Model;

import GameOfLife.Common.BoardTooSmallExeption;

public class Board {
    private Dot[][] board;

    public Board(int x, int y) throws BoardTooSmallExeption {
        if (x < 1 || y < 1) {
            throw new BoardTooSmallExeption("Board must be at least 1 x 1");
        }
        this.board = new Dot[x][y];
        initBoard(this.board);
    }

    private void initBoard(Dot[][] boardToInit) {
//        System.out.print("Initialising Board");
        for (int i = 0; i < boardToInit.length; i++) {
            for (int j = 0; j < boardToInit[0].length; j++) {
                boardToInit[i][j] = new Dot();
            }
        }
//        System.out.print("  ...Done\n");
    }

    public Dot[][] getBoard() {
        return board;
    }

    public void nextGen() {

        Dot[][] tempBoard = newEmptyBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                Dot currentSourceDot = board[i][j];
                Dot currentDestDot = tempBoard[i][j];
                int aliveNeighbors = getNeighbors(j, i);

                if ((currentSourceDot.isAlive() && aliveNeighbors <= 3) && (currentSourceDot.isAlive() && aliveNeighbors >= 2)) {
                    currentDestDot.toggleStatus();
                }
                if (!currentSourceDot.isAlive() && aliveNeighbors == 3) {
                    currentDestDot.toggleStatus();
                }
            }
        }
        this.board = tempBoard;
    }

    private Dot[][] newEmptyBoard() {
        int xLength = board[0].length;
        int yLength = board.length;
        Dot[][] tempBoard = new Dot[yLength][xLength];
        initBoard(tempBoard);
        return tempBoard;

    }

    private int getNeighbors(int y, int x) {
        int neighbors = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    if (board[x + i][y + j].isAlive() && !(i == 0 && j == 0)) {
                        neighbors++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return neighbors;
    }
}
