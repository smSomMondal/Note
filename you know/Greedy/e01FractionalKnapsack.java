package Greedy;

import java.util.Arrays;

class e01FractionalKnapsack {

    public static void main(String[] args) {
        int[] val = { 60, 100, 120 };
        int[] wt = { 10, 20, 30 };
        int capacity = 50;

        int n = val.length;

        // Create 2D array to store value and weight
        // items[i][0] = value, items[i][1] = weight
        int[][] items = new int[n][2];

        for (int i = 0; i < n; i++) {
            items[i][0] = val[i];
            items[i][1] = wt[i];
        }

        // Sort items based on value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(
                (double) b[0] / b[1],
                (double) a[0] / a[1]));

        double res = 0.0;
        int currentCapacity = capacity;

        for (int i = 0; i < n; i++) {

            // If we can take the entire item
            if (items[i][1] <= currentCapacity) {
                res += items[i][0];
                currentCapacity -= items[i][1];
            }

            // Otherwise take a fraction of the item
            else {
                res += (1.0 * items[i][0] / items[i][1]) * currentCapacity;

                // Knapsack is full
                break;
            }
        }

        System.out.println(res);
    }
}