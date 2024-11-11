package dp;

public class recurMultiply {
    public static void main(String[] args) {
        int num1 = -3;
        int num2 = -3;
        System.out.println(rec(num1, num2));
    }

    static int rec(int num1, int num2){
        if (num1>=0) {
            if (num1==1){
                return num2;
            }
            else if (num1 == 0 || num2 == 0) {
                return 0;
            }
            return num2 + rec(num1-1,num2);
        }
        else {
            if (num1==-1){
                return -num2;
            }
            return -num2 + rec(num1+1,num2);
        }
    }
}
