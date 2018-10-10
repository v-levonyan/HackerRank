package problems;

import java.io.IOException;
import java.util.Scanner;

public class smallestNumber {

//    private static int smallestNum(int[] arr, int low, int high, int m) {
//
//    }

    private static int findmax(int[] aux) {
        int max = aux[0];
        for (int i = 1; i < aux.length; i++) {
            if (aux[i] > max) {
                max = aux[i];
            }
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

//        System.out.println(smallestNum(arr, 0, arr.length - 1, 4));
        scanner.close();
    }
}
