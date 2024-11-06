public class stringRotation {
    public static void main(String[] args) {
        String s1 = "ilovemywife";
        String s2 = "vemywifeilo";
        String s3 = "emywiffilov";
        System.out.println(stringRotationChecker(s1,s2));
        System.out.println(stringRotationChecker(s1,s3));
    }

    static boolean stringRotationChecker(String s1, String s2) {
        if (s1.length()!=s2.length()) return false;
        for (int i = 0; i<s2.length();i++) {
            String part1 = s2.substring(i);
            String part2 = s2.substring(0,i);
            String res = part1  + part2;
            // System.out.print(res);
            // System.out.print("," + s1);
            // System.out.println();
            if (res.equals(s1)) return true;
        }
        return false;
    } 
}