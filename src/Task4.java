public class Task4 {
    public static void main(String[] args) {
        int[][] table = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}

        };

        int[][] result = new int[table.length][table[0].length];

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                result[col][table.length - 1 - row] = table[row][col];
            }
        }

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                System.out.print(result[row][col] + " ");
            }
            System.out.println();
        }
    }
}
