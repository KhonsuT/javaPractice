package moderate;

import java.util.*;

public class smallestDiff {

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(3,1,4,5,2,9);
        List<Integer> l2 = Arrays.asList(11,1,8,7,14,5,16);
        Collections.sort(l1);
        Collections.sort(l2);
        int p1 = 0;
        int p2 = 0;
        int min = Integer.MAX_VALUE;
        int[] pair = {-1,-1};
        while(p1<l1.size() && p2<l2.size()) {
            if(Math.abs(l1.get(p1)-l2.get(p2))< min) {
                min = Math.abs(l1.get(p1)-l2.get(p2));
                pair = new int[]{p1,p2};
            }
            if(l1.get(p1)<l2.get(p2)) {
                p1++;
            }
            else {
                p2++;
            }
        }
        System.out.println("Min: "+ min);
        Arrays.stream(pair).forEach(System.out::println);
    }
}
