// import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class gfg {
    public static void main(String[] args) {

        String s = "725083";
        int k = 5;
        // String s = "765028321";
        // int k = 5;
        // String s = "149811";
        // int k = 3;
        if(s.length() == k){
            // return "0";
            System.out.println("0");
        }
        int[] ans = new int[s.length()];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <s.length(); i++) {
            while (!st.isEmpty() && s.charAt(st.peek()) <= s.charAt(i)) {
                st.pop();
            }

            if (st.isEmpty()) {
                if(i==0){
                    ans[i] = -1;
                }else{
                    ans[i] = i;
                }
                
            } else {
                ans[i] = st.peek();
            }

            st.push(i);
            
        }
        
        Boolean[] me = new Boolean[s.length()];
        Arrays.fill(me, false);
        
        for (int i = 0; i <me.length; i++) {
            
            if (ans[i] == -1 ) {
                continue;
            }
            if(!me[ans[i]] && k>0){
                me[ans[i]]=true;
                k--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i <me.length; i++){
            if(!me[i]){
               sb.append(s.charAt(i)); 
            }
        }
        
        if(sb.length()>0 && k>0){
            sb.deleteCharAt(0);
            k--;
        }
        
        if(sb.length()>0 && sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        
        // sb.length()>0?sb.toString():"0";
        if(sb.length()>0)
            System.out.println(sb.toString());
        else
            System.out.println("0");
    }
}
