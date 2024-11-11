package dp;

import java.util.ArrayList;
import java.util.List;

public class coins {
    static int[] options = {1,5,10,25};
    public static void main(String[] args) {
        int n = 24;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curPath = new ArrayList<>();
        int index = 0;
        coin(n,curPath, result,index);
        for (List<Integer> i: result) {
            System.out.println(i);
        }
    }

    static void coin(int n, List<Integer> curPath, List<List<Integer>> result,int index) {
        if (n == 0) {
            result.add(new ArrayList<>(curPath));
        }
        if(n<0) return;

        for (int i = index; i<options.length;i++) {
            curPath.add(options[i]);
            coin(n-options[i],curPath,result,i);
            curPath.remove(curPath.size()-1);
        }
    }
}
