public class Task8 {
    public static void main(String[] args) {
        int[][] matrix = {
                {11, 12, 13, 14, 15, 16},
                {21, 22, 23, 24, 25, 26},
                {31, 32, 33, 180, 35, 36,},
                {41, 42, 43, 44, 45, 46,},
                {51, 52, 53, 54, 55, 56,},
                {61, 62, 63, 64, 65, 66}
        };

        //как да сметна подматрица 2х2?
        int result = Integer.MIN_VALUE;

        for (int row = 0; row < matrix.length -1; row++) {
            for (int col = 0; col < matrix[row].length-1; col++) {
                int tempSum = 0;
                tempSum = matrix[row][col] + matrix[row][col+1] + matrix[row+1][col] + matrix [row+1][col+1];
                System.out.println(tempSum);
                if(result< tempSum){
                    result = tempSum;
                }
            }
        }
        System.out.println(result);

    }
}
