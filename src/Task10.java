import java.util.Arrays;
import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();


        int[][] table = new int[n][m];
        int row = 0;
        int col = 0;
        int sumOfAllIndex = n*m;
        int count = 0;
        while (sumOfAllIndex > 0){
            count++;
            if (col %2 ==0){
                table[row][col] = count;
                row++;
            }
            else {
                table[row][col] = count;
                row--;
            }

            if (row < 0){
                col++;
                row++;
            }

            if(row == table.length){
                col++;
                row--;
            }

            sumOfAllIndex--;
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
