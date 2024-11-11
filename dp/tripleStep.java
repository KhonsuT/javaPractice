package dp;
public class tripleStep {
    public static void main(String[] args) {
        int steps = 50;
        long[] memo = new long[steps+1];
        System.out.println(countSteps(steps, memo));
        // s 1 2 3 e
        // [1,2,3]
        // memo  = 0,1,2

    }

    static long countSteps(int steps, long[] memo) {
        //at step 9 it takes one more step, thus 2 and 3 steps False
        if (steps == 0) {
            return 1;
        }
        else if(steps<0) {
            return 0;
        }
        if (memo[steps]!=0) return memo[steps];
        memo[steps] =  countSteps(steps-1, memo) + countSteps(steps-2, memo) + countSteps(steps-3,memo);
        return memo[steps];
    }
}