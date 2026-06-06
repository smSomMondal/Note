public class p01DecToBina {

    public static void main(String[] args) {
        int n = 543;
        StringBuilder sb = new StringBuilder();

        while (n>0) {
            if (n%2 == 1) {
                sb.append(1);
            }else{
                sb.append(0);
            }
            n = n/2;
        }

        sb.reverse();

        System.out.println(sb.toString());
    }
}