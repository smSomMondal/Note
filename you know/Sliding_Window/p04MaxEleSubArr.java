package Sliding_Window;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class p04MaxEleSubArr {
    public static void main(String[] args) {
        int[] arr = { 45, 8, 12, 7 };// [3, 3, 4, 5, 5, 5, 6]
        int k = 3;
        // int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };// [3, 3, 4, 5, 5, 5, 6]
        // int k = 3;
        int i = 0, j = 0;
        // int max=0,smax=0;

        ArrayList<Integer> li = new ArrayList<>();
        /*{
            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> arr[b] - arr[a]);

            while (j < k - 1) {
                heap.add(j);
                j++;
            }

            for (; j < arr.length; i++, j++) {
                heap.add(j);
                System.out.println(heap);
                li.add(arr[heap.peek()]);
                while (heap.peek() <= i) {
                    heap.poll();
                }

            }
        }*/
        
        
        Deque<Integer> qu = new ArrayDeque<>();

        while (j<k-1) {
            while (!qu.isEmpty() && arr[j]>=arr[qu.peekLast()]) {
                qu.pollLast();
            }
            qu.addLast(j);
            j++;
        }

        for ( ; j < arr.length; i++,j++) {
            while (!qu.isEmpty() && arr[j]>=arr[qu.peekLast()]) {
                qu.pollLast();
            }
            qu.addLast(j);
            li.add(arr[qu.peekFirst()]);
            while (!qu.isEmpty() && qu.peekFirst()<=i) {
                qu.pollFirst();
            }
        }
        System.out.println(li);
    }
}
