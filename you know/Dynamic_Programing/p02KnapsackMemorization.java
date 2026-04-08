package Dynamic_Programing;

import java.util.Arrays;

public class p02KnapsackMemorization {

    static int[][]dp;
    static int knapsack(int[] w,int[] v,int wt,int n){    

        //  BASE CONDITION
        if (n<0 || wt<=0) {
            return 0;
        }
        
        //  MEMORIZATION CONDITION
        if (dp[wt][n]!=-1) {
            return dp[wt][n];
        }

        //  
        if (w[n]>wt) {
            return dp[wt][n] = knapsack(w, v, wt, n-1);
        }

        return dp[wt][n] = Math.max(v[n] + knapsack(w, v, wt-w[n], n-1), knapsack(w, v, wt, n-1));
    }
    public static void main(String[] args) {
        int[] val = {1, 2, 3};
        int[] wt = {4, 5, 1};
        int W = 4;
        int N = val.length;

        dp = new int[W+1][N+1];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }

        System.out.println(knapsack(wt, val, W, N-1));
        /*for (int i = 0; i <= W; i++) {
            for (int j = 0; j <= N; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/
        
        
    }
}
