public class Task11 {
    public static void main(String[] args) {
        int[][] matrix = new int[5][6];
        matrix[0][0] = 1;
        int stop = 16;
        int count = 1;
        int row = 0;
        int col = 0;

        boolean down = true;
        boolean right = false;
        boolean up = false;
        boolean left = false;

        for (int i = 0; i < matrix.length*2; i++) {

        }

        while (stop > 0) {
            count++;

            //down logic
            if (row == matrix.length -1 || matrix[row+1][col] !=0){
                down = false;
                right = true;
            }

            if (down && matrix[row+1][col] == 0){
                matrix[row+1][col] = count;
                row++;
                continue;
            }

            //right logic
            if (col == matrix[0].length -1 || matrix[row][col+1] != 0){
                right= false;
                up = true;
            }

            if (right && matrix[row][col+1] == 0 ){
                matrix[row][col+1] = count;
                col++;
                continue;
            }

            //up logic

            if (row< 1 || matrix[row-1][col] != 0 ){
                up =false;
                left = true;
            }

           if (up && matrix[row-1][col] == 0){
                matrix[row-1][col] = count;
                row--;
                continue;
            }

           // left logic

            if (matrix[row][col-1] != 0){
                left = false;
                down = true;
            }

            if (left && matrix[row][col-1] == 0){
                matrix[row][col-1] = count;
                col--;
                continue;
            }

            stop--;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
