package Sorting;

/*
    input should be in range of 1 -> n
*/

public class p04CyclickSort {

    static void cyclicSort(int[] arr){
        
        for(int i=0;i<arr.length;){
            int correctIndex = arr[i] - 1;

            if(arr[i] != arr[correctIndex]){
                int temp = arr[i];
                arr[i] = arr[correctIndex];
                arr[correctIndex] = temp;
            }else{
                i++;
            }
            /*if(arr[i]>0 && arr[i]<arr.length && arr[i] != arr[correctIndex]){
                int temp = arr[i];
                arr[i] = arr[correctIndex];
                arr[correctIndex] = temp;
            }else{
                i++;
            }*/
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,4,3,2,7,5,6};
        // int[] arr = {1,4,3,-4,2,7,5,-2,6,18};

        for(int a:arr){
            System.out.printf("%d ",a);
        }
        System.out.printf("\n");
        
        cyclicSort(arr);
        for(int a:arr){
            System.out.printf("%d ",a);
        }
    }
}
