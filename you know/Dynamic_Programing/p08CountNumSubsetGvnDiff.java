package Dynamic_Programing;

/*
     s1 - s2 = d {or K that are  that is targate}
     s1 > s2
     s1 = totalSum - s2
    ->
        totalSum - s2 - s2 = d
        s2 = ( totalSum - d ) / 2
*/

import java.util.Arrays;

public class p08CountNumSubsetGvnDiff {

    static int count(int[] arr,int sum,int n,int[][] dp){
        if (sum==0) {
            return 1;
        }

        if (n==0) {
            return 0;
        }

        if (dp[sum][n]!=0) {
            return dp[sum][n];
        }
        int pick = 0;
        if (sum>=arr[n-1]) {
            pick = count(arr, sum-arr[n-1], n-1,dp);
        }

        return dp[sum][n] = pick + count(arr, sum, n-1,dp);
    }

    public static void main(String[] args) {
        int[] arr ={1, 2, 3, 1, 2};
        int sum =0;
        int target = 1;

        for (int i : arr) {
            sum += i;
        }

        if((sum-target) < 0 && (sum-target)%2 == 1){
            System.out.println(0);
            System.out.println("hi");
        }
        int s2 = (sum-target)/2;
        int[][] dp = new int[s2+1][arr.length+1];
        count(arr, s2, arr.length, dp);
        // count(arr, sum-target, arr.length, dp);
        System.out.println(dp[s2][arr.length]);

        for (int i = 0; i <= s2; i++) {
            for (int j = 0; j <= arr.length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        for (int[] is : dp) {
            Arrays.fill(is, 0);;
        }
        for (int i = 0; i <= arr.length; i++) {
            dp[0][i]=1;
        }

        for (int i = 1; i <= s2; i++) {
            for (int j = 1; j <= arr.length; j++) {
                
                int pick = 0;

                if (i >= arr[j-1]) {
                    pick = dp[i - arr[j-1]][j-1];
                }

                dp[i][j] = pick + dp[i][j-1];
            }
        }

        for (int i = 0; i <= s2; i++) {
            for (int j = 0; j <= arr.length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        
    }
}
