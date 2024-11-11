package dp;

public class magicNumber {
    public static void main(String[] args) {
        int[][] arr = {
            {-12, -9, -5, -2, 0, 1, 4, 7, 10, 15, 18, 21, 23, 26, 30, 32, 35, 40},
            {55, 40, 28, 19, 12, 8, 5, 3, 2, 1, 0, -2, -6, -9, -12, -14, -20, -25},
            {-100, -85, -65, -50, -30, -10, 0, 2, 7, 15, 22, 29, 35, 40, 45, 52, 58, 60}
        };
        for (int[] a: arr) {
            System.out.println(findMagicNum(a));
        }
    }

    static int findMagicNum(int[] arr) {
        int p = (arr.length-1)/2;
        boolean[] visited = new boolean[arr.length];
        if (arr[p] == p) return p;

        while (p>=0 && p<arr.length) {
            if(visited[p]) break;
            if (arr[p]>p) {
                visited[p]=true;
                p--;
            }
            else if(arr[p]<p) {
                visited[p]=true;
                p++;
            }
            else return p;
        }
        return -1;
    }

}
