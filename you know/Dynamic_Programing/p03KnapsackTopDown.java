package Dynamic_Programing;

// import java.util.Arrays;

public class p03KnapsackTopDown {
    static int[][] dp;

    static int knapsack(int[] w, int[] v, int wt, int n) {

        // BASE CONDITION
        if (wt == 0 || n < 0) {
            return 0;
        }

        // MEMORIZATION CONDITION
        if (dp[wt][n] != -1) {
            return dp[wt][n];
        }

        //
        if (w[n] > wt) {
            return dp[wt][n] = knapsack(w, v, wt, n - 1);
        }

        return dp[wt][n] = Math.max(v[n] + knapsack(w, v, wt - w[n], n - 1), knapsack(w, v, wt, n - 1));
    }

    public static void main(String[] args) {
        /*int[] val = { 10, 40, 30, 50 };// ans=80
        int[] wt = { 5, 4, 2, 3 };
        int W = 5;
        int N = val.length;*/
        int[] val = {10, 8, 6};
        int[] wt = {1, 7, 9};
        int W = 7;
        int N = val.length;

        dp = new int[W + 1][N + 1];

        for (int i = 0; i <= W; i++) {
            for (int j = 0; j <= N; j++) {

                /*********************************
                 * if (n<0 || wt<=0) {
                 * return 0;
                 * }
                 *********************************/
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                /*********************************
                 * // MEMORIZATION CONDITION
                 * if (dp[wt][n]!=-1) {
                 * return dp[wt][n];
                 * }
                 *********************************/

                /*********************************
                 * if (w[n]>wt) {
                 * return dp[wt][n] = knapsack(w, v, wt, n-1);
                 * }
                 *********************************/
                if (wt[j-1] > i) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }

                /*********************************
                 * return dp[wt][n] = Math.max(v[n] + knapsack(w, v, wt-w[n], n-1), knapsack(w,
                 * v, wt, n-1));
                 *********************************/
                dp[i][j] = Math.max(val[j-1] + dp[i - wt[j-1]][j - 1], dp[i][j - 1]);
                
            }
        }
        /*
            System.out.println(knapsack(wt, val, W, N-1));
        */
        System.out.println(dp[W][N-1]);

    }
}
