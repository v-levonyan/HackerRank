package problems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaximumSubarray {

    static int[] maxSubarray(int[] arr) {
        long startTime = System.nanoTime();
        int [] result = {maximumSubarray(arr)};
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("Execution time: " + duration);
        return result;
    }


    private static int maximumSubSequence(int[] arr) {
        QuickSortLomuto.quickSort(arr);
        return maximumSubarray(arr);
    }


    private static int maximumSubarray(int[] arr) {
        return maximumSubarray(arr, 0, arr.length - 1);
    }

    private static int maximumSubarray(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }

        int max = sum(arr, low, high);

        int sum1 = maximumSubarray(arr, low + 1, high);

        if (sum1 > max) {
            max = sum1;
        }

        int sum2 = maximumSubarray(arr, low, high - 1);

        if (sum2 > max) {
            max = sum2;
        }

        return max;
    }

    private static int sum(int[] arr, int low, int high) {
        int sum = 0;
        for (int i = low; i <= high; i++) {
            sum += arr[i];
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/vahanl/IdeaProjects/HackerRank/output"));
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] result = maxSubarray(arr);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
