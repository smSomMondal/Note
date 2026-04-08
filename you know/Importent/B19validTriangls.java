package Importent;

import java.util.Arrays;

public class B19validTriangls {
    public static void main(String[] args) {
        int[] arr={4, 6, 3, 7};

        Arrays.sort(arr);

        int res=0;
        for(int i=2;i<arr.length;i++){
            int left=0,right=i-1;

            while (left<right) {
                if (arr[left]+arr[right]>arr[i]) {
                    res += right-left;
                    right--;
                }else{
                    left++;
                }
            }
        }

        System.out.println(res);
    }
}
