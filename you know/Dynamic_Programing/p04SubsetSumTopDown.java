package Dynamic_Programing;

public class p04SubsetSumTopDown {

    static boolean[][] dp;

    static boolean isPresent(int[] arr,int sum,int n){


        if (sum==0) {
            return true;
        }
        if (n==0) {
            return false;
        }

        if (dp[sum][n]) {
            return true;
        }

        boolean pick = false;

        if (sum>=arr[n-1]) {
            pick=isPresent(arr, sum-arr[n-1], n-1);
        }

        return dp[sum][n]=pick || isPresent(arr, sum, n-1);
    }
    public static void main(String[] args) {
        int[] arr = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        // int[] arr = { 3, 34, 4, 12, 5, 2 };
        // int sum = 30;

        dp =new boolean[sum+1][arr.length+1];
        //isPresent(arr, sum, arr.length);
        for (int i = 0; i <= arr.length; i++) {
            dp[0][i] = true;
        }

        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= arr.length; j++) {
                boolean pick = false;

                if (i>=arr[j-1]) {
                    pick=dp[i-arr[j-1]][j-1];
                }

                dp[i][j]=pick || dp[i][j-1];
            }
        }
        System.out.println(dp[sum][arr.length]);
    }
}
