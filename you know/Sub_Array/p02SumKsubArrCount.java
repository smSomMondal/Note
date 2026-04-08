package Sub_Array;

import java.util.HashMap;

public class p02SumKsubArrCount {
    public static void main(String[] args) {
        int[] arr={10, 2, -2, -20, 10};
        int k = -10;

        int i=0,count=0,sum=0;
        HashMap<Integer,Integer> mp = new HashMap<>();
        mp.put(0,1);
        while (i<arr.length) {
            sum+=arr[i];

            if (mp.containsKey(sum-k)) {
                count += mp.get(sum-k);
            }

            mp.put(sum, mp.getOrDefault(sum,0)+1);
            i++;
        }

        System.out.println(count);

    }
}
