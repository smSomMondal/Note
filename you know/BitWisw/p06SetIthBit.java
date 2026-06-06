public class p06SetIthBit {

    /*
        230 -> 11100110
            230 | (1 << 2)
            11100110
            00001000
            11101110        
    */
    public static void main(String[] args) {

        int num = 230;
        System.out.println("Original Number: " + num);
        System.out.println("Binary        : " + toBinary(num));

        // Left Shift
        int leftShift = num | (1<<3);
        System.out.println("\nAfter set the number:");
        System.out.println("Decimal       : " + leftShift);
        System.out.println("Binary        : " + toBinary(leftShift));
        System.out.println("Ori Binary    : " + toBinary(num));

    }

    public static String toBinary(int num) {
        return String.format("%9s", Integer.toBinaryString(num)).replace(' ', '0');
    }
}
