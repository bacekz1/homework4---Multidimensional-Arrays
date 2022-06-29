public class Task1 {
    public static void main(String[] args) {

        int[][] table = {
                {48, 72, 13, 14, 15},
                {21, 22, 53, 24, 75},
                {31, 57, 33, 34, 35},
                {41, 95, 43, 44, 45},
                {59, 52, 53, 54, 55},
                {61, 69, 63, 64, 65}
        };

        int minValue = table[0][0];
        int maxValue = table[0][0];

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col] < minValue) {
                    minValue = table[row][col];
                }
                if (table[row][col] > maxValue) {
                    maxValue = table[row][col];
                }
            }
        }
        System.out.println("smallest " + minValue);
        System.out.println("biggest " + maxValue);

    }
}
