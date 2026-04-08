package Importent;
//https://www.youtube.com/shorts/3-y7KRC-v40
public class B17GashStationProblem {

    static int gasStionIndex(int[] gas,int[] cost){
        int index = 0;
        int totalGas = 0;
        int currGas = 0;
        for (int i = 0; i < cost.length; i++) {
            totalGas += gas[i]-cost[i];
            currGas += gas[i]-cost[i];
            if (currGas<0) {
                index = i+1;
                currGas =0;
            }
        }
        if (totalGas<0) {
            return -1;
        }

        return index;
    }
    public static void main(String[] args) {
        int[] gas = {4,5,7,4 };
        int[] cost = {6, 6, 3, 5};
        System.out.println(gasStionIndex(gas, cost));
    }
}
