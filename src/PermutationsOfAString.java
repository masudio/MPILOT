import java.util.LinkedList;
import java.util.List;

/**
 * Created by masudio on 4/28/16.
 */
public class PermutationsOfAString {
    public static void main(String[] args) {
        printPerms("abc");
    }

    private static void printPerms(String s) {
        StringBuilder sb = new StringBuilder(s);
        printPerms(sb, sb.length());
    }

    private static List<StringBuilder> printPerms(StringBuilder sb, int length) {
        if(1 == sb.length()) {
            if(1 == length) {
                System.out.print(sb);
            }
            List<StringBuilder> list = new LinkedList<>();
            list.add(sb);
            return list;
        }

        List<StringBuilder> clay = printPerms(new StringBuilder(sb.substring(1)), length);
        List<StringBuilder> result = new LinkedList<>();
        char c = sb.charAt(0);
        for(StringBuilder curSb : clay) {
            for(int i = 0; i <= curSb.length(); i++) {
                StringBuilder newSb = new StringBuilder(curSb);
                newSb.insert(i, c);
                result.add(newSb);
                if(length == newSb.length()) {
                    System.out.println(newSb);
                }
            }
        }

        return result;
    }
}
