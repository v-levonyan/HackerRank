package problems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixRotation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] rowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < n; j++) {
                int queriesItem = Integer.parseInt(rowItems[j]);
                matrix[i][j] = queriesItem;
            }
        }

        int[][] result = rotateMatrix(matrix, n);

        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

        scanner.close();
    }

    private static int[][] rotateMatrix(int[][] matrix, int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[n - 1 - j][i];
            }
        }
        return result;
    }

}
