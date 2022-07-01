import java.util.Scanner;

public class Task9 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] table = new int[n][m];

        for (int row = 0; row < table.length; row++) {
            int count = row + 1;
            for (int col = 0; col < table[row].length; col++) {
                table[row][col] = count;
                count += m-1;
                System.out.print(table[row][col] + " ");
            }
            System.out.println();
        }
    }
}
