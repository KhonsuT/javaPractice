package moderate;

public class factorialZero {
    public static void main(String[] args) {
        int n = 45;
        int fives = 0;
        for (int i = 0; i <= n; i++){
            if(i%5==0) fives++;
        }
        System.out.println(fives);

    }
}
