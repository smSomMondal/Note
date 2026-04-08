package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class p06DFStravarsalGraph {
    public static void main(String[] args) {
        int[][] input={{0,1},{0,4},{1,2},{1,3},{1,4},{2,3},{3,4}};


        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i,new ArrayList<Integer>());
        }

        for (int i = 0; i < input.length; i++) {
            int a=input[i][0],b=input[i][1];
            map.get(a).add(b); // a -> b
            map.get(b).add(a); // b -> a (undirected)
        }
        
        // Print graph
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            System.out.print("Node " + entry.getKey() + " : ");
            for (int v : entry.getValue()) {
                System.out.print(v + " ");
            }
            System.out.println();
        }


        // CREATE Stack S
        Stack<Integer> st = new Stack<>();

        // PUSH v onto S
        st.push(0);

        // MARK v as visited
        boolean[] visited=new boolean[5];
        Arrays.fill(visited, false);
        visited[0]=true;

        // WHILE S is not empty:
        while (!st.isEmpty()) {
                //System.out.println("Stack : "+st);
        //     current_node = POP S
            int top = st.pop();
        //     // Optional: Process the node here if using post-order, 
        //     // otherwise, the 'VISIT' happens when initially marked visited.
            {
                /* prosses the top */
                System.out.println(top);
            }
        //     FOR EACH neighbor w of current_node:
            for (int val : map.get(top)) {
        //         IF w is not visited:
                if (!visited[val]) {
        //             MARK w as visited
                    visited[val]=true;

        //             PUSH w onto S
                    st.push(val);
                }
            }
        }
    }
}
