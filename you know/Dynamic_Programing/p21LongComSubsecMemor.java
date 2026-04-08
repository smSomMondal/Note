package Dynamic_Programing;

public class p21LongComSubsecMemor {


    static int[][] dp;
    static int longComSubSec(char[] s1,char[] s2,int n1,int n2){
        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        if (s1[n1-1]==s2[n2-1]) {
            return 1 + longComSubSec(s1, s2, n1-1, n2-1);
        }
        return Math.max(longComSubSec(s1, s2, n1-1, n2), longComSubSec(s1, s2, n1, n2-1)) ;
    }
    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        dp = new int[s1.length()+1][s2.length()+1];
        
        System.out.println(longComSubSec(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
    }
}
