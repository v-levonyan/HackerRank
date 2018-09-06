package dynamic_programming;

import problems.MergeSort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaxSubArray {

    private static int[] findMaxCrossingArray(int[] arr, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;
        for (int i = mid; i >= low ; i--) {
            sum += arr[i];
            if (leftSum < sum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            if (rightSum < sum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        int[] result = {maxLeft, maxRight, leftSum + rightSum};
        return result;
    }

    private static int[] maxSubarray(int[] arr, int low, int high) {
        if (low == high) {
            return new int[]{low, high, arr[low]};
        }
        int mid = (high + low)/2;

        int[] left = maxSubarray(arr, low, mid);
        int[] right = maxSubarray(arr, mid + 1, high);
        int[] cross = findMaxCrossingArray(arr, low, mid, high);

        if (left[2] >= right[2] &&  left[2] >= cross[2]) {
            return left;
        } else if (right[2] >= left[2] && right[2] >= cross[2]) {
            return right;
        } else return cross;
    }

    private static int[] maxSubarray(int[] arr) {
        long startTime = System.nanoTime();
        int[] result = maxSubarray(arr, 0, arr.length - 1);

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("Execution time: " + duration);
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
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

//            int maxSumSubArray = maxSubarray(arr)[2];
            int maxSumSubArray = maxSubarrayLinear(arr, true)[2];
//            int maxSumSubSequence = maxSubSequence(arr);
            int maxSumSubSequence = maxSubsequenceLinear(arr);
            int[] result = {maxSumSubArray, maxSumSubSequence};

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

    private static int maxSubSequence(int[] arr) {
        MergeSort.mergeSort(arr);
        return maxSubarray(arr)[2];
    }

    private static int[] maxSubarrayLinear(int[] arr) {
        int M = Integer.MIN_VALUE;
        int lowM = 0;
        int highM = 0;
        int Mr = 0;
        int lowR = 0;

        for (int i = 0; i < arr.length; i++) {
            Mr += arr[i];
            if (Mr > M) {
                lowM = lowR;
                highM = i;
                M = Mr;
            }
            if (Mr < 0) {
                Mr = 0;
                lowR = i + 1;
            }
        }
        return new int[]{lowM, highM, M};
    }

    private static int[] maxSubarrayLinear(int[] arr, boolean interval) {
        long startTime = System.nanoTime();

        int[] result = maxSubarrayLinear(arr);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Execution time: " + duration);

        return result;
    }

    private static int maxSubsequenceLinear(int[] arr) {
        int Mr = Integer.MIN_VALUE;
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (Mr < 0) {
                    Mr = 0;
                }
                Mr += arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        if (Mr < max) {
            Mr = max;
        }
        return Mr;
    }
}
