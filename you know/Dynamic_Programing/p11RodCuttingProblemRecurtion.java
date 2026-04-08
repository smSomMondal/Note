package Dynamic_Programing;
/*
                        <---------  Rod Cutting   --------->
    Given a rod of length n inches and an array price[]. price[i] denotes the value of a piece of length i. The task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.

    Note: price[] is 1-indexed array.

    Input: price[] =  [1, 5, 8, 9, 10, 17, 17, 20]
    Output: 22
    Explanation:  The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5 + 17 = 22.

    Input : price[] =  [3, 5, 8, 9, 10, 17, 17, 20]
    Output : 24
    Explanation : The maximum obtainable value is 24 by cutting the rod into 8 pieces of length 1, i.e, 8*price[1]= 8*3 = 24.

    Input : price[] =  [3]
    Output : 3
    Explanation: There is only 1 way to pick a piece of length 1.

*/
public class p11RodCuttingProblemRecurtion {

    static int count(int[] arr,int len,int n){
        if(len==0 || n == 0 ){
            return 0;
        }

        int pick = 0;

        if(len >= n){
            pick = arr[n-1] + count(arr, len-n, n);
        }

        return Math.max(pick, count(arr, len, n-1));
    }
    public static void main(String[] args) {
        
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
        int len=price.length;
        int N=price.length;
        System.out.println(count(price, len, N));
        
    }
}
