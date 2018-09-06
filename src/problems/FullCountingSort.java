package problems;

import java.util.*;

public class FullCountingSort {

    static String countingSort4(Map<Integer, List<String>> mapped) {

        StringBuilder result = new StringBuilder();

        mapped.forEach((index, list) -> list.forEach(p -> result.append(p).append(" ")));


        return result.toString();

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Map<Integer, List<String>> result = new HashMap<>();

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int nItr = 0; nItr < n; nItr++) {
            String[] xs = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xs[0]);

            String s = xs[1];
            if(nItr < n/2) {
                s = "-";
            }

            result.computeIfAbsent(x, empList -> new ArrayList<>()).add(s);
        }

        String decodedShifr = countingSort4(result);
        System.out.println(decodedShifr);
        scanner.close();
    }

}
