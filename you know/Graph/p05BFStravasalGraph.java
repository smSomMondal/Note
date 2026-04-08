package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class p05BFStravasalGraph {
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
        
        
        // CREATE Queue Q
        ArrayDeque<Integer> q = new ArrayDeque<>();

        // ENQUEUE s onto Q
        q.add(0);

        // MARK s as visited
        Boolean[] visited = new Boolean[5];
        Arrays.fill(visited, false);
        visited[0]=true;

        // WHILE Q is not empty:
        while (!q.isEmpty()) {

        //     v = DEQUEUE Q
            int top = q.poll();
            /* process the top */
            {
                System.out.println(top);
            }
        //     FOR EACH neighbor w of v in G:
            for(int val:map.get(top)){

        //         IF w is not visited:
                if (!visited[val]) {
        //             MARK w as visited
                    visited[val]=true;
        //             ENQUEUE w onto Q
                    q.add(val);
        //             // Optional: Store parent or distance information here
                }
            }
        }      

    }
}
