package moderate;

import java.util.*;
import java.util.stream.Collectors;

public class englishInt {
    // the cycle repeats for every 1000 place
    // 1 = one... 9 = nine;
    // special cases 11=eleven.. 19=nineteen
    // 2 at 10th = twenty...9 at 10th = ninty
    // at 100th place is something hundred(i.e. 393) -> check how many digits(3) the hundred place check index 0 = 3 hundred, check index = 2 (index2==1)? (special case): thirty, index 3 = three;

    // start from the back or front?
    // back we can easily append the hundreds, thousands
    // front is one go no need to reverse

    static Map<Character, String> numMap = Map.of(
        '0', "",
        '1', "one",
        '2', "two",
        '3', "three",
        '4', "four",
        '5', "five",
        '6', "six",
        '7', "seven",
        '8', "eight",
        '9', "nine"
    );

    static Map<String, String> specialMap = Map.of(
        "10", "ten",
        "11", "eleven",
        "12", "twelve",
        "13", "thirteen",
        "14", "fourteen",
        "15", "fifteen",
        "16", "sixteen",
        "17", "seventeen",
        "18", "eighteen",
        "19", "nineteen"
    );

    static Map<Character, String> normalMap = Map.of(
        '0', "",
        '2', "twenty",
        '3', "thirty",
        '4', "forty",
        '5', "fifty",
        '6', "sixty",
        '7', "seventy",
        '8', "eighty",
        '9', "ninety"
    );
    static Map<Integer, String> bigMap = Map.of(
        9,"billion",
        6,"million",
        3,"thousand"
    );
    public static void main(String[] args) {
        int n = 191;
        List<Character> num = Integer.toString(n).chars()  // Convert the number to a stream of int values (ASCII)
                .mapToObj(c -> (char) c)          // Convert each int to a Character
                .collect(Collectors.toList());
        System.out.println(rec(num));

    }
    public static String generateThree(List<Character> nums) {
        if (nums.isEmpty()) return "";
        if (nums.size()==1) {
            if (nums.get(0)=='0') return "Zero";
            return numMap.get(nums.get(0));
        }
        if(nums.size()==2) {
            return (nums.get(0)=='1')? specialMap.get(Character.toString(nums.get(1))+Character.toString(nums.get(2))): normalMap.get(nums.get(0))+" "+numMap.get(nums.get(1));
        }
        if (nums.get(1)=='1') return numMap.get(nums.get(0))+" hundred "+ specialMap.get(Character.toString(nums.get(1))+Character.toString(nums.get(2)));
        return numMap.get(nums.get(0))+" hundred "+normalMap.get(nums.get(1))+" "+numMap.get(nums.get(2));

    }
    public static String rec(List<Character> num) {
        if(num.size()<=3){
            return generateThree(num);
        }
        int val = num.size()/3;
        return generateThree(num.subList(0, val))+ " " +bigMap.get(num.size()-val)+" "+ rec(num.subList(val, num.size()));
    }
}
