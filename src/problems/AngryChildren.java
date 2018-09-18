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

    static long angryChildren1(int k, int[] packets) {


        Arrays.sort(packets);
        long sum = unfairnessSum(k, packets,0);
        long minSum = sum;
        for (int i = 1; i <= packets.length - k; i++) {
            sum = computeSum(packets, sum, i, i + k - 1);
            if (minSum > sum) {
                minSum = sum;
            }
        }
        return minSum;
    }

    private static long computeSum(int[] packets, long sum, int low, int high) {
        long head = 0;
        for (int j = high - 1; j >= low; j--) {
            head +=  packets[high] - packets[j];
        }
        long tail = 0;
        for (int i = high - 1; i >= low; i--) {
            tail += packets[i] - packets[low - 1];
        }
        long result = sum - tail + head;
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

    static long angryChildren2(int k, int[] packets) {


        Arrays.sort(packets);
        long sum = unfairnessSum(k, packets,0);
        long minSum = sum;
        Long subSum = Long.valueOf(-1);
        for (int i = 1; i <= packets.length - k; i++) {
            sum = computeSum2(packets, sum, subSum, i, i + k - 1);
            if (minSum > sum) {
                minSum = sum;
            }
        }
        return minSum;
    }


    private static long computeSum2(int[] packets, long sum, Long subSum, int low, int high) {
        int k = high - low + 1;

        if (subSum.longValue() < 0) {
            for (int j = high - 1; j >= low; j--) {
//                subSum += packets[j];
                subSum = subSum.longValue() + packets[j];
            }
        } else {
            subSum = subSum - packets[low - 1] + packets[high - 1];
        }

        long head = k * packets[high] - subSum;

        long tail = subSum - k * packets[low - 1];



        long result = sum - tail + head;
        return result;
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


    private static int sum(int[] packets, int low, int high) {
        int sum = 0;
        for (int j = low; j <= high ; j++) {
            sum += packets[j];
        }
        return sum;
    }
}
