package dp;

public class towerOfHanoi {
    public static void main(String[] args) {
        int n = 6;
        char src = 'A';
        char buf = 'B';
        char des = 'C';
        rec(n,src,buf,des);
    }

    static void rec(int n, char src, char buf, char des) {
        if (n==1) {
            System.out.println(n+" from " + src + " to " + des);
            return;
        }

        rec(n-1,src,des,buf);

        System.out.println(n+" from " + src + " to " + des);

        rec(n-1,buf,src,des);
        
    }

    
}
