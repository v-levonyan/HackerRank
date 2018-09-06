package problems;

import java.io.IOException;
import java.util.Scanner;

public class QuickSortLomuto {
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

        quickSort(arr, 0, arr.length - 1);
        scanner.close();
    }

    private static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }


    static void printArray(int[] ar) {
        for (int n : ar) {
            System.out.print(n + " ");
        }

        System.out.println();
    }

    private static int partition(int[] arr, int lowIndex, int highIndex) {
        int pivot = arr[highIndex];

        int low = lowIndex;
        for (int i = lowIndex; i <= highIndex; i++) {
            if (arr[i] < pivot) {
                int temp = arr[low];
                arr[low] = arr[i];
                arr[i] = temp;
                low++;
            }
        }

        int temp = arr[low];
        arr[low] = pivot;
        arr[highIndex] = temp;

        return low;
    }
}
