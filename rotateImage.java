import java.util.*;
public class rotateImage{
    public static void main (String[] args){
        int[][] img = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] rotated = {{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}};
        int[][] res = rotateImage(img);
        System.out.println(Arrays.deepToString(res));
        System.out.println(Arrays.deepEquals(res,rotated));
    }

    static int[][] rotateImage(int[][] img) {
        for (int i = 0; i<img.length; i++) {
            img[i] = invertArray(img[i]);
        }
        System.out.println("Inverted:"+ Arrays.deepToString(img));
        int n = img.length-1;
        for (int i = 0; i<img.length;i++) {
            for (int j = i; j<img.length;j++) {
                int[] l = {n-j, i};
                int[] r = {n-i, j};
                int temp = img[l[0]][l[1]];
                img[l[0]][l[1]] = img[r[0]][r[1]];
                img[r[0]][r[1]] = temp;
            }
        }
        return img;
    }  

    static int[] invertArray(int[] arr) {
        int l = 0;
        int r = arr.length-1;
        while (l<r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
        return arr;
    }

}