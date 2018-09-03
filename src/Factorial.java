import java.math.BigInteger;
import java.util.*;


public class Factorial {

private static int SIZE = 1000007;
    private static BigInteger[] fact = new BigInteger[SIZE];

    public static void main(String args[]) throws Exception {

        Scanner s = new Scanner(System.in);
        String T = s.nextLine();
        int numberOfTestCases = Integer.parseInt(T);

        factorial();
        BigInteger [] result = new BigInteger[numberOfTestCases];
        for (int i = 0; i < numberOfTestCases; i++) {
            String numberString = s.nextLine();
            int number = Integer.parseInt(numberString);
            result[i] = fact[number];

        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }


    }

    static void factorial() {
        fact[0] = BigInteger.ONE;
        fact[1] = BigInteger.ONE;

        for (int i = 2; i < SIZE; i++) {
            fact[i] = fact[i-1].multiply(BigInteger.valueOf(i)).mod(BigInteger.valueOf(1000000007));
        }
    }

}
