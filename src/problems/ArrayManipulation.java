package problems;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArrayManipulation {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {

        long[] result = new long[n + 1];
        long maxValue = 0;
        for (int[] arr : queries) {
            int low = arr[0];
            int high = arr[1];
            int value = arr[2];

            for (int i = low; i <= high; i++) {
                result[i] += value;
                if (maxValue < result[i]) {
                    maxValue = result[i];
                }
            }
        }
        return maxValue;
    }

    static class Range implements Comparable<Range> {
        int low;
        int high;

        public Range(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        public int compareTo(Range range) {
            return this.low - range.low;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Range)) {
                return false;
            }
            Range c = (Range) o;
            return low == c.low && high == c.high;
        }
    }

    static long arrayManipulation1(int n, int[][] queries) {

        TreeMap<Range, Long> rangeValues = new TreeMap<>();

        for (int[] arr : queries) {
            Range range = new Range(arr[0], arr[1]);
            rangeValues.put(range, rangeValues.getOrDefault(range, 0L) + (long) arr[2]);
        }


        TreeMap<Range, Long> splittedRanges = new TreeMap<>();
        Map.Entry<Range, Long> previous = null;
        Iterator<Map.Entry<Range, Long>> it = rangeValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Range, Long> entry = it.next();
            if (previous != null) {
                if (previous.equals(rangeValues.firstEntry())) {
                    TreeMap<Range, Long> splittedRange = splitRanges(previous, entry);
                    splittedRanges.putAll(splittedRange);
                } else {
                    previous = splittedRanges.lowerEntry(entry.getKey());
                    TreeMap<Range, Long> splittedRange = splitRanges(previous, entry);
                    splittedRanges.putAll(splittedRange);
                }
            }

            previous = entry;
        }
        rangeValues.putAll(splittedRanges);


        return rangeValues.values().stream().max(Long::compare).get();
    }

    private static TreeMap<Range, Long> splitRanges(
            Map.Entry<Range, Long> preventry,
            Map.Entry<Range, Long> currententry) {

        TreeMap<Range, Long> result = new TreeMap<>();

        Range prevRange = preventry.getKey();
        Long prevVal = preventry.getValue();
        Range currentRange = currententry.getKey();
        Long currentVal = currententry.getValue();

        if (currentRange.low <= prevRange.high) {
            if (currentRange.high >= prevRange.high) {
                result.put(new Range(prevRange.low, currentRange.low - 1), prevVal);
                result.put(new Range(currentRange.low, prevRange.high), prevVal + currentVal);
                result.put(new Range(prevRange.high + 1, currentRange.high), currentVal);
            } else if (currentRange.high < prevRange.high) {
                result.put(new Range(prevRange.low, currentRange.low - 1), prevVal);
                result.put(new Range(currentRange.low, currentRange.high), prevVal + currentVal);
                result.put(new Range(currentRange.high + 1, prevRange.high), prevVal);
            }
        } else {
            result.put(new Range(currentRange.low, currentRange.high), currentVal);
        }

        return result;
    }

    private static void intersections() {
        //        Iterator entries = rangeValues.entrySet().iterator();
//        while (entries.hasNext()) {
//            Map.Entry entry = (Map.Entry) entries.next();
//            Integer key = (Integer)entry.getKey();
//            Integer value = (Integer)entry.getValue();
//            System.out.println("Key = " + key + ", Value = " + value);
//        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation1(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
