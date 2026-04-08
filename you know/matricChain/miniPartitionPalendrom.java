import java.util.Arrays;

public class miniPartitionPalendrom {

    static int [][] dp;

    boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    int solve(String p, int i, int j) {
        if (i >= j) {
            return 0;
        }
        
        if(dp[i][j] != -1) return dp[i][j];

        if (isPalindrome(p.substring(i, j))) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        int left, right;
        // Try splitting the chain at different points and calculate the cost
        for (int k = i+1; k < j; k++) {
            if(dp[i][k] != -1 ) {
                left = dp[i][k];
            }else{
                left = solve(p, i, k);
                dp[i][k] = left;
            }
            if(dp[k+1][j] != -1) {
                right = dp[k+1][j];
            }else{
                right = solve(p, k + 1, j);
                dp[k+1][j] = right;
            }
            int cost = left + right +1;
            minCost = Math.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

    public static void main(String[] args) {
        // Entry point of the program
        //System.out.println("Hello, World!");
        miniPartitionPalendrom mcr = new miniPartitionPalendrom();

        String p = "aaba";
        int n = p.length();
        dp = new int[n][n]; 
        for(int[] a:dp){
            Arrays.fill(a, -1);
        }
        int result = mcr.solve(p, 1, n - 1); 
        System.out.println("Minimum number of multiplications is: " + result); 

    }

}