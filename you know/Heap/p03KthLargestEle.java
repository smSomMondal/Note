package Heap;
import java.util.PriorityQueue;

public class p03KthLargestEle {
    public static void main(String[] args) {
        int[] arr = { 1, 6, 4, 8, 2, 0, 3, 7, 5, 12, 33 };
        int k = 4;

        // min heap so that min element can remove
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        System.out.println(pq);
        System.out.println(pq.peek());
        System.out.println(pq);

        /*

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // Using a lambda
        names.forEach(s -> System.out.println(s));
        // Using a method reference
        names.forEach(System.out::println);

        */

    }
}
