package Stack;

import java.util.Stack;

public class p02NearestGraterToLeft {
    public static void main(String[] args) {
        System.out.println("NearestGraterToRight");

        int[] arr = { 5, 3, 2, 4, 4,6, 3, 2, 4, 9 };

        int[] ans = new int[arr.length];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <arr.length; i++) {
            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            // 🔴 MANDATORY STEP
            st.push(arr[i]);
            
        }

        for (int a : arr) {
            System.out.print(a + "\t");
        }
        System.out.println("");;
        for (int a : ans) {
            System.out.print(a + "\t");
        }
    }

}
