package String;

public class p01StrMatchingBrut {

    public static void main(String[] args) {
        String s = "rkemfkcoprtrj";
        String pat = "fkco";

        int i,j,k,flag=1;
        for (i = 0; i < s.length(); i++) {
            for ( j = 0, k = i; j < pat.length(); k++,j++) {
                if (s.charAt(k)!=pat.charAt(j)) {
                    break;
                }
            }
            if (j==pat.length()) {
                //System.out.println("pattarn match at index "+i);
                flag = 0;
                break;
            }
        }

        // (flag > 0) ? System.out.println("pattarn match at index "+i):System.out.println("pattarn dose not match");
        if (flag == 0) {
            System.out.println("pattarn match at index "+i);
        }else{
            System.out.println("pattarn dose not match");
        }
    }
}
