package Sliding_Window;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class p02FirstNegSubarray {
    
    public static void main(String[] args) {
        int[] arr={12, -1, -7, 8, -15, 30, 16, 28};
        // int[] ans=new int[arr.length];
        ArrayList<Integer> ans = new ArrayList<>();
        int k=3;

        int i=0,j=0;

        Queue<Integer> qu = new ArrayDeque<Integer>();

        for (j = 0; j < k; j++) {
            if(arr[j]<0){
                qu.offer(j);
            }
        }

        for ( ; j < arr.length; j++,i++) {

            if(qu.size()>0){
                ans.add(arr[qu.peek()]);
            }else{
                ans.add(0);
            }
            // System.out.println("i = "+i+" j = "+j+" list = "+ans);
            if(arr[j]<0){
                qu.offer(j);
            }
            if (qu.peek()==i) {
                qu.poll();
            }

        }

        if(qu.size()>0){
            ans.add(arr[qu.peek()]);
        }else{
            ans.add(0);
        }

        for (int val : arr) {
            System.out.print(val+"\t");
        }
        System.out.println();
        for (int val : ans) {
            System.out.print(val+"\t");
        }
    }
}
