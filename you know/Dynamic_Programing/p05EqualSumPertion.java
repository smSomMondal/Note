package Dynamic_Programing;

public class p05EqualSumPertion {
    public static void main(String[] args) {
        int[] arr = { 1, 5, 11, 5 };
        int sum =0;
        // int[] arr = { 3, 34, 4, 12, 5, 2 };
        // int sum = 30;

        int count =0;
        for (int i : arr) {
            count += i;
        }

        if (count%2 != 0) {
            System.out.println("false");
            System.exit(0);
        }else{
            sum = count/2;
        }


        boolean[][] dp =new boolean[sum+1][arr.length+1];
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
