package Sliding_Window;

/*****************************************************************************
                Minimum Window Subsequence
    You are given two strings, s1 and s2. Your task is to find the smallest substring in s1 such that s2 appears as a subsequence within that substring.

    The characters of s2 must appear in the same sequence within the substring of s1.
    If there are multiple valid substrings of the same minimum length, return the one that appears first in s1.
    If no such substring exists, return an empty string.
    Note: Both the strings contain only lowercase english letters.
    
    Input: s1 = "geeksforgeeks", s2 = "eksrg"
    Output: "eksforg"
    Explanation: "eksforg" satisfies all required conditions. s2 is its subsequence and it is smallest and leftmost among all possible valid substrings of s1.

        APPROCH -> Sliding Window
            1) create a memory[] of size s2 [j] to store position of s1 [i] string
            2) incriment i and macht to s2 till i < SIZE(s1)
                IF match incrimnt j  
                    IF j == SIZE(s2)
                        1) decriment i-- and match to s2 until j == 0
                            if match deciment j--
                        2) check for min subsequence
            3) print substring [ ANS ]
*****************************************************************************/

public class p08MinWinSubseq {
    public static void main(String[] args) {

        String s1="geeksforgeeks";
        String s2="eksrg";
        int n = s2.length();
        int [] mem = new int[n];
        
        int i=0,j=0,min=Integer.MAX_VALUE,a=0,b=0;
        
        while(i<s1.length()){
            if(s1.charAt(i)==s2.charAt(j)){
                mem[j]=i;
                j++;
                if(j==s2.length()){
                    int temp=i;
                    j--;
                    while(j>=0){
                        if(s1.charAt(i)==s2.charAt(j)){
                            mem[j]=i;
                            j--;
                        }
                        i--;
                    }
                    int count = mem[n-1]-mem[0]+1;
                    if(min>count){
                        min=count;
                        a=mem[0];
                        b=mem[n-1]+1;
                    }
                    j=n-1;
                    i=temp;
                }
            }
            i++;
        }
        
        // return s1.substring(a,b);
        System.out.println(s1.substring(a,b));
    }
}
