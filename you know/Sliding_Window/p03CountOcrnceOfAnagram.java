package Sliding_Window;

import java.util.HashMap;

public class p03CountOcrnceOfAnagram {
    public static void main(String[] args) {
        String text = "forfoxxorfxdofr";
        String pat = "for";

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < pat.length(); i++) {
            map.put(pat.charAt(i), map.getOrDefault(pat.charAt(i), 0) + 1);
        }

        int i = 0, j = 0, count = map.size(), ans = 0;

        for (j = 0; j < pat.length() - 1; j++) {
            if (map.containsKey(text.charAt(j))) {
                map.put(text.charAt(j), map.get(text.charAt(j)) - 1);

            }
            if (map.get(text.charAt(j)) == 0) {
                count--;
            }
        }

        for (; j < text.length(); j++, i++) {

            if (map.containsKey(text.charAt(j))) {
                map.put(text.charAt(j), map.get(text.charAt(j)) - 1);

                if (map.get(text.charAt(j)) == 0) {
                    count--;
                }
            }

            if (count == 0) {
                ans++;
            }

            if (map.containsKey(text.charAt(i))) {
                map.put(text.charAt(i), map.get(text.charAt(i)) + 1);

                if (map.get(text.charAt(i)) == 1) {
                    count++;
                }
            }

        }
        System.out.println(ans);
    }
}
