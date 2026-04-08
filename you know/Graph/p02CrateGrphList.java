package Graph;

import java.util.ArrayList;

public class p02CrateGrphList {
    public static void main(String[] args) {
        int[][] input={{1,2},{2,3},{3,4},{1,3},{2,4}};

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < input.length; i++) {
            int a=input[i][0],b=input[i][1];
            list.get(a).add(b); // a -> b
            list.get(b).add(a); // b -> a (undirected)
        }
        for (int i = 1; i < 5; i++) {
            System.out.print("Node " + i + " : ");
            for (int v : list.get(i)) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
