package Importent;

public class B16NextParmutation {

    static void nextPermu(int[] arr){
        int n =arr.length;
        int pivot = -1;

        for (int i = n-2; i >=0; i--) {
            if (arr[i]<arr[i+1]) {
                pivot = i;
                break;
            }
        }

        if (pivot==-1) {
            reverse(arr,0,n-1);
            return;
        }

        for (int i = n-1; i >=0; i--) {
            if (arr[pivot]<arr[i]) {
                swap(arr, pivot, i);
                break;
            }
        }

        reverse(arr, pivot+1, n-1);

    }

    static void reverse(int[] arr,int i,int j){
        while (i<j) {
            swap(arr,i++,j--);
        }
    } 

    static void swap(int[] arr,int i,int j){
        int t = arr[i];
        arr[i]=arr[j];
        arr[j]=t;  
    }
    public static void main(String[] args) {
        int[] arr = { 2, 4, 1, 7, 5, 0 };
        nextPermu(arr);
        
        for(int i = 0; i < arr.length; i++)
        System.out.print(arr[i] + " ");
    }
}
