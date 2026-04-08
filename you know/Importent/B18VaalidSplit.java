package Importent;

//https://www.youtube.com/shorts/5YlcFpQauOM
import java.util.*;
public class B18VaalidSplit {
    public static void main(String[] args) {
        
        int[] arr = {10,4,-8,7,3,6};
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        int sum2 = 0;
        for (int i = 0; i < arr.length; i++) {
            sum2 += arr[i];
            if (sum2 >= (sum-sum2)) {
                ans.add(i);
            }
        }

        System.out.println(ans);
    }    
}