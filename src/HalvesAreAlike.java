import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HalvesAreAlike {
    public boolean halvesAreAlike(String s) {
        Set<Character> vowel = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        int firstHalf = 0;
        int secondHalf = 0;
        int halve = s.length() / 2; // it is ensured that s.length() is even
        for (int i = 0; i < halve; i++) {
            if(vowel.contains(s.charAt(i))) {
                firstHalf++;
            }
            if (vowel.contains(s.charAt(i + halve))) {
                secondHalf++;
            }
        }
        return firstHalf == secondHalf;
    }

    public static void main(String[] args) {
        HalvesAreAlike haa = new HalvesAreAlike();
        String s = "Zhiteng love Carolyn";
        System.out.println(haa.halvesAreAlike(s));
    }
}
