public class p04SwapTwoNumber {
    public static void main(String[] args) {
        int a = 6;
        int b = 4;
        System.out.println("a = "+a+" b = "+b);

        a = a^b;
        b = a^b;
        a = a^b;

        System.out.println("a = "+a+" b = "+b);
    }
}
