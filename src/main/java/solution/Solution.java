package solution;

import java.io.*;
import java.util.*;

public class Solution {

    private static final Map<String, Integer> charCounts = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String gene = sc.next();
        int shortestLength = findShortestLength(gene, count);

//        System.out.println(shortestLength);

    }

    private static int findShortestLength(String gene, int count) {

        List<Range> ranges = splitIntoRanges(gene);
//        System.out.println(ranges);
//        System.out.println(ranges.size());

        printMap();

        Map<String, Integer> heavyChars = disBalancedChars(charCounts, count);

        int shortestLength = findMinLength(heavyChars, ranges);

        return shortestLength;

    }


    private static int findMinLength(Map<String, Integer> heavyChars, List<Range> ranges) {
        List<Integer> result = new ArrayList<>();

        RangeStack rangeStack = new RangeStack();
        rangeStack.setHeavyChars(heavyChars);

        for (Range range : ranges) {
            if (range.isFromHeavyChars(heavyChars)) {
                rangeStack.addToStack(range);
                if (rangeStack.isCompleteForChar(range.getIdentifier(), heavyChars)) {
                    rangeStack.removeFromHeavyChars(range.getIdentifier());
                }
                if (rangeStack.isComplete()) {
                    rangeStack.cutEdges(heavyChars);
                    System.out.println(rangeStack);
//                    System.out.println(rangeStack.length());
                    result.add(rangeStack.length());
                    rangeStack = new RangeStack();
                    rangeStack.setHeavyChars(heavyChars);
                }
            }
        }


        return Collections.min(result);
    }

    private static class RangeStack {
        List<Range> ranges;

        public RangeStack() {
            ranges = new ArrayList<>();
        }

        public void setHeavyChars(Map<String, Integer> heavyChars) {
            this.heavyChars = new HashMap(heavyChars);
        }

        Map<String, Integer> heavyChars;
        public void addToStack(Range range) {
            ranges.add(range);
        }

        @Override
        public String toString() {
            return "RangeStack{" +
                    "ranges=" + ranges +
                    '}';
        }

        private int countForChar(char ch) {
            int count = 0;
            for (Range range : ranges) {
                if (range.getIdentifier() == ch) {
                    count += range.length();
                }
            }
            return count;
        }

        public boolean isCompleteForChar(char identifier, Map<String, Integer> heavyChars) {
            return countForChar(identifier) >= heavyChars.get(String.valueOf(identifier));
        }


        public void removeFromHeavyChars(char ch) {
            heavyChars.remove(String.valueOf(ch));
        }

        public boolean isComplete() {
            return heavyChars.isEmpty();
        }

        public int length() {
            Range first = ranges.get(0);
            Range last = ranges.get(ranges.size() - 1);
            return last.end - first.start;
        }

        public void cutEdges(Map<String, Integer> heavyChars) {
            Range startRange = ranges.get(0);
            Range endRange = ranges.get(ranges.size() - 1);
            String startKey = String.valueOf(startRange.getIdentifier());
            String endKey = String.valueOf(endRange.getIdentifier());
            if (startKey.equals(endKey)) {
                int toRemove = heavyChars.get(startKey);
                int sum = startRange.length() + endRange.length();
                int diff = sum - toRemove;
                if (diff > 0) {
                    if (startRange.length() > diff) {
                        startRange.start += diff;
                    } else {
                        endRange.end -= diff;
                    }
                }
            } else {
                int toRemoveStart = heavyChars.get(startKey);
                int toRemoveEnd = heavyChars.get(endKey);

                if (startRange.length() > toRemoveStart) {
                    startRange.start += toRemoveStart;
                }
                if (endRange.length() > toRemoveEnd) {
                    endRange.end -= toRemoveEnd;
                }
            }
        }
    }

    private static class Range {
        public int start;
        public int end;

        public char getIdentifier() {
            return identifier;
        }

        private char identifier;

        public Range(int start, int end, char identifier) {
            this.start = start;
            this.end = end;
            this.identifier = identifier;
        }

        public int length() {
            return (end - start);
        }

        public String toString() {
            return this.identifier + "(" + this.start + ", " + this.end + ")";
        }

        public boolean isFromHeavyChars(Map<String, Integer> heavyChars) {
            Iterator it = heavyChars.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String key = (String) pair.getKey();
                if (this.identifier == key.charAt(0)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static List<Range> splitIntoRanges(String s) {

        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < s.length(); ++i) {
            int start = 0;
            char ch = s.charAt(i);
            start = i;
            while (i < s.length() && s.charAt(i) == ch) {
                i++;
            }

            Range range = new Range(start, i, ch);
            ranges.add(range);
            String key = String.valueOf(ch);
            if (charCounts.containsKey(key)) {
                charCounts.put(key, charCounts.get(key) + range.length());
            } else {
                charCounts.put(key, range.length());
            }
            i--;
        }
        return ranges;
    }

    private static void printMap() {
        Iterator it = charCounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
    }
    private static Map<String, Integer> disBalancedChars(Map<String, Integer> charCounts, int totalCount) {
        Map<String, Integer> heavyChars = new HashMap<>();
        Iterator it = charCounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int count = (int) pair.getValue();
            if (count > totalCount / 4) {
                heavyChars.put((String) pair.getKey(), count - totalCount / 4);
//                System.out.println(pair.getKey() + ": " + (count - totalCount / 4));
            }
            it.remove();
        }
        return heavyChars;
    }
}
