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
//        for (int i = 0; i < boardToInit.length; i++) {
//            for (int j = 0; j < boardToInit[0].length; j++) {
//                boardToInit[i][j] = new Dot();
//            }
//        }
    }

    public Dot[][] getBoard() {
        return board;
    }

    public void nextGen() {

        Dot[][] tempBoard = newEmptyBoard();
        Dot dotToCopy = null;

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

//                try {
//                    currentSourceDot = board[i][j].isAlive();
//                    dotToCopy = board[i][j];
//                }catch (NullPointerException exeption){
//                    currentSourceDot = false;
//                }
//
//                Dot currentDestDot = tempBoard[i][j];
//                int aliveNeighbors = getNeighbors(j, i);
//
//                if ((currentSourceDot && aliveNeighbors <= 3) && (currentSourceDot && aliveNeighbors >= 2)) {
//                    currentDestDot = dotToCopy;
////                    currentDestDot.toggleStatus();
//                }
//                if (!currentSourceDot && aliveNeighbors == 3) {
//                    currentDestDot = dotToCopy;
////                    currentDestDot.toggleStatus();
//                }
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
//        System.out.println("X,Y: "+x+" "+y+" "+ "N: "+neighbors);
        return neighbors;
    }
}
