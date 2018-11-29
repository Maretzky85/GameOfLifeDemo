package GameOfLife.Model;

import GameOfLife.Common.BoardTooSmallExeption;

public class Board {
    private Dot[][] board;

    public Board(int y, int x) throws BoardTooSmallExeption {
        if (x < 1 || y < 1) {
            throw new BoardTooSmallExeption("Board must be at least 1 x 1");
        }
        this.board = new Dot[y][x];
    }


    public Dot[][] getBoard() {
        return board;
    }

    public void nextGen() {

        Dot[][] tempBoard = newEmptyBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int aliveNeighbors = getNeighbors(j, i);
                Dot currentSourceDot = board[i][j];
                Dot currentDestDot = tempBoard[i][j];

                if ((currentSourceDot != null && aliveNeighbors <= 3) && aliveNeighbors >= 2) {
                    tempBoard[i][j] = currentSourceDot;
                } else if (currentDestDot == null && aliveNeighbors == 3) {
                    tempBoard[i][j] = new Dot();
                }
            }
        }
        this.board = tempBoard;
    }

    private Dot[][] newEmptyBoard() {
        int xLength = board[0].length;
        int yLength = board.length;
        return new Dot[yLength][xLength];
    }

    private int getNeighbors(int y, int x) {
        int neighbors = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    if (board[x + i][y + j] != null && !(i == 0 && j == 0)) {
                        neighbors++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return neighbors;
    }

    public void updateOnPosition(int x, int y) {
        if (getBoard()[y][x] == null) {
            getBoard()[y][x] = new Dot();
        } else {
            getBoard()[y][x] = null;
        }
    }
}
