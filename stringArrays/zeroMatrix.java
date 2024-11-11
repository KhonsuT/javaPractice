package stringArrays;
import java.util.*;
public class zeroMatrix {
    public static void main(String[] args) {
        int[][] mat = {{1,0,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println(Arrays.deepToString(zeroMatrixConverter(mat)));

    }

    static int[][] zeroMatrixConverter(int[][] mat) {
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i<mat[0].length; i++) {
            if (mat[0][i] == 0) firstRow = true;
        }
        for (int i = 0; i<mat.length; i++) {
            if (mat[i][0] == 0) firstCol = true;
        }
        for (int i = 1; i<mat.length; i++) {
            for (int j = 1; j<mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    mat[0][j] = 0;
                    mat[i][0] = 0;
                }
            }
        }

        for (int i = 0; i<mat.length; i++) {
            if (mat[i][0] == 0) {
                for (int j = 0; j<mat[0].length; j++) {
                    mat[i][j] = 0;
                }
            }
        }
        
        for (int i = 0; i<mat[0].length; i++) {
            if (mat[0][i] == 0) {
                for (int j = 0; j<mat.length; j++) {
                    mat[j][i] = 0;
                }
            }
        }

        if (firstRow) {
            for (int i = 0; i<mat[0].length; i++) {
                mat[0][i] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i<mat.length; i++) {
                mat[i][0] = 0;
            }
        }
        return mat;
    }


}