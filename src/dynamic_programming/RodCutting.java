package dynamic_programming;

import java.io.IOException;

public class RodCutting {

    static int cutRod(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int q = -1;
        for (int i = 1; i < n; i++) {
            q = Math.max(q, p[i] + cutRod(p, n - i));
        }
        return q;
    }


    public static void main(String[] args) throws IOException {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        int rev = cutRod(prices, prices.length);

        System.out.println("rev: " + rev);
    }
}
