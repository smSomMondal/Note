package Sliding_Window;

import java.util.HashMap;

public class p06LongestSubStrOfUnicChar {
    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k=3,count=0,count1=0,max=0;
        HashMap<Character,Integer> mp = new HashMap<>();
        for(int i=0,j=0;j<s.length();j++){
            Character a = s.charAt(j);
            mp.put(a, mp.getOrDefault(a, 0)+1);
            if (mp.size()==k) {
                max = Math.max(max,j-i+1);
                count++;
            }
            while (mp.size()>k) {
                char b = s.charAt(i);
                mp.put(b, mp.get(b)-1);
                if (mp.get(b)==0) {
                    mp.remove(b);
                }
                i++;
            }
            count1 += j-i+1; // all sub array size <= k
        }
        // System.out.println(count+" "+count1);
        System.out.println(max);
    }
}
