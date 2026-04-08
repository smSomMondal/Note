// import java.util.*;

class Practice{

    int a;
    static double d;
    boolean flag;
    String name;

    public static void main(String []args){
        float f = 34.345454443f;
        System.out.printf("value = %.2f \n",f);
        System.out.println("value "+(float)Math.round(f*1000)/1000);

        // Math.
        // Integer i ;
        Practice t = new Practice();
        System.out.println(t.a);      // 0
        System.out.println(d);        // 0.0
        System.out.println(t.flag);   // false
        System.out.println(t.name);   // null
    }
}