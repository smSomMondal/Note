package Importent;

import java.io.*;
import java.util.*;

public class AA01TakeInput {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String line;
        try{
            while ((line=bf.readLine())!=null && !line.isEmpty()) {
                StringTokenizer st = new StringTokenizer(line);

                System.out.println(st.countTokens());
                while (st.hasMoreTokens()) {
                    System.out.println(st.nextToken());
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}
