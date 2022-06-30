public class Task2 {

    public static void main(String[] args) {

        int[][] table = {
                {1, 4, 6, 3},
                {5, 9, 7, 2},
                {4, 8, 1, 9},
                {2, 3, 4, 5},
        };


        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (row == col) {
                    System.out.print(table[row][col]);
                }
            }
        }

        System.out.println();
        for (int row = 0; row < table.length; row++) {
            int rowLength = table[row].length - row;
            for (int col = 0; col < table[row].length; col++) {
                if (rowLength -1 == col) {
                    System.out.print(table[row][col]);
                }
            }
        }

    }
}
