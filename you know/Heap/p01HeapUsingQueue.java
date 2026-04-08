package Heap;

import java.util.PriorityQueue;

public class p01HeapUsingQueue {
    public static void main(String[] args) {
        int[] arr = { 1,  6, 4,8, 2, 0, 3, 7, 5, 12, 33 };

        //min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(pq);
            System.out.println(pq.poll());
        }


        //max heap
        PriorityQueue<Integer> pqRev = new PriorityQueue<>((a,b)->b-a);

        for (int i = 0; i < arr.length; i++) {
            pqRev.add(arr[i]);
        }
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(pqRev);
            System.out.println(pqRev.poll());
        }
    }
}
