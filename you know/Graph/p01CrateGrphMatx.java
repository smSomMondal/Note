package Graph;

public class p01CrateGrphMatx {
    public static void main(String[] args) {
        int[][] input={{1,2},{2,3},{3,4},{1,3},{2,4}};

        int[][] mat=new int[5][5];
        for (int i = 0; i < input.length; i++) {
            int a=input[i][0],b=input[i][1];
            mat[a][b]=mat[b][a]=1;
        }

        for (int i = 1; i < 5; i++) {
            System.out.printf("\nNode %d , Naighbours : ",i);
            for (int j = 0; j < 5; j++) {
                if (mat[i][j]==1) {
                     System.out.printf("%d ", j);                   
                }
            }
        }
    }
}
