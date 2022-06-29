public class Task5 {

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        int sumRow =Integer.MIN_VALUE;
        int sumCol =Integer.MIN_VALUE;

        for (int row = 0; row < matrix.length; row++) {
            int tempSumRow = 0;
            int tempSumCol = 0;
            for (int col = 0; col < matrix[row].length; col++) {
                tempSumRow += matrix[row][col];
                tempSumCol += matrix[col][row];

                if (tempSumRow > sumRow){
                    sumRow = tempSumRow;
                }
                if(tempSumCol > sumCol){
                    sumCol =tempSumCol;
                }
            }
        }

        System.out.println("biggest sum by row is " + sumRow);
        System.out.println("biggest sum by column is " + sumCol);
        System.out.println(sumRow > sumCol ? "Max sum by row is bigger than max sum by col" :
                "Max sum by col is bigger than max sum by col" );


    }
}
