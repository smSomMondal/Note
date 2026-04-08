import java.util.Arrays;

public class matchainREcur {

    static int [][] dp;
    int solve(int[] p, int i, int j) {
        // Base case: if the chain length is 1, return 0
        if (i >= j) {
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];
        // Initialize minimum cost to a large value
        int minCost = Integer.MAX_VALUE;

        // Try splitting the chain at different points and calculate the cost
        for (int k = i; k < j; k++) {
            int cost = solve(p, i, k) + solve(p, k + 1, j) + p[i - 1] * p[k] * p[j];
            minCost = Math.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

    public static void main(String[] args) {
        // Entry point of the program
        System.out.println("Hello, World!");
        matchainREcur mcr = new matchainREcur();
        int[] p = {10,20,30,40,30,10,40};
        int n = p.length;
        dp = new int[n][n]; // Initialize the dp array
        for(int[] a:dp){
            Arrays.fill(a, -1);
        }
        int result = mcr.solve(p, 1, n - 1); // Call the solve method with appropriate indices
        System.out.println("Minimum number of multiplications is: " + result); 

    }

    // Add your methods here
}