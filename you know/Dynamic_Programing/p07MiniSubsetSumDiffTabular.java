package Dynamic_Programing;

public class p07MiniSubsetSumDiffTabular {
    public static void main(String[] args) {
        
        int[] arr ={1, 6, 11, 5};
        int sum =0;

        for (int i : arr) {
            sum += i;
        }

        /*if (sum%2 == 0) {
            sum = sum/2;
        }else{
            sum = sum/2;
            sum++;
        }*/
        

        boolean[][] dp = new boolean[sum+1][arr.length+1];

        for (int i = 0; i <= arr.length; i++) {
            dp[0][i]=true;
        }

        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= arr.length; j++) {
                
                boolean pick = false;

                if (i >= arr[j-1]) {
                    pick = dp[i - arr[j-1]][j-1];
                }

                dp[i][j] = pick || dp[i][j-1];
            }
        }

        /*for (int i = 0; i <= sum; i++) {
            System.out.print(dp[i][arr.length] + " ");
        }*/

        int min = Integer.MAX_VALUE;
        /*for (int i = 0; i <= sum; i++) {
            if(dp[i][arr.length]){
                min=Math.min(min, sum-i);
            }
        }*/
        for (int i = 0; i <= sum / 2; i++) {
            if (dp[i][arr.length]) {
                min = Math.min(min,Math.abs((sum - i) - i));
            }
        }
        System.out.println(min);
    }
}
