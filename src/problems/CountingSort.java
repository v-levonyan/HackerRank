package problems;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountingSort {

    // Complete the countingSort function below.
    static int[] countingSort(int[] arr) {

        int[] result = new int[100];
        for (int i = 0; i < arr.length; i++) {
            result[arr[i]]++;

        }

        return result;

    }

    static int[] countingSort1(int[] arr) {

        int[] counts = countingSort(arr);

        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            int k = i;
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > 0) {
                    int value = j;
                    int count = counts[j];
                    while (count > 0) {
                        result[k++] = value;
                        count--;
                    }
                }
            }
            i = k;
        }

        return result;

    }

    static int[] countingSort2(int[] arr) {

        int[] counts = countingSort(arr);

        int[] result = new int[arr.length];

        int j = 0;
        for (int i = 0; i < counts.length; i++) {
            int value = i;
            int count = counts[i];
            while (count > 0) {
                result[j++] = value;
                count--;
            }
        }

        return result;

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

        int[] result = countingSort2(arr);

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
