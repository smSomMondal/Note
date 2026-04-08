package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class p04SortKsortedArr {
    public static void main(String[] args) {
        int[] arr={2, 3, 1, 4};
        int k= 2;
        int i;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(i=0;i<arr.length;i++){
            pq.add(arr[i]);
            System.out.println(pq);
            while (pq.size()>k) //***** TIGGER WHEN SIZE OF QUEUE EXCED K *****/
            {
                arr[i-k]=pq.peek();
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            arr[i - k] = pq.poll();
            i++;
        }

        System.out.println(Arrays.toString(arr));
    }
}
