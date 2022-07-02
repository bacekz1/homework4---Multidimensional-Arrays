import java.util.Arrays;

public class Task12 {
    public static void main(String[] args) {
        int[][] table = new int[8][8];
        int count = 1;

        for (int row = 0; row < table.length; row++) {
            int tempCount = count;
            for (int col = 0; col < table[row].length; col++) {
                table[row][col] = tempCount;
                tempCount *= 2;
            }
            count *= 2;
        }
        
        for (int i = 0; i < table.length; i++) {
            System.out.println(Arrays.toString(table[i]));
        }


        int currentRow = 1;
        int currentCol = 1;

        // 0 = down right 1 = up right 2 = up left 3 = down left
        int direction = 0;



        System.out.print(table[0][0] + " ");
        while (true) {
            System.out.print(table[currentRow][currentCol] + " ");
            //up left corner
            if (currentRow == 0 && currentCol == 0) {
                break;
            }
            //down left corner
            if (currentRow == 0 && currentCol == table[0].length - 1) {
                break;
            }
            // up right corner
            if (currentRow == table.length - 1 && currentCol == 0) {
                break;
            }
            //down right corner
            if (currentRow == table.length - 1 && currentCol == table[0].length - 1) {
                break;
            }

            switch (direction) {
                //down right direction row++ col++
                case 0:
                    if (currentRow + 1 < table.length && currentCol + 1 < table[0].length) {
                        currentRow++;
                        currentCol++;
                        continue;
                    } else {
                        direction = 1;
                    }
                    //up right direction row-- col++
                case 1:
                    if (currentRow - 1 > -1 && currentCol + 1 < table[0].length) {
                        currentRow--;
                        currentCol++;
                        continue;
                    } else {
                        direction = 2;
                    }
                    //up left direction row-- col--
                case 2:
                    if (currentRow - 1 > -1 && currentCol - 1 > -1) {
                        currentRow--;
                        currentCol--;
                        continue;
                    } else {
                        direction = 3;
                    }
                    //down left direction row++ col--
                case 3:
                    if (currentRow + 1 < table.length && currentCol - 1 > -1) {
                        currentRow++;
                        currentCol--;
                    } else {
                        direction = 0;
                    }
            }
        }
    }
}
