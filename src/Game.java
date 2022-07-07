
import java.util.Random;
import java.util.Scanner;

public class Game {
    static char empty = ' ';
    static char rowBorder = '-';
    static char colBorder = '|';
    static char exit = 'E';
    static char people = 'P';
    static char deadPeople = 'D';
    static char rock = 'R';
    static char bush = '#';

    public static void main(String[] args) {
        //да оправя случая когато храстите запушват изхода
        //fix difficult percent
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select difficult from 1 to 3");
        int difficult;
        int matrixLength = 0;
        int difficultPercent = 0;
        boolean gameOver = false;
        boolean success = false;
        int rightBorderBushPosition = -1;
        int downBorderBushPosition = -1;

        int rockRow;
        int rockCol;


        do {
            difficult = sc.nextInt();
            if (difficult > 3 || difficult <= 0) {
                System.out.println("Please select difficult from 1 to 3");
            }

            switch (difficult) {
                case 1 -> {
                    matrixLength = 12;
                    //12.5 percent
                    difficultPercent = 8;
                }
                case 2 -> {
                    matrixLength = 17;
                    //20 percent
                    difficultPercent = 5;
                }
                case 3 -> {
                    matrixLength = 27;
                    //33.33 percent
                    difficultPercent = 3;
                }
            }
        }
        while (difficult > 3 || difficult <= 0);

        //initializing matrix
        char[][] matrix = new char[matrixLength][matrixLength];
        //add borders and replace char 0 with ' '
        fillMatrix(matrix);
        //add exit
        matrix[matrix.length - 2][matrix.length - 2] = exit;
        //add people
        int peopleRow = giveMeRandomNum(1, matrixLength - 2);
        int peopleCol = giveMeRandomNum(1, matrixLength - 2);
        matrix[peopleRow][peopleCol] = people;

        //adds the bushes
        int count = (matrixLength * matrixLength) / difficultPercent;
        while (count > 0) {
            int bushRow = giveMeRandomNum(matrixLength);
            int bushCol = giveMeRandomNum(matrixLength);
            if (canSpawnBushes(matrix, bushRow, bushCol)) {
                if (bushRow == matrixLength - 2) {
                    downBorderBushPosition = bushCol;
                }
                if (bushCol == matrixLength - 2) {
                    rightBorderBushPosition = bushRow;
                }
                matrix[bushRow][bushCol] = bush;
                count--;
            }
        }

        //add the rock
        while (true) {
            rockRow = giveMeRandomNum(matrixLength);
            rockCol = giveMeRandomNum(matrixLength);
            if (canSpawnRocks(matrix, rockRow, rockCol) && canRockMove(matrix, rockRow, rockCol)) {
                matrix[rockRow][rockCol] = rock;
                break;
            }
        }

        System.out.println("You can move with w = forward, a = left, s = backward and d = right");
        //print matrix
        printMatrix(matrix);

        while (!gameOver && !success) {

            char directions = sc.next().charAt(0);

            switch (directions) {
                //up
                case 'w':
                    //game over
                    if (isOutOfBounds(matrix, peopleRow - 1)) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow - 1][peopleCol] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow - 1][peopleCol] == rock) {
                        rockRow = peopleRow - 1;
                        //game over
                        if (isOutOfBounds(matrix, rockRow - 1)) {
                            System.out.println("Game over");
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow - 1][rockCol] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = empty;
                        matrix[--peopleRow][peopleCol] = people;
                        matrix[--rockRow][rockCol] = rock;
                        break;
                    }

