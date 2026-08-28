package Sub_Array;

import java.util.HashMap;

public class p04ContSubArrLessSumK {
    public static void main(String[] args) {
        int[] arr={10, 5, 2, 7, 1, -10};
        int k = 15;

        int i=0,j=0,count=0,sum=0;
        while (j<arr.length) {
            sum+=arr[j];
            
            while(sum>k && i<j){
                sum-=arr[i];
                i++;
            }
            count += j-i+1;
            j++;
        }

        System.out.println(count);
    }
}
