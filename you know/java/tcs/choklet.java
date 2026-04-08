import java.util.*;

public class choklet{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        int j=0;
        for(int i=0;i<n;i++){
            int val = sc.nextInt();
            if(val!=0){
                arr[j++]=val;
            }
        }

        int i = 0;
        if (n>0) {
            System.out.printf("%d",arr[i++]);
        }

        for( ;i<n;i++){
            System.out.printf(", %d",arr[i]);
        }
    }
} 
