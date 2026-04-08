package Graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class p08KnightToTergate {

    static boolean isValid(int x,int y,int n,boolean[][] vis){
        
        if(x>=0 && y>=0 && x<n && y<n && !vis[x][y]){
            return true;
        }

        return false;
    }
    static int countStep(int[] kPos,int[] ter,int n){
        int ans = 0;

        Deque<int[]> path = new ArrayDeque<>();

        boolean[][] visited = new boolean[n][n] ;

        int x=kPos[0];
        int y=kPos[1];

        int tx=ter[0];
        int ty=ter[1];

        path.add(new int[]{x,y});
        visited[x][y]=true;

        if (x==tx && y==ty) {
            return 0;
        }

        int[] dx = {2,2,-2,-2,1,-1,1,-1};
        int[] dy = {1,-1,1,-1,2,2,-2,-2};

        while (!path.isEmpty()) {
            ans++;
            int size = path.size();
            while (size>0) {
                x=path.peek()[0];
                y=path.peek()[1];
                path.poll();
                for(int i=0;i<8;i++){
                    int xx = x+dx[i];
                    int yy = y+dy[i];

                    if (xx==tx && yy==ty) {
                        return ans;
                    }
                    if (isValid(xx,yy,n,visited)) {
                        visited[xx][yy]=true;
                        path.add(new int[]{xx,yy});
                    }
                }
                size--;
            }
            
        }



        return -1;
    }
    public static void main(String[] args) {
        int n = 30;
        int[] knightPos = { 1, 1 };
        int[] targetPos = { 12, 30 };

        System.out.println(countStep(knightPos, targetPos, n));
    }
}
