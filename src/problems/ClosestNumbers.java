package problems;

import java.io.*;
import java.util.*;

public class ClosestNumbers {


    // Complete the closestNumbers function below.
    static Integer[] closestNumbers(int[] arr) {

        QuickSortLomuto.quickSort(arr);

        QuickSortLomuto.printArray(arr);

        int diff;
        int minDiff = arr[1] - arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            diff = arr[i+1] - arr[i];
            if (minDiff > diff) {
                minDiff = diff;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            diff = arr[i+1] - arr[i];
            if (diff == minDiff) {
                result.add(arr[i]);
                result.add(arr[i+1]);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/vahanl/IdeaProjects/HackerRank/output"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        Integer[] result = closestNumbers(arr);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