                    matrix[peopleRow][peopleCol] = empty;
                    matrix[--peopleRow][peopleCol] = people;
                    break;
                //left
                case 'a':
                    //game over
                    if (isOutOfBounds(matrix, peopleCol - 1)) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow][peopleCol - 1] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow][peopleCol - 1] == rock) {
                        rockCol = peopleCol - 1;
                        //game over
                        if (isOutOfBounds(matrix, rockCol - 1)) {
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow][rockCol - 1] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = empty;
                        matrix[peopleRow][--peopleCol] = people;
                        matrix[rockRow][--rockCol] = rock;
                        break;

                    }

                    matrix[peopleRow][peopleCol] = empty;
                    matrix[peopleRow][--peopleCol] = people;
                    break;
                //down
                case 's':
                    //game over
                    if (isOutOfBounds(matrix, peopleRow + 1)) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow + 1][peopleCol] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow + 1][peopleCol] == rock) {
                        rockRow = peopleRow + 1;
                        //game over
                        if (isOutOfBounds(matrix, rockRow + 1)) {
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow + 1][rockCol] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = empty;
                        matrix[++peopleRow][peopleCol] = people;
                        matrix[++rockRow][rockCol] = rock;
                        break;

                    }

                    matrix[peopleRow][peopleCol] = empty;
                    matrix[++peopleRow][peopleCol] = people;
                    break;
                //right
                case 'd':
                    //game over
                    if (isOutOfBounds(matrix, peopleCol + 1)) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow][peopleCol + 1] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow][peopleCol + 1] == rock) {
                        rockCol = peopleCol + 1;
                        //game over
                        if (isOutOfBounds(matrix, rockCol + 1)) {
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow][rockCol + 1] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = empty;
                        matrix[peopleRow][++peopleCol] = people;
                        matrix[rockRow][++rockCol] = rock;
                        break;

                    }
                    matrix[peopleRow][peopleCol] = empty;
                    matrix[peopleRow][++peopleCol] = people;
                    break;

            }

            if (rockRow == matrixLength - 2 && rockCol == matrixLength - 2) {
                success = true;
            }

            //Rock game over logic
            if (!canRockMove(matrix, rockRow, rockCol)
                    || (rockCol == matrixLength - 2 && rightBorderBushPosition > rockRow || rightBorderBushPosition-1 == rockRow)
                    || (rockRow == matrixLength - 2 && downBorderBushPosition > rockCol || downBorderBushPosition-1 == rockCol)) {
                gameOver = true;
            }
            printMatrix(matrix);
            if (gameOver) {
                matrix[peopleRow][peopleCol] = deadPeople;
                System.out.println("Game over :(");
            }

            if (success) {
                System.out.println("Congrats you win the game");
            }
        }
    }

    static int giveMeRandomNum(int from, int to) {
        Random random = new Random();
        return random.nextInt(from, to);
    }

    static int giveMeRandomNum(int to) {
        Random random = new Random();
        return random.nextInt(to);
    }

    static boolean canSpawnBushes(char[][] matrix, int row, int col) {
        return (matrix[row][col] != people && matrix[row][col] != rowBorder
                && matrix[row][col] != colBorder && matrix[row][col] != exit);
    }

    static boolean canSpawnRocks(char[][] matrix, int row, int col) {
        return (matrix[row][col] != people && matrix[row][col] != rowBorder
                && matrix[row][col] != colBorder && matrix[row][col] != exit && matrix[row][col] != bush);
    }

    static boolean canRockMove(char[][] matrix, int rockRow, int rockCol) {

        boolean leftBush = matrix[rockRow][rockCol - 1] == bush;
        boolean rightBush = matrix[rockRow][rockCol + 1] == bush;
        boolean upBush = matrix[rockRow - 1][rockCol] == bush;
        boolean downBush = matrix[rockRow + 1][rockCol] == bush;
        boolean upBorder = matrix[rockRow - 1][rockCol] == rowBorder;
        boolean leftBorder = matrix[rockRow][rockCol + 1] == colBorder;
        return !((leftBush && upBush) || (leftBush && downBush) ||
                (rightBush && upBush) || (rightBush && downBush)
                || upBorder || leftBorder);
    }


    static void printMatrix(char[][] matrix) {
        for (char[] ints : matrix) {
            for (char anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    static char[][] fillMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {

            for (int col = 0; col < matrix.length; col++) {
                if (row == 0 || row == matrix.length - 1) {
                    matrix[row][col] = rowBorder;
                } else {
                    matrix[row][col] = empty;
                }

                if (col == 0 || col == matrix.length - 1) {
                    matrix[row][col] = colBorder;
                }
            }
        }
        return matrix;
    }

    static boolean isOutOfBounds(char[][] matrix, int position) {
        return (position == 0 || position == matrix.length - 1);
    }
}
