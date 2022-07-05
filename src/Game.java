import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        //да оправя случая когато камъка е до храст и e в края на полето
        //да оправя случая когато храстите запушват изхода
        //fix border method
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select difficult from 1 to 3");
        int difficult;
        int matrixLength = 0;
        int difficultPercent = 0;

        do {
            difficult = sc.nextInt();
            if (difficult > 3 || difficult <= 0) {
                System.out.println("Please select difficult from 1 to 3");
            }

            switch (difficult) {
                case 1:
                    matrixLength = 10;
                    //12.5 percent
                    difficultPercent = 8;
                    break;
                case 2:
                    matrixLength = 20;
                    //20 percent
                    difficultPercent = 5;
                    break;
                case 3:
                    matrixLength = 30;
                    //33.33 percent
                    difficultPercent = 3;
                    break;
            }
        }
        while (difficult > 3 || difficult <= 0);

        char[][] matrix = new char[matrixLength][matrixLength];

        int peopleRow = random.nextInt(matrixLength);
        int peopleCol = random.nextInt(matrixLength);
        char exit = 'E';
        char people = 'P';
        char rock = 'R';
        char bush = '#';
        boolean gameOver = false;
        boolean success = false;
        matrix[matrix.length - 1][matrix.length - 1] = exit;

        //initialized people
        while (true) {
            boolean isPeopleInTheEnd = peopleRow == matrixLength - 1 && peopleCol == matrixLength - 1;
            if (!isPeopleInTheEnd) {
                matrix[peopleRow][peopleCol] = people;
                break;
            }
        }

        //initialized bush
        int count = (matrixLength * matrixLength) / difficultPercent;
        while (count > 0) {
            int bushRow = random.nextInt(matrixLength);
            int bushCol = random.nextInt(matrixLength);
            boolean isBushInTheEnd = bushRow == matrixLength - 1 && bushCol == matrixLength - 1;
            if (matrix[bushRow][bushCol] != people && !isBushInTheEnd) {
                matrix[bushRow][bushCol] = bush;
                count--;
            }
        }

        //initialized rock
        while (true) {
            int rockRow = random.nextInt(matrixLength);
            int rockCol = random.nextInt(matrixLength);
            boolean isRockInTheEnd = rockRow == matrixLength - 1 && rockCol == matrixLength - 1;
            boolean isRockOnBorder = rockRow + 1 < matrixLength  && rockRow - 1 > -1 &&
                    rockCol + 1 < matrixLength  && rockCol - 1 > -1;

            if (matrix[rockRow][rockCol] != people && matrix[rockRow][rockCol] != bush &&
                    !isRockInTheEnd && isRockOnBorder) {
                boolean leftBush = matrix[rockRow][rockCol - 1] == bush;
                boolean rightBush = matrix[rockRow][rockCol + 1] == bush;
                boolean upBush = matrix[rockRow - 1][rockCol] == bush;
                boolean downBush = matrix[rockRow + 1][rockCol] == bush;
                boolean canNotMove = (leftBush && upBush) || (leftBush && downBush) ||
                        (rightBush && upBush) || (rightBush && downBush);
                if (!canNotMove) {
                    matrix[rockRow][rockCol] = rock;
                    break;
                }
            }
        }

        System.out.println("You can move with w = forward, a = left, s = backward and d = right");
        //print matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        while (!gameOver || !success) {

            char directions = sc.next().charAt(0);

            switch (directions) {
                //up
                case 'w':
                    //game over
                    if (peopleRow - 1 < 0) {
                        System.out.println("Game over");
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow - 1][peopleCol] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow - 1][peopleCol] == rock) {
                        int rockRow = peopleRow - 1;
                        int rockCol = peopleCol;
                        //game over
                        if (rockRow - 1 < 0) {
                            System.out.println("Game over");
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow - 1][rockCol] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = 0;
                        matrix[--peopleRow][peopleCol] = people;
                        matrix[--rockRow][rockCol] = rock;
                        break;
                    }

                    matrix[peopleRow][peopleCol] = 0;
                    matrix[--peopleRow][peopleCol] = people;
                    break;
                //left
                case 'a':
                    //game over
                    if (peopleCol - 1 < 0) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow][peopleCol - 1] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow][peopleCol - 1] == rock) {
                        int rockRow = peopleRow;
                        int rockCol = peopleCol - 1;
                        //game over
                        if (rockCol - 1 < 0) {
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow][rockCol - 1] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = 0;
                        matrix[peopleRow][--peopleCol] = people;
                        matrix[rockRow][--rockCol] = rock;
                        break;

                    }

                    matrix[peopleRow][peopleCol] = 0;
                    matrix[peopleRow][--peopleCol] = people;
                    break;
                //down
                case 's':
                    //game over
                    if (peopleRow + 1 > matrix.length - 1) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow + 1][peopleCol] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow + 1][peopleCol] == rock) {
                        int rockRow = peopleRow + 1;
                        int rockCol = peopleCol;
                        //game over
                        if (rockRow + 1 > matrix.length - 1) {
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow + 1][rockCol] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = 0;
                        matrix[++peopleRow][peopleCol] = people;
                        matrix[++rockRow][rockCol] = rock;
                        break;

                    }

                    matrix[peopleRow][peopleCol] = 0;
                    matrix[++peopleRow][peopleCol] = people;
                    break;
                //right
                case 'd':
                    //game over
                    if (peopleCol + 1 > matrix.length - 1) {
                        gameOver = true;
                        break;
                    }
                    //bush logic
                    if (matrix[peopleRow][peopleCol + 1] == bush) {
                        break;
                    }
                    //rock logic
                    if (matrix[peopleRow][peopleCol + 1] == rock) {
                        int rockRow = peopleRow;
                        int rockCol = peopleCol + 1;
                        //game over
                        if (rockCol + 1 > matrix.length - 1) {
                            gameOver = true;
                            break;
                        }
                        //bush logic
                        if (matrix[rockRow][rockCol + 1] == bush) {
                            break;
                        }
                        matrix[peopleRow][peopleCol] = 0;
                        matrix[peopleRow][++peopleCol] = people;
                        matrix[rockRow][++rockCol] = rock;
                        break;

                    }
                    matrix[peopleRow][peopleCol] = 0;
                    matrix[peopleRow][++peopleCol] = people;
                    break;

            }

            //success
            if (matrix[matrix.length - 1][matrix.length - 1] == rock) {
                success = true;
            }

            if (gameOver) {
                matrix[peopleRow][peopleCol] = 'D';
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(matrix[i][j] + " ");
                    boolean isRockOnBorder = i + 1 < matrixLength  && i - 1 > -1 &&
                            j + 1 < matrixLength && j - 1 > -1;

                    //bush game over logic
                    if (isRockOnBorder && matrix[i][j] == rock) {
                        boolean leftBush = matrix[i][j - 1] == bush;
                        boolean rightBush = matrix[i][j + 1] == bush;
                        boolean upBush = matrix[i - 1][j] == bush;
                        boolean downBush = matrix[i + 1][j] == bush;
                        if ((leftBush && upBush) || (leftBush && downBush) ||
                                (rightBush && upBush) || (rightBush && downBush)) {
                            gameOver = true;
                        }
                    }
                }
                System.out.println();
            }

            if (gameOver) {
                System.out.println("Game over :(");
            }

            if (success) {
                System.out.println("Congrats you win the game");
            }
        }
    }
}
