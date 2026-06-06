public class p02BinaToDec {
    public static void main(String[] args) {
        String s = "10101010101010101010000110";
        
        int n = s.length() - 1;

        int ans=0;
        int p=1;

        for (int i = n; i >=0 ; i--) {

            ans += Character.getNumericValue(s.charAt(i))*p;
            p *= 2;
        }

        System.out.println(ans);

    }
}
