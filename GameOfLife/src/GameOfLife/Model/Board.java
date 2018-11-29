package GameOfLife.Model;

import GameOfLife.Common.BoardTooSmallException;

import java.util.Arrays;

public class Board {
    private Dot[][] board;

    private int[] ruleToLive = new int[]{2, 3};
    private int[] ruleToGetAlive = new int[]{3};

    public Board(int y, int x) throws BoardTooSmallException {
        if (x < 5 || y < 5) {
            throw new BoardTooSmallException("Board must be at least 5 x 5");
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

                if (currentSourceDot != null && Arrays.stream(ruleToLive).anyMatch(value -> value == aliveNeighbors)) {
                    tempBoard[i][j] = currentSourceDot;
                } else if ((currentDestDot == null && Arrays.stream(ruleToGetAlive).anyMatch(value -> value == aliveNeighbors))) {
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

    public void clearBoard() {
        board = newEmptyBoard();
    }

    public void setRules(int i) {
        switch (i) {
            case 1:
                ruleToLive = new int[]{2, 3};
                ruleToGetAlive = new int[]{3};
                break;
            case 2:
                ruleToGetAlive = new int[]{3, 4, 5};
                ruleToGetAlive = new int[]{3, 4, 5};
                break;
            case 3:
                ruleToLive = new int[]{2, 4, 5};
                ruleToGetAlive = new int[]{3, 6, 8};
            default:
                break;
        }
    }

    public void initExampleBoard() {
        int xOffset = 0;
        int yOffset = 0;
        int x2Offset = 40;
        int y2Offset = 20;
        int x3Offset = 40;
        int y3Offset = 20;
        try {
//        Glider
            board[2 + y3Offset][2 + x3Offset] = new Dot();
            board[2 + y3Offset][3 + x3Offset] = new Dot();
            board[2 + y3Offset][1 + x3Offset] = new Dot();
            board[1 + y3Offset][3 + x3Offset] = new Dot();
            board[y3Offset][2 + x3Offset] = new Dot();

//        something
            board[8 + x2Offset][1 + y2Offset] = new Dot();
            board[8 + x2Offset][3 + y2Offset] = new Dot();
            board[7 + x2Offset][3 + y2Offset] = new Dot();
            board[6 + x2Offset][5 + y2Offset] = new Dot();
            board[5 + x2Offset][5 + y2Offset] = new Dot();
            board[4 + x2Offset][5 + y2Offset] = new Dot();
            board[5 + x2Offset][7 + y2Offset] = new Dot();
            board[4 + x2Offset][7 + y2Offset] = new Dot();
            board[3 + x2Offset][7 + y2Offset] = new Dot();
            board[4 + x2Offset][8 + y2Offset] = new Dot();

//        GliderGun

            board[8 + xOffset][1 + yOffset] = new Dot();
            board[7 + xOffset][1 + yOffset] = new Dot();
            board[8 + xOffset][2 + yOffset] = new Dot();
            board[7 + xOffset][2 + yOffset] = new Dot();

            board[8 + xOffset][12 + yOffset] = new Dot();
            board[7 + xOffset][12 + yOffset] = new Dot();
            board[6 + xOffset][12 + yOffset] = new Dot();

            board[9 + xOffset][13 + yOffset] = new Dot();
            board[5 + xOffset][13 + yOffset] = new Dot();

            board[4 + xOffset][14 + yOffset] = new Dot();
            board[10 + xOffset][14 + yOffset] = new Dot();

            board[5 + xOffset][15 + yOffset] = new Dot();
            board[9 + xOffset][15 + yOffset] = new Dot();

            board[8 + xOffset][16 + yOffset] = new Dot();
            board[7 + xOffset][16 + yOffset] = new Dot();
            board[6 + xOffset][16 + yOffset] = new Dot();

            board[8 + xOffset][17 + yOffset] = new Dot();
            board[7 + xOffset][17 + yOffset] = new Dot();
            board[6 + xOffset][17 + yOffset] = new Dot();

            board[6 + xOffset][22 + yOffset] = new Dot();
            board[5 + xOffset][22 + yOffset] = new Dot();
            board[4 + xOffset][22 + yOffset] = new Dot();

            board[3 + xOffset][23 + yOffset] = new Dot();
            board[4 + xOffset][23 + yOffset] = new Dot();
            board[6 + xOffset][23 + yOffset] = new Dot();
            board[7 + xOffset][23 + yOffset] = new Dot();

            board[3 + xOffset][24 + yOffset] = new Dot();
            board[4 + xOffset][24 + yOffset] = new Dot();
            board[6 + xOffset][24 + yOffset] = new Dot();
            board[7 + xOffset][24 + yOffset] = new Dot();

            board[3 + xOffset][25 + yOffset] = new Dot();
            board[4 + xOffset][25 + yOffset] = new Dot();
            board[5 + xOffset][25 + yOffset] = new Dot();
            board[6 + xOffset][25 + yOffset] = new Dot();
            board[7 + xOffset][25 + yOffset] = new Dot();
            board[2 + xOffset][26 + yOffset] = new Dot();
            board[3 + xOffset][26 + yOffset] = new Dot();
            board[7 + xOffset][26 + yOffset] = new Dot();
            board[8 + xOffset][26 + yOffset] = new Dot();

            board[3 + xOffset][31 + yOffset] = new Dot();
            board[4 + xOffset][31 + yOffset] = new Dot();
            board[5 + xOffset][35 + yOffset] = new Dot();
            board[6 + xOffset][35 + yOffset] = new Dot();
            board[5 + xOffset][36 + yOffset] = new Dot();
            board[6 + xOffset][36 + yOffset] = new Dot();
        } catch (IndexOutOfBoundsException ignore) {

        }
    }
}
