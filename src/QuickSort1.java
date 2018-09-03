import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class QuickSort1 {

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

        partitionRecursive(arr);
        scanner.close();
    }

    static void partitionRecursive(int[] arr) {

        if (arr.length < 2) {
            return;
        }
        int pivot = arr[0];

        int[] left = new int[arr.length - 1];
        int[] right = new int[arr.length - 1];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < pivot) {
                left[leftIndex++] = arr[i];
            } else {
                right[rightIndex++] = arr[i];
            }
        }

        left = Arrays.copyOf(left, leftIndex);
        right = Arrays.copyOf(right, rightIndex);

        partitionRecursive(left);
        partitionRecursive(right);

        int index = 0;

        for (int i = 0; i < left.length; i++) {
            arr[index++] = left[i];
            System.out.print("Left: " + left[i] + " ");
        }

        arr[index++] = pivot;
        System.out.print("pivot: " + pivot + " ");

        for (int i = 0; i < right.length; i++) {
            arr[index++] = right[i];
            System.out.print("right: " + right[i] + " ");
        }

        System.out.println();
    }
}
