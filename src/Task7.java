public class Task7 {

    public static void main(String[] args) {

        int[][] matrix = {
                {11, 12, 13, 14, 15, 16},
                {21, 22, 23, 24, 25, 26},
                {31, 32, 33, 34, 35, 36,},
                {41, 42, 43, 44, 45, 46,},
                {51, 52, 53, 54, 55, 56,},
                {61, 62, 63, 64, 65, 66}
        };

        int col = 0;
        int row = 0;
        int rowSum = 0;
        int result = 0;
        while (row < matrix.length) {

            if (row % 2 != 0) {
                if (col == 0) {
                    col = 1;
                }
            }
            System.out.print(matrix[row][col] + ",  ");
            rowSum += matrix[row][col];
            result +=matrix[row][col];
            col += 2;

            if (col >= matrix.length) {
                System.out.print("sum of elements for row: " + rowSum);
                System.out.println();
                row++;
                rowSum = 0;
                col = 0;
            }
        }
        System.out.println("Sum of elements: " + result);
    }
}

