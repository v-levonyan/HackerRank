package problems;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixZero {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nm = scanner.nextLine().split(" ");

        int rows = Integer.parseInt(nm[0]);
        int columns = Integer.parseInt(nm[1]);

        int[][] matrix = new int[rows][columns];

        for (int[] row : matrix) {
            String[] rowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < columns; i++) {
                row[i] = Integer.parseInt(rowItems[i]);
            }
        }

        changeMatrix(matrix, rows, columns);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }


    }

    private static void changeMatrix(int[][] matrix, int rows, int columns) {
        boolean[] checkCol = new boolean[columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    if (!checkCol[j]) {
                        setZero(matrix, i, j, rows, columns);
                        checkCol[j] = true;
                    }
                    break;
                }
            }
        }


    }

    private static void setZero(int[][] matrix, int row, int col, int rows, int columns) {

        for (int i = 0; i < columns; i++) {
            matrix[row][i] = 0;
        }

        for (int i = 0; i < rows; i++) {
            matrix[i][col] = 0;
        }
    }
}
