public class p05cheqIthBitSet {

    /*
        230 -> 11100110
            230 & (1 << 2)
            11100110
            00000100
            00000100

        230 -> 11100110
            (230 >> 2) & 1
            00111001
            00000001
            00000001
    */
    public static void main(String[] args) {
        int a = 230;

        if ((a & (1 << 2)) != 0) {
            System.out.println("2 th bit is set");
        }else{
            System.out.println("2 th bit is not set");
        }

        if ((1 & (a >> 3)) != 0) {
            System.out.println("3 th bit is set");
        }else{
            System.out.println("3 th bit is not set");
        }

    }
}
