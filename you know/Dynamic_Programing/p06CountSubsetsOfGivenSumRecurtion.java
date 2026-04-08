package Dynamic_Programing;

public class p06CountSubsetsOfGivenSumRecurtion {

    static int count(int[] arr,int sum,int n){
        if (sum==0) {
            return 1;
        }

        if (n==0) {
            return 0;
        }

        int pick = 0;
        if (sum>=arr[n-1]) {
            pick = count(arr, sum-arr[n-1], n-1);
        }

        return pick + count(arr, sum, n-1);
    }
    public static void main(String[] args) {
        int[] arr = {1,1,1,1};
        int target = 3;
        // int[] arr = {1, 2, 3, 3};
        // int target = 6;
        System.out.println(count(arr, target, arr.length));
    }
}
