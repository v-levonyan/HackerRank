package problems;

import java.io.*;
import java.util.*;

public class PriyankaAndToys {

    // Complete the toys function below.
    static int toys(int[] w) {

        QuickSortLomuto.quickSort(w);

        int containerCount = 1;

        int minWeightItemIndex = 0;
        for (int i = 1; i < w.length; i++) {
            if (w[i] - w[minWeightItemIndex] > 4) {
                containerCount++;
                minWeightItemIndex = i;
            }
        }

        return containerCount;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/vahanl/IdeaProjects/HackerRank/output"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] w = new int[n];

        String[] wItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int wItem = Integer.parseInt(wItems[i]);
            w[i] = wItem;
        }

        int result = toys(w);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
