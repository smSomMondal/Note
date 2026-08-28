package Sub_Array;

import java.util.Stack;

/*    _______
            _____
    1 4 6 7 3 7 8 1
            !
*/

public class p06SumOfMinOfEachSubArr {

    static int sumSubMins(int[] arr) {
        // code here
        
        int n = arr.length;
        /*int count = 0 ,min;
        for(int i=0;i<n;i++){
            min = arr[i];
            for(int j=i;j<n;j++){
                if(min>arr[j]) min = arr[j];
                    count += min;
            }
        }
        return count;*/
        
        int[] le = new int[n];
        int[] ri = new int[n];
        int sum=0;
        
        Stack<Integer> st = new Stack<>();
        
        for( int i=n-1;i>=0;i-- ){
            while( !st.isEmpty() && arr[i]<=arr[st.peek()] ){
                st.pop();
            }
            ri[i] = st.isEmpty() ? n-i : st.peek()-i;
            st.push(i);
        }
        
        st.clear();
        
        for( int i=0;i<n;i++ ){
            while(!st.isEmpty() && arr[i]<arr[st.peek()]){
                st.pop();
            }
            le[i] = st.isEmpty() ? i+1 : i-st.peek();
            st.push(i);
        }
        
        for( int i=0;i<n;i++ ){
            sum += arr[i]*le[i]*ri[i];
        }
        
        return sum;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(sumSubMins(arr));

        int[] arr1 = {1, 2, 1, 3, 1, 4};
        System.out.println(sumSubMins(arr1));
    }
}
