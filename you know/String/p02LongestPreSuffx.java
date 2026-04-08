package String;

public class p02LongestPreSuffx {

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
                //System.out.println("hiii 27");

        }
    }    


    public static void main(String[] args) {
        
        /*String s = "aabaacaadaabaaba"; //0101201201234501
        int[] lps = new int[s.length()];
        constractLPS(s,lps);
        for (Character i : s.toCharArray()) {
            System.out.print(i);
        }
        System.out.println();
        for (int i : lps) {
            System.out.print(i);
        }
        System.out.println();*/
        String s1 = "abcabcababd"; //0101201201234501
        int[] lps1 = new int[s1.length()];
        constractLPS(s1,lps1);
        for (Character i : s1.toCharArray()) {
            System.out.print(i);
        }
        System.out.println();
        for (int i : lps1) {
            System.out.print(i);
        }
    }
}
