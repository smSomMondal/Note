import java.util.*;

public class togalBit {
    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(Integer.toString(n,10));
        System.out.println(Integer.toString(n,2));
        System.out.println(Integer.toString(n,8));
        System.out.println(Integer.toString(7415912,16));

        char[] ch = Integer.toString(n,2).toCharArray();

        for(int i=0;i<ch.length;i++){
            if(ch[i]=='1'){
                ch[i]='0';
            }else{
                ch[i]='1';
            }
        }

        System.out.println(Integer.parseInt(new String(ch),2));

        float f = sc.nextFloat();
        System.out.println(Float.toString(f));
        System.out.println((float)Math.round(f*100)/100);
    }
}
