package Sub_Array;
/*
    Longest Subarray With Sum K
*/

import java.util.HashMap;

public class p03LongSubArrWithSumK {
    public static void main(String[] args) {
        int[] arr={10, 5, 2, 7, 1, -10};
        int k = 15;

        int i=0,max=0,sum=0;
        HashMap<Integer,Integer> mp = new HashMap<>();
        // mp.put(0,0);
        while (i<arr.length) {
            sum+=arr[i];
            if(sum==k){
                max=i+1;
            }
            if (mp.containsKey(sum-k)) {
                max =Math.max(max,i- mp.get(sum-k)+1);
            }
            if (!mp.containsKey(sum)) {
                mp.put(sum,i);
            }
            
            i++;
        }

        System.out.println(max);
    }
}
