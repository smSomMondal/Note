package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class p07Dijakastra {

    static ArrayList<Integer> dijkstra(ArrayList<ArrayList<int[]>> adj, int src){

        int V = adj.size();

        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);   // [max,max,max,max,max]

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        dist[src]=0;    //[0,max,max,max,max]

        //arr[waight,source];
        pq.offer(new int[]{0,src});     //{0,src}

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();

            int w = temp[0];
            int u = temp[1];    //src

            if (w>dist[u]) {    // cost[src] > cost[src]
                continue;
            }

            for (int[] i : adj.get(u)) {
                
                int v = i[0]; //dest
                int wg = i[1];

                if (dist[v]>dist[u]+wg) {   // cost[dest] > cost[src] + (cost src -> dest)
                    dist[v]=dist[u]+wg;
                    pq.offer(new int[]{dist[v],v}); //{cost[dest],dest}
                }
            }
            
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int d : dist)
            result.add(d);

        // Return the final shortest distances from the source
        return result;

    }

    static void addEdge(ArrayList<ArrayList<int[]>> adj, int u, int v, int w) {
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }
    public static void main(String[] args) {
        int V = 5;
        int src = 2;

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1, 4);
        addEdge(adj, 0, 2, 8);
        addEdge(adj, 1, 4, 6);
        addEdge(adj, 1, 2, 3);
        addEdge(adj, 2, 3, 2);
        addEdge(adj, 3, 4, 10);

        ArrayList<Integer> result = dijkstra(adj, src);
        System.out.println(result);
    }
}
