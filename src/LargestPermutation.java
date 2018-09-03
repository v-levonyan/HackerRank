import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class LargestPermutation {

    // Complete the largestPermutation function below.
    static int[] largestPermutation(int k, int[] arr) {

        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length && k > 0; i++) {
            k = swapWithMax(i, k, arr);
        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("Execution time: " + duration);
        return arr;

    }

    private static int[] largestPermutation2(int k, int[] index, int[] arr) {

        int[] copyArray = Arrays.copyOf(arr, arr.length);
        int j = index.length - 1;
        int maxValue;
        for (int i = 0; i < arr.length && k > 0; i++) {
            maxValue = copyArray[index[j--]];
            k = swapWithMaxIndex(i, k, arr, maxValue);
        }

        return arr;

    }

    private static int swapWithMax(int i, int swapCount, int[] arr) {
        int max = arr[i];
        int maxIndex = i;
        for (int j = i; j < arr.length; j++) {
            if (arr[j] > max) {
                max = arr[j];
                maxIndex = j;
            }
        }

        if (maxIndex == i) {
            return swapCount;
        }

        int temp = arr[i];
        arr[i] = max;
        arr[maxIndex] = temp;
        return --swapCount;
    }

    private static int swapWithMaxIndex(int i, int swapCount, int[] arr, int maxValue) {

        int maxIndex = i;
        for (int j = i; j < arr.length; j++) {
            if (arr[j] == maxValue) {
                maxIndex = j;
            }
        }

        if (maxIndex == i) {
            return swapCount;
        }

        int temp = arr[i];
        arr[i] = maxValue;
        arr[maxIndex] = temp;
        return --swapCount;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/vahanl/IdeaProjects/HackerRank/output"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];
        int[] index = new int[n + 1];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
            index[arr[i]] = i;
        }

        printArray(index);

        int[] result = largestPermutation2(k, index, arr);

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



    private static void swapindexes(int[] arr, int i, int index) {
        int tmp = arr[i];
        arr[i] = arr[index];
        arr[index] = tmp;
    }

    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}
