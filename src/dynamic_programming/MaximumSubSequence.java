package dynamic_programming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaximumSubSequence {

    static int[] maxSubarray(int[] arr) {
        int[] result = new int[2];
        result[0] = maximumSubarray(arr);
        result[1] = maximumSubSequence(arr);
        return result;
    }


    private static int maximumSubSequence(int[] arr) {

        return 0;
    }


    private static int maximumSubarray(int[] arr) {
        int[] sums = new int[arr.length];
        sums[0] = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sum = sums[i] + arr[j];
            }
            sums[i] = sum;
        }
        return 0;
    }

    private static int sum(int[] arr, int low, int high) {
        int sum = 0;
        for (int i = low; i <= high; i++) {
            sum += arr[i];
        }
        return sum;
    }

    private static int maxSum(int[] arr, int low, int high) {
        int sum = 0;
        for (int i = low; i <= high; i++) {
            if (sum < sum + arr[i]) {
                sum += arr[i];
            }
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

