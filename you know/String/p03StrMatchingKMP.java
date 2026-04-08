package String;

import java.util.ArrayList;

public class p03StrMatchingKMP {

    static void constractLPS(String str,int[] lps){
        int len=0;
        int i = 1;
        lps[0]=0;

        while (i<str.length()) {
            if(str.charAt(i)==str.charAt(len)){
                //System.out.println("hiii 12");
                len++;
                lps[i]=len;
                i++;
            }

            else{
                if (len != 0) {
                    len = lps[len-1];
                }
                else{
                    lps[i]=0;
                    i++;
                }
            }
                
        }
    }

    public static void main(String[] args) {
        String s ="aabaacaadaabaaba";
        String pat = "aaba";

        int m = pat.length();
        int n = s.length();

        int[] lps = new int[m];

        constractLPS(pat, lps);

        ArrayList<Integer> ans = new ArrayList<>();

        int i=0,j=0;

        while (i<n) {
            if(s.charAt(i)==pat.charAt(j)){
                ++i;
                ++j;

                if(j==m){
                    ans.add(i-j);

                    j=lps[j-1];
                }
            }else{
                if (j!=0) {
                    j=lps[j-1];
                } else {
                    i++;
                }
            }
        }

        System.out.println(ans);
    }

}
