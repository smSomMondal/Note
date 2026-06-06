public class p03RightSift {

    /*
        Left Shift -> devide by 2 
            n >> k -> n / 2^k
        Right Shift -> multiply by 2
            n << k -> n * 2^k
    */

    public static String toBinary(int num) {
        return String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0');
    }

    public static void main(String[] args) {
        
        int num = 13; // 00001010

        System.out.println("Original Number: " + num);
        System.out.println("Binary        : " + toBinary(num));

        // Left Shift
        int leftShift = num << 1;
        System.out.println("\nAfter Left Shift (num << 1):");
        System.out.println("Decimal       : " + leftShift);
        System.out.println("Binary        : " + toBinary(leftShift));

        // Right Shift
        int rightShift = num >> 1;
        System.out.println("\nAfter Right Shift (num >> 1):");
        System.out.println("Decimal       : " + rightShift);
        System.out.println("Binary        : " + toBinary(rightShift));
        
    }
}
