package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class p03CrateGrphMap {
    public static void main(String[] args) {
        int[][] input={{1,2},{2,3},{3,4},{1,3},{2,4}};

        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i < 5; i++) {
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
    }
}
