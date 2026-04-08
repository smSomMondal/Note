package Dynamic_Programing;

public class p09TargetSum {

    static int count(int[] arr,int sum,int n,int[][] dp){
        // if (sum==0) {
        //     return 1;
        // }

        if (n==0) {
            // return 0;
            return (sum == 0) ? 1 : 0;
        }

        /*if (dp[sum][n]!=0) {
            return dp[sum][n];
        }*/
        /*int pick = 0;
        if (sum>=arr[n-1]) {
            pick = count(arr, sum-arr[n-1], n-1,dp);
        }*/

        // return dp[sum][n] = count(arr, sum-arr[n-1], n-1,dp) + count(arr, sum+arr[n-1], n-1,dp);
        return count(arr, sum-arr[n-1], n-1,dp) + count(arr, sum+arr[n-1], n-1,dp);
    }
    public static void main(String[] args) {
         int[] arr ={1, 1, 1, 1, 1};
        int sum =3;
        /*int target = 1;

        for (int i : arr) {
            sum += i;
        }*/

        /*
        if((sum-target) < 0 && (sum-target)%2 == 1){
            System.out.println(0);
            System.out.println("hi");
        }
        int s2 = (sum-target)/2;*/
        int[][] dp = new int[sum+1][arr.length+1];
        count(arr, sum, arr.length, dp);
        // count(arr, sum-target, arr.length, dp);
        System.out.println(count(arr, sum, arr.length, dp));
    }
}
