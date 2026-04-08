package Stack;

import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

public class p05MaxAreaOfHistogram {

    static void NSL(int[] arr,int[] ans){

        Stack<Integer> st = new Stack<>();

        for(int i=0;i<arr.length;i++){
            while (!st.isEmpty() && arr[st.peek()] > arr[i] ) {
                st.pop();
            }

            ans[i] = st.isEmpty() ? i : st.peek();

            st.push(i);
        }

    }

    static void NSR(int[] arr,int[] ans){
        
        Stack<Integer> st = new Stack<>();

        for(int i=arr.length-1;i>=0;i--){
            while (!st.isEmpty() && arr[st.peek()] > arr[i] ) {
                st.pop();
            }

            ans[i] = st.isEmpty() ? i : st.peek();

            st.push(i);
        }

    }
    
    static int maxArea(int[] arr){

        int[] l = new int[arr.length];
        int[] r = new int[arr.length];

        NSL(arr,l);
        NSR(arr,r);

        int max = Integer.MIN_VALUE,min;

        for(int i=0;i<arr.length;i++){
            min = Math.min(arr[l[i]],arr[r[i]]);
            max = Math.max(max, min*(r[i]-l[i]+1));
        }

        return max;
    }
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        // [60, 20, 50, 40, 10, 50, 60]
        int[] arr = Arrays.stream(sc.nextLine().replaceAll("[\\[\\]]", "").split(",\\s*")).mapToInt(Integer::parseInt).toArray();

        System.out.println(maxArea(arr));

    }
}
