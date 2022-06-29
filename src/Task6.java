public class Task6 {
    public static void main(String[] args) {
        int[][] matrix = {
                {11, 12, 13, 14, 15, 16},
                {21, 22, 23, 24, 25, 26},
                {31, 32, 33, 34, 35, 36,},
                {41, 42, 43, 44, 45, 46,},
                {51, 52, 53, 54, 55, 56,},
                {61, 62, 63, 64, 65, 66}
        };

        int sum = 0;
        for (int row = 1; row < matrix.length; row += 2) {
            int tempSum = 0;
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + ",");
                tempSum += matrix[row][col];
            }
            sum += tempSum;
            System.out.print(" tempSum " + tempSum);
            System.out.println();

        }
        System.out.println("Sum of all elements " + sum);
    }
}
