package problems;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Stream;

public class CeasarCipher {

    private static String alphabet  = "abcdefghijklmnopqrstuvwxyz";

    static Map<Character, Character> toMap(int k) {
        Map<Character, Character> result = new HashMap<>();

        k = k%alphabet.length();
        int length = alphabet.length();
        int index;
        for (int i = 0; i < length; i++) {
            if (i < k) {
                index = length - k + i;
            } else {
                index = i - k;
            }
            result.put(alphabet.charAt(index), alphabet.charAt(i));
        }

        return result;
    }

    // Complete the caesarCipher function below.
    static String caesarCipher(String s, int k) {

        Map<Character, Character> map = toMap(k);

//        Stream.of(map.keySet().toString())
//                .forEach(System.out::println);
//        Stream.of(map.values().toString())
//                .forEach(System.out::println);
        StringBuilder result = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isAlphabetic(s.charAt(i))) {
                result.append(s.charAt(i));
                continue;
            }
            boolean isUpperCase = Character.isUpperCase(s.charAt(i));
            char ch = map.get(Character.toLowerCase(s.charAt(i)));
            if (isUpperCase) {
                ch = Character.toUpperCase(ch);
            }
            result.append(ch);
        }

        return result.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/vahanl/IdeaProjects/HackerRank/output"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
