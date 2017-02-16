package solution;

import java.util.*;

public class Solution {

    private static final Map<Character, Integer> charCounts = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String gene = sc.next();

        Map<Character, Integer> charCounts = getCharCounts(gene);
        Map<Character, Integer> heavyChars = disBalancedChars(charCounts, count);

        int shortestLength = findShortestLength(gene, heavyChars);

        System.out.println(shortestLength);

    }

    private static Map<Character, Integer> disBalancedChars(Map<Character, Integer> charCounts, int totalCount) {
        Map<Character, Integer> heavyChars = new HashMap<>();
        Iterator it = charCounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            int count = (int) pair.getValue();
            if (count > totalCount / 4) {
                heavyChars.put((Character) pair.getKey(), count - totalCount / 4);
//                System.out.println(pair.getKey() + ": " + (count - totalCount / 4));
            }
            it.remove();
        }
        return heavyChars;
    }

    private static boolean containsHeavyChars(String str, Map<Character, Integer> heavyChars) {
        Map<Character, Integer> copyHeavyChars = new HashMap(heavyChars);
        for (int i = 0; i < str.length(); ++i) {
            Character currentChar = str.charAt(i);
            if (copyHeavyChars.containsKey(currentChar)) {
                copyHeavyChars.put(currentChar, copyHeavyChars.get(currentChar) - 1);
                if (copyHeavyChars.get(currentChar) == 0) {
                    copyHeavyChars.remove(currentChar);
                }
            }

        }
        if (copyHeavyChars.isEmpty()) {
            return true;
        }

        return false;
    }

    private static int findShortestLength(String gene, Map<Character, Integer> heavyChars) {
        if (heavyChars.size() == 0) {
            return 0;
        }
        String sub;
        int i, c, length, result;
        length = gene.length();
        result = length;
        for (c = 0; c < length; c++) {
            for (i = 1; i <= length - c; i++) {
                sub = gene.substring(c, c + i);
                if (containsHeavyChars(sub, heavyChars)) {
//                    System.out.println(sub);
                    if (result > sub.length()) {
                        result = sub.length();
                    }
                }
            }
        }
        return result;
    }

    private static Map<Character, Integer> getCharCounts(String str) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (charCounts.containsKey(ch)) {
                charCounts.put(ch, charCounts.get(ch) + 1);
            } else {
                charCounts.put(ch, 1);
            }
        }
        return charCounts;
    }

}
