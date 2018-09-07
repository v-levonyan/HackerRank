package problems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class AngryChildren {

    // Complete the angryChildren function below.
    static long angryChildren(int k, int[] packets) {


        Arrays.sort(packets);
        long sum = 0;
        long minSum = Long.MAX_VALUE;
        for (int i = 0; i <= packets.length - k; i++) {
            sum = unfairnessSum(k, packets,i);
            if (minSum > sum) {
                minSum = sum;
            }
        }
        return minSum;
    }

    static long angryChildren2(int k, int[] packets) {


        Arrays.sort(packets);
        long sum = 0;
        long minSum = unfairnessSum(k, packets,0);
        for (int i = 1; i <= packets.length - k; i++) {
            sum = computeSum(packets, minSum, i, i + k - 1);
            if (minSum > sum) {
                minSum = sum;
            }
        }
        return minSum;
    }

    private static long computeSum(int[] packets, long minSum, int low, int high) {
        long head = 0;
        for (int j = high - 1; j >= low; j--) {
            head +=  packets[high] - packets[j];
        }
        long tail = 0;
        for (int i = high - 1; i >= low + 1 ; i--) {
            tail += packets[i] - packets[low - 1];
        }
        long result = minSum - tail + head;
        return result;
    }

    private static long unfairnessSum(int k , int[] packets, int low) {
        long sum = 0;
        int high = low + k - 1;
        if (high >= packets.length) {
            high = packets.length - 1;
            low = high - k + 1;
        }
        for (int i = high; i >= low; i--) {
            for (int j = low; j < i; j++) {
                sum += packets[i] - packets[j];
            }
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] packets = new int[n];

        for (int i = 0; i < n; i++) {
            int packetsItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            packets[i] = packetsItem;
        }

        long result = angryChildren2(k, packets);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
