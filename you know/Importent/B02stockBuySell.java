package Importent;

/*  https://www.geeksforgeeks.org/dsa/stock-buy-sell/#expected-approach-accumulate-profit-on-time-and-o1-space   */

public class B02stockBuySell {

    static int maxProfit(int[] arr){
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i-1]<arr[i]){
                sum += (arr[i]-arr[i-1]);
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] prices = { 100, 180, 260, 310, 40, 535, 695 };
        System.out.println(maxProfit(prices));
    }
}
