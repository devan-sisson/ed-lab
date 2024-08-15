package com.gameoflife;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.IntFunction;

public class GameState {
    private final String ALIVE = "⬜"; //""● ";
    private final String DEAD = "⬛"; //""○ ";

    private int xCount;
    private int yCount;
    private int startingLives = 0;
    private int[][] board;

    public GameState() {
        this.xCount = 10;
        this.yCount = 20;
        this.board = new int[xCount][yCount];
    }

    private void initGameBoard(){
        Random random = new Random();
        int lives = 0;
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                board[i][j] = 0;
                if (random.nextInt(10) % 2 == 0){
                    board[i][j] = 1;
                    lives++;
                }
            }
        }

        this.startingLives = lives;
    }

    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                Runtime.getRuntime().exec("cls");
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("clear");
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printGameBoard() {
      int currentAlive = 0;
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                if (this.board[i][j] == 1) {
                    board.append(ALIVE);
                    currentAlive++;
                } else {
                    board.append(DEAD);
                }
            }
            board.append("\n");
        }
        board.append(String.format("alive/starting: %d/%d", currentAlive, startingLives));

        clearConsole();
        System.out.println(board);
    }

    private void updateGameBoard() {
        int [][] _board = new int[xCount][yCount];

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                int neighbors = determineNeighbors(i, j);
                if (neighbors <= 1) {
                    _board[i][j] = 0;
                } else if (neighbors == 2 && board[i][j] == 1) {
                    _board[i][j] = 1;
                } else if (neighbors == 3) {
                    _board[i][j] = 1;
                } else if (neighbors > 3) {
                    _board[i][j] = 0;
                }
            }
        }

        this.board = _board;
    }

    private int determineNeighbors(int x, int y) {
        int count = 0;

        IntFunction up = i -> board[x][y-1]; // ↑
        if (x == 0 && y == 0){ // top left (0, 0)
            count += board[x+1][y+1]; // ↘
            count += board[x+1][y]; // →
            count += board[x][y+1]; // ↓
        } else if (x == 0 && y == yCount -1) { // bottom left (0, 10)
            count += board[x+1][y-1]; // ↗
            count += board[x+1][y];
            count += board[x][y-1]; // ↑
        } else if (x == xCount - 1 && y == 0) { // top right (10, 0)
            count += board[x-1][y+1]; // ↙
            count += board[x-1][y];
            count += board[x][y+1]; // ↓
        } else if (x == xCount - 1 && y == yCount - 1) { // bottom right (10, 10)
            count += board[x-1][y-1]; // ↖
            count += board[x-1][y]; // ←
            count += board[x][y-1]; // ↑
        } else if (y == 0) { // top edge (0, y)
            count += board[x][y+1]; // ↓
            count += board[x+1][y]; // →
            count += board[x-1][y]; // ←

            count += board[x+1][y+1]; // ↘
            count += board[x-1][y+1]; // ↙
        } else if (x == 0) { // left edge (x, 0)
            count += board[x][y-1]; // ↑
            count += board[x][y+1]; // ↓
            count += board[x+1][y]; // →

            count += board[x+1][y-1]; // ↗
            count += board[x+1][y+1]; // ↘
        } else if (x == xCount - 1) { // right edge (10, y)
            count += board[x][y-1]; // ↑
            count += board[x][y+1]; // ↓
            count += board[x-1][y]; // ←

            count += board[x-1][y-1]; // ↖
            count += board[x-1][y+1]; // ↙
        } else if (y == yCount - 1) { // bottom edge (x, 10)
            count += board[x][y-1]; // ↑
            count += board[x+1][y]; // →
            count += board[x-1][y]; // ←

            count += board[x-1][y-1]; // ↖
            count += board[x+1][y-1]; // ↗
        } else {
            count += board[x][y-1]; // ↑
            count +=
            count += board[x][y+1]; // ↓
            count += board[x+1][y]; // →
            count += board[x-1][y]; // ←

            count += board[x-1][y-1]; // ↖
            count += board[x+1][y-1]; // ↗
            count += board[x+1][y+1]; // ↘
            count += board[x-1][y+1]; // ↙
        }

        return count;
    }

    public void Run() {
        initGameBoard();
        int end = (yCount * xCount) / 4;
        try {
            while (end > 0) {
                printGameBoard();
                System.out.println("end: " + end);
                updateGameBoard();
                end--;
                TimeUnit.MILLISECONDS.sleep(300);
            };

            printGameBoard();
            System.out.println("turns left: " + end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //

    }
}
