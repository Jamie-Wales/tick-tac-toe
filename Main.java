package tictactoe;

import java.io.PrintStream;
import java.util.Scanner;


public class Main {

    static final PrintStream out = System.out;
    static final Scanner SCANNER = new Scanner(System.in);

    static class GameBoard {
        int height = 3;
        int width = 3;
        char[][] arr;
        String input;
        int xCount;
        int oCount;
        int gameCount;
        String gameState;

        public GameBoard() {

            this.arr = new char[height][width];
            fillAboard();
        }

        private void fillAboard() {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    arr[i][j] = ' ';
                }
            }
        }

        public void checkGameState() {
            int topAcross = arr[0][0] + arr[0][1] + arr[0][2];
            int middleAcross = arr[1][0] + arr[1][1] + arr[1][2];
            int bottomAcross = arr[2][0] + arr[2][1] + arr[2][2];
            int firstRowDown = arr[0][0] + arr[1][0] + arr[2][0];
            int secondRowDown = arr[0][1] + arr[1][1] + arr[2][1];
            int thirdRowDown = arr[0][2] + arr[1][2] + arr[2][2];
            int leftDiagonal = arr[0][0] + arr[1][1] + arr[2][2];
            int rightDiagonal = arr[2][0] + arr[1][1] + arr[0][2];

            boolean oWins = topAcross == 237 || middleAcross == 237 || bottomAcross == 237 || firstRowDown == 237
                    || secondRowDown == 237 || leftDiagonal == 237 || rightDiagonal == 237 || thirdRowDown == 237;

            boolean xWins = topAcross == 264 || middleAcross == 264 || bottomAcross == 264 || firstRowDown == 264
                    || secondRowDown == 264 || leftDiagonal == 264 || rightDiagonal == 264 || thirdRowDown == 264;

            if (oWins) {
                gameState = "O wins";
                out.println(this.gameState);
                return;

            } else if (xWins) {
                gameState = "X wins";
                out.println(this.gameState);
                return;
            } else if (xCount + oCount != 9) {
                gameState = "Game not finished";
                updateGameBoard();
            } else if (Math.abs(xCount - oCount) == 1) {
                gameState = "Draw";
                out.println(this.gameState);
                return;
            }
        }

        public void printGameBoard() {
            out.println("---------");
            for (int i = 0; i < height; i++) {
                if (i == 1 || i == 2) {
                    out.println();
                }
                out.print("| ");
                for (int j = 0; j < width; j++) {
                    out.print(arr[i][j] + " ");
                    if (j == 2) {
                        out.print("|");
                    }
                }

            }
            out.println();
            out.println("---------");
        }

        public void updateGameBoard() {
            out.println("Enter the coordinates:");

            while (true) {
                Boolean input = SCANNER.hasNextInt();

                if (!input) {
                    out.println("You should enter numbers!");
                    break;

                } else {
                    int cols = SCANNER.nextInt();
                    int rows = SCANNER.nextInt();
                    cols--;
                    rows--;

                    if (cols >= arr.length || rows >= arr[0].length || (cols < 0 || rows < 0)) {
                        out.println("Out of bounds please try again");
                        continue;
                    }

                    if (arr[cols][rows] == 'X' || arr[cols][rows] == 'O') {

                        out.println("This cell is occupied! Choose another one!");

                    } else {
                        if (gameCount % 2 == 0) {
                            arr[cols][rows] = 'X';
                            xCount++;
                        } else {
                            arr[cols][rows] = 'O';
                            oCount++;
                        }
                        this.printGameBoard();
                        gameCount++;
                        checkGameState();
                        break;
                    }
                }
            }

        }


        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            GameBoard gameboard = new GameBoard();
            gameboard.printGameBoard();
            gameboard.updateGameBoard();

        }
    }
}




