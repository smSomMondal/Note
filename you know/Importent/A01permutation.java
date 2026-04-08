package Importent;

import java.util.ArrayList;

public class A01permutation {
    
    static ArrayList<ArrayList<Integer>> li;
    static void func (int[] arr,int index){

        if (index == arr.length) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int num : arr) {
                temp.add(num);
            }
            li.add(temp);
            return;
        }        

        for (int i = index; i < arr.length; i++) {
            swap(arr,index,i);
            func(arr, index+1);
            swap(arr,index,i);
        }

    }

    static void swap(int[] arr,int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b]=temp;
    }

    public static void main(String[] args) {
        li = new ArrayList<>();
        int[] arr = {1,2,3,4};
        func(arr, 0);
        System.out.println(li);
    }
}
