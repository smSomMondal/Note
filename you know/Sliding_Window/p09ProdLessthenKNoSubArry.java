package Sliding_Window;

public class p09ProdLessthenKNoSubArry {
    
    public static void main(String[] args) {
        int[] arr = {10,5,2,6,7};
        int k =100;

        int i=0,j=0;
        int mul=1,count=0;

        while (i<arr.length) {
            mul *= arr[i];

            while (mul>k) {
                mul /= arr[j];
                j++;
            }

            if(mul<=k) count += (i-j+1);
            i++;
        }

        System.out.println(count);
    }
}
