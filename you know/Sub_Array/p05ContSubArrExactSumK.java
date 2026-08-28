package Sub_Array;

public class p05ContSubArrExactSumK {

    // -> return all count of sub array less then sum k
    static int p04ContSubArrLessSumK(int[] arr,int k){
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
        return count;
    }
    public static void main(String[] args) {
        int[] arr={10, 5, 2, 7, 1, -10};//3
        int k = 15;
        // int[] arr={9, 4, 20, 3, 10, 5};//2
        // int k = 33;
        // int[] arr={1, 3, 5};//0
        // int k = 2;

        int ans = p04ContSubArrLessSumK(arr,k)-p04ContSubArrLessSumK(arr,k-1);
        System.out.println(ans);

        /*
            Count subarrays with exactly k odd numbers
                ->May change the logic of function
        */
        int[] arr1={2, 2, 5, 6, 9, 2, 11};//8
        int k1 = 2;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i]%2 == 0){
                arr1[i]=0;
            }else{
                arr1[i]=1;
            }
        }
        System.out.println(p04ContSubArrLessSumK(arr1,k1)-p04ContSubArrLessSumK(arr1,k1-1));
        

        /*
            Subarrays with equal 0s and 1s

            Not working
        */

        int[] arr2={1, 0, 0, 1, 1, 0, 0, 1};//12
        int k2 = 0;
        for (int i = 0; i < arr1.length; i++) {
            if(arr2[i]== 0){
                arr2[i]=-1;
            }
        }
        System.out.println(p04ContSubArrLessSumK(arr2,k2)-p04ContSubArrLessSumK(arr2,k2-1));
        
    }
}
