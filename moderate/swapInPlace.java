package moderate;

public class swapInPlace {
    public static void main(String[] args) {
        int num1 = -22;
        int num2 = -19;

        num1 = num2-num1;
        num2 = num2-num1;
        num1 = num2+num1;

        System.out.println(num1+","+num2+",");
    }
}
