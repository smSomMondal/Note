package Sliding_Window;

public class p01MaxSumSubarray {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 10, 23, 3, 1, 0, 100};//sum=39
        int k = 4;
        int i=0,j=0,sum=0,max=0;

        while(j<k){
            sum+=arr[j];
            j++;
        }

        // max = Math.max(max, sum);
        for (int val : arr) {
            System.out.print(val+" ");
        }
        System.out.println("");
        for ( ; j < arr.length; i++,j++) {
            sum-=arr[i];
            sum+=arr[j];
            System.out.print(sum+" ");
            max = Math.max(max, sum);
                                    
        }
        System.out.println("");
        System.out.println("max = "+max);

    }
}
