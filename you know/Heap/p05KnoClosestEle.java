package Heap;

import java.util.*;

public class p05KnoClosestEle {
    public static void main(String[] args) {
        int[] arr={12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};
        int k = 4, x = 35;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->Math.abs(arr[b]-x)-Math.abs(arr[a]-x));

        ArrayList<Integer> li = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(i);
            while (pq.size()>k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            li.add(pq.poll());
        }

        // Optional: sort result by value
        Collections.sort(li);

        for (int idx : li) {
            System.out.println(arr[idx]);
        }
    }
}
