package Dynamic_Programing;

public class p12CoinChangeProblem {

    static int count(int[] arr,int sum,int n){
        if(sum==0){
            return 1;
        }

        if( n == 0 || sum < 0 ){
            return 0;
        }

        // Pick (unbounded)
        int pick = count(arr, sum - arr[n - 1], n);

        // Not pick
        int notPick = count(arr, sum, n - 1);

        return pick + notPick;
    }
    public static void main(String[] args) {
        // int[] coins = {2, 5, 3, 6};//5
        // int sum = 10;
        // int[] coins = {1, 2, 3};//5
        // int sum = 5;
        int[] coins = {4};//0
        int sum = 5;


        int N = coins.length;

        System.out.println(count(coins, sum, N));

    }
}
