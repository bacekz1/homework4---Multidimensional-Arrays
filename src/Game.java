import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        char[][] matrix = new char[10][10];
        matrix[matrix.length - 1][matrix.length - 1] = 'E';

        int peopleRow = random.nextInt(10);
        int peopleCol = random.nextInt(10);
        char people = 'A';
        char rock = 'R';
        boolean gameOver = false;
        boolean success = false;
        //initialized people
        while (true) {
            boolean isPeopleInTheEnd = peopleRow == matrix.length - 1 && peopleCol == matrix.length - 1;
            if (!isPeopleInTheEnd) {
                matrix[peopleRow][peopleCol] = people;
                break;
            }
        }
        //initialized rock
        while (true) {
            int rockRow = random.nextInt(10);
            int rockCol = random.nextInt(10);
            boolean isRocketInTheEnd = rockRow == matrix.length - 1 && rockCol == matrix.length - 1;
            if ((matrix[rockRow][rockCol] != people) && (!isRocketInTheEnd)) {
                matrix[rockRow][rockCol] = rock;
                break;
            }
        }

        //initialized bush
        int count = 20;
        char bush = '#';
        while (count > 0) {
            int bushRow = random.nextInt(10);
            int bushCol = random.nextInt(10);
            boolean isBushInTheEnd = bushRow == matrix.length - 1 && bushCol == matrix.length - 1;

            if ((matrix[bushRow][bushCol] != people) && (matrix[bushRow][bushCol] != rock) && (!isBushInTheEnd)) {
                count--;
                matrix[bushRow][bushCol] = bush;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        while (!gameOver || !success) {
            char direction = sc.next().charAt(0);

            switch (direction) {
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
                    //rocket logic
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
                    //rocket logic
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
                    //rocket logic
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
                    //rocket logic
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

                    //bush game over logic
                    if (matrix[i][j] == rock) {
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
