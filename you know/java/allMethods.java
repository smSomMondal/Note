import java.util.*;
import java.lang.reflect.Method;

public class allMethods {

    public static void main(String[] args) {
        
        Method[] m = Arrays.class.getMethods();
        for (Method method : m) {
            System.out.println(method);
        }
    }
}