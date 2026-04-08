package Importent;
// https://www.youtube.com/shorts/pJKRR_0x-UI
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B21SumTriplet {


    static void printAllTriplet(int[] arr,int target){
        List<List<Integer>> res = new ArrayList<>();
        int n = arr.length;

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
          
            // Skip duplicates for i
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            // Two pointer technique
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum == target) {
                    List<Integer> curr = Arrays.asList(arr[i], arr[j], arr[k]);
                    res.add(curr);
                    j++;
                    k--;

                    // Skip duplicates for j and k
                    while (j < n && arr[j] == arr[j - 1]) j++;
                    while (k > j && arr[k] == arr[k + 1]) k--;
                } 
              	else if (sum < target) { 
                    j++;
                }
              	else { 
                    k--;
                }
            }
        }

        for (List<Integer> list : res) {
            System.out.println(list);
        }

    }

    static boolean isPresent(int[] arr,int sum){

        int n = arr.length;

        for(int i=0;i<n;i++){
            int l=0;int r=n-1;
            int reqSum = sum - arr[i];
            while (l<r) {
                if (arr[l]+arr[r]==reqSum) {
                    return true;
                }else if (arr[l]+arr[r]>reqSum) {
                    r--;
                }else{
                    l++;
                }
            }
        }

        return false;
    }
    public static void main(String[] args) {
        int[] arr = { 1, 4, 45, 6, 10, 8 };
        int target = 13;

        Arrays.sort(arr);
        System.out.println(isPresent(arr, target));
        printAllTriplet(arr, target);
    }
}
