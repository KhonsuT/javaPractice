package moderate;

import java.util.*;

public class intersection {
    public static void main(String[] args) {
        List<List<Integer>> seg1 = new ArrayList<>();
        seg1.add(Arrays.asList(0,0));
        seg1.add(Arrays.asList(1,0));
        seg1.add(Arrays.asList(2,0));
        seg1.add(Arrays.asList(3,0));
        seg1.add(Arrays.asList(4,0));

        List<List<Integer>> seg2 = new ArrayList<>();
        seg2.add(Arrays.asList(13,-1));
        seg2.add(Arrays.asList(13,0));
        seg2.add(Arrays.asList(13,1));
        seg2.add(Arrays.asList(13,2));
        seg2.add(Arrays.asList(13,3));

        boolean matchFound = seg1.stream()
            .anyMatch(innerList1 -> seg2.stream()
                .anyMatch(innerList1::equals));

        System.out.println(matchFound);
    }
}
