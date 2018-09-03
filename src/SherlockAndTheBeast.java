import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SherlockAndTheBeast {
    static void decentNumber(int n) {

        if (n < 3) {
            System.out.println(-1);
            return;
        }

        int reminder = n;
        for (int i = 0; i < n; i++) {
            if (reminder < 0) {
                System.out.println(-1);
                return;
            }
            if (reminder%3 == 0) {
                printDigits(n - i*5, i*5);
                return;
            }
            reminder -= 5;

        }

    }

    private static void printDigits(int fiveCount, int threeCount) {
        for (int j = 0; j < fiveCount; j++) {
            System.out.print(5);
        }
        for (int j = 0; j < threeCount; j++) {
            System.out.print(3);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                decentNumber(n);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
