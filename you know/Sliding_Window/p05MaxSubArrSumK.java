package Sliding_Window;

import java.util.HashMap;
import java.util.Map;

public class p05MaxSubArrSumK {
    public static void main(String[] args) {
        //int[] arr = {10, 5, 2, 7, 1, -10}; // ans = 6
        //int k = 15;
        int[] arr = {94 ,-33, -13, 40, -82, 94, -33, -13, 40, -82}; // ans = 5
        int k = 52;
        // int[] arr = {10, -10, 20, 30};// ans = 0
        // int k = 5;
        System.out.println(longestSubarrayMyCode(arr,k));//only work for all positive
        System.out.println(longestSubarray(arr,k));
    }
    static int longestSubarrayMyCode(int[] arr, int k){
        int i=0,j=0,sum=0,max=0;

        while (j<arr.length) {
            sum+=arr[j];
            if (sum==k && j-i+1>max) {
                max = j-i+1;
            }
            while (sum>k && i< j) {
                sum-=arr[i];
                i++;
            }
            /* 
            while (arr[j]<0 && sum<k && i>0) {
                i--;
                sum+=arr[i];                
            }
            */
            if (sum==k && j-i+1>max) {
                max = j-i+1;
            }
            j++;
        }

        return max;
    }

    static int longestSubarray(int[] arr, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        int prefSum = 0;

        for (int i = 0; i < arr.length; ++i) {
            prefSum += arr[i];

			// Check if the entire prefix sums to k
            if (prefSum == k) 
                res = i + 1;

            // If prefixSum - k exists in the map then there exist such 
      		// subarray from (index of previous prefix + 1) to i.
            else if (mp.containsKey(prefSum - k)) 
                res = Math.max(res, i - mp.get(prefSum - k));

            // Store only first occurrence index of prefSum
            if (!mp.containsKey(prefSum))
                mp.put(prefSum, i);
        }

        return res;
    }
}
