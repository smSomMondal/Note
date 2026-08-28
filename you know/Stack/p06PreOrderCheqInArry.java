package Stack;
//  https://www.geeksforgeeks.org/dsa/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class p06PreOrderCheqInArry {
    public static boolean canRepresentBST(List<Integer> arr) {
        // code here
        
        Stack<Integer> st = new Stack<>();
        
        int root = Integer.MIN_VALUE;
        
        for(int i=0;i<arr.size();i++){
            
            if(arr.get(i)<root)
                return false;
                
            while( !st.isEmpty() && st.peek()<arr.get(i) ){
                root = st.pop();
            }
            
            st.push(arr.get(i));
        }
        
        return true;
    }
    public static void main(String[] args) {
        List<Integer> arr
            = Arrays.asList(40, 30, 35, 80, 100);

        if (canRepresentBST(arr))
            System.out.println("true");
        else
            System.out.println("false");
    }
}
