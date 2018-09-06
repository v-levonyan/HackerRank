package problems;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the insertionSort1 function below.
    static void insertionSort1(int n, int[] arr) {

        int lastElem = arr[n - 1];

        for(int i = n-2; i >= 0; --i) {
            if(arr[i] > lastElem) {
                arr[i+1] = arr[i];
                if (i == 0) {
                    arr[i] = lastElem;
                }
            } else if (arr[i] < lastElem) {
                arr[i+1] = lastElem;
                break;
            }

        }
    }

    static void insertionSort2(int n, int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            insertionSort1(i + 1, arr);
            printArray(arr);
        }
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        insertionSort2(n, arr);

        scanner.close();
    }
}
