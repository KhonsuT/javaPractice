package dp;
import java.util.*;
public class paren {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        int count = 3;
        int leftP = count;
        int rightP = count;
        char[] str = new char[count*2];
        int index = 0;
        rec(result, leftP, rightP, str, index);
        for (String s: result) {
            System.out.println(s);
        }
    }

    static void rec(List<String> result, int leftP, int rightP, char[] str, int index) {
        if (leftP<0 || leftP > rightP) {return;}
        if (leftP==0 && rightP == 0) {
            result.add(String.copyValueOf(str));
            return;
        }
        str[index] = '(';
        rec(result, leftP-1, rightP, str, index+1);

        str[index] = ')';
        rec(result, leftP, rightP-1, str, index+1);

    }
}
