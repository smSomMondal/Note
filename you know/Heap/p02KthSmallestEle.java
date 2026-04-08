package Heap;

import java.util.PriorityQueue;

public class p02KthSmallestEle {

    public static void main(String[] args) {
        int[] arr = { 1, 6, 4, 8, 2, 0, 3, 7, 5, 12, 33 };
        int k = 4;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        System.out.println(pq);
        System.out.println(pq.peek());
        System.out.println(pq);
    }

}
