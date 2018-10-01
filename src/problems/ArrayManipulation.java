package problems;

import java.io.*;
import java.util.*;

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

    static void test() {
        TreeMap<Range, Long> rangeValues = new TreeMap<>();

        rangeValues.put(new Range(6, 10), 10L);
        rangeValues.put(new Range(6, 20), 15L);

        System.out.println(rangeValues.toString());
    }

    static class Range implements Comparable<Range> {
        int low;
        int high;

        public Range(int low, int high) {
            if (high < low) high = low;
            this.low = low;
            this.high = high;
        }

        public Range(int i) {
            this.low = i;
            this.high = i;
        }

        @Override
        public int compareTo(Range range) {
            if (this.low == range.low) {
                return this.high - range.high;
            }
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
                if (!previous.equals(rangeValues.firstEntry())) {
                    previous = splittedRanges.lowerEntry(entry.getKey());
                }
                splitRanges(previous, entry, splittedRanges);
            }

            previous = entry;
        }
//        rangeValues.putAll(splittedRanges);


        return splittedRanges.values().stream().max(Long::compare).get();
    }


    private static void splitRanges(
            Map.Entry<Range, Long> preventry,
            Map.Entry<Range, Long> currententry, TreeMap<Range, Long> rangeValues) {

        Range prevRange = preventry.getKey();
        Long prevVal = preventry.getValue();
        Range currentRange = currententry.getKey();
        Long currentVal = currententry.getValue();

        if (currentRange.low < prevRange.high) {
            if (currentRange.high >= prevRange.high) {
                rangeValues.remove(prevRange);
                if (currentRange.low != prevRange.low) {
                    rangeValues.put(new Range(prevRange.low, currentRange.low - 1), prevVal);
                }
                rangeValues.put(new Range(currentRange.low, prevRange.high), prevVal + currentVal);

//                rangeValues.put(new Range(prevRange.high + 1, currentRange.high), currentVal);

                //TODO: sum to next ranges!!!
                Range tailRange = new Range(prevRange.high, currentRange.high);
                Iterator<Map.Entry<Range, Long>> entries = rangeValues.tailMap(tailRange).entrySet().iterator();
                breakRange(tailRange, entries, rangeValues, currentVal);

            } else if (currentRange.high < prevRange.high) {
                rangeValues.remove(prevRange);
                rangeValues.put(new Range(prevRange.low, currentRange.low - 1), prevVal);
                rangeValues.put(new Range(currentRange.low, currentRange.high), prevVal + currentVal);
                rangeValues.put(new Range(currentRange.high + 1, prevRange.high), prevVal);
            }
        } else if (currentRange.low == prevRange.high) {
            rangeValues.remove(prevRange);
            rangeValues.put(new Range(currentRange.low), prevVal + currentVal);
//            rangeValues.put(new Range(currentRange.low + 1, currentRange.high), currentVal);
            Range tailRange = new Range(currentRange.low, currentRange.high);
            Iterator<Map.Entry<Range, Long>> entries = rangeValues.tailMap(tailRange).entrySet().iterator();
            breakRange(tailRange, entries, rangeValues, currentVal);
        } else if (currentRange.low > prevRange.high) {
            rangeValues.put(prevRange, prevVal);
            Range tailRange = new Range(currentRange.low, currentRange.high);
            Iterator<Map.Entry<Range, Long>> entries = rangeValues.tailMap(tailRange).entrySet().iterator();
            breakRange(tailRange, entries, rangeValues, currentVal);
        }


    }

    private static void breakRange(
            Range tailRange,
            Iterator<Map.Entry<Range, Long>> entries,
            TreeMap<Range, Long> result, Long currentVal) {

        int index = tailRange.low;
        while (entries.hasNext()) {
            Map.Entry<Range, Long> entry = entries.next();
            Range range = entry.getKey();

            if (index < range.low - 1) {
                Range key = new Range(index, range.low - 1);
                result.put(key, result.getOrDefault(key, 0l) + currentVal);
            }
            if (range.high > tailRange.high) {
                result.remove(range);
                result.put(new Range(range.low, tailRange.high), currentVal + entry.getValue());
                result.put(new Range(tailRange.high + 1, range.high), entry.getValue());
                return;
            } else {
                result.replace(new Range(range.low, range.high), currentVal + entry.getValue());
                index = range.high + 1;
            }
        }

        result.put(new Range(index, tailRange.high), currentVal);
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

        long result1 = arrayManipulation1(n, queries);
        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.append(String.valueOf(result1));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }


    private static TreeMap<Range, Long> splitRangesRecursive(
            Map.Entry<Range, Long> preventry,
            Map.Entry<Range, Long> currententry, TreeMap<Range, Long> rangeValues) {

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

//                splitRangesRecursive()

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
}
