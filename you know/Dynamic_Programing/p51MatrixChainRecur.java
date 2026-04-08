package Dynamic_Programing;

public class p51MatrixChainRecur {

    static int MatMul(int[] arr,int i,int j){
        if(i>=j){
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int temp = MatMul(arr, i, k) + MatMul(arr, k+1, j) + (arr[i-1]*arr[k]*arr[j]);
            if (min>temp) {
                min = temp;
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        int[] arr ={2, 1, 3, 4};
        
        System.out.println(MatMul(arr, 1, arr.length-1));

    }
}