package Dynamic_Programing;

public class p01KnapsackRecurtion {
    static int knapsack(int[] w,int[] v,int wt,int n){

        //  BASE CONDITION
        if (n<0 || wt==0) {
            return 0;
        }
        
        //  
        if (w[n]>wt) {
            return knapsack(w, v, wt, n-1);
        }

        return Math.max(v[n] + knapsack(w, v, wt-w[n], n-1), knapsack(w, v, wt, n-1));
    }
/**************************************************************************************************/
    static int knapsackPerfect(int[] w,int[] v,int wt,int n){

        //  BASE CONDITION
        if (n==0 || wt==0) {
            return 0;
        }
        
        //  
        if (w[n-1]>wt) {
            return knapsack(w, v, wt, n-1);
        }

        return Math.max(v[n-1] + knapsack(w, v, wt-w[n-1], n-1), knapsack(w, v, wt, n-1));
    }
/**************************************************************************************************/
    public static void main(String[] args) {
        int[] val = {10, 8, 6};
        int[] wt = {1, 7, 9};
        int W = 8;
        int N = val.length;
        System.out.println(knapsack(wt, val, W, N-1));
        System.out.println(knapsackPerfect(wt, val, W, N));
    }
}
