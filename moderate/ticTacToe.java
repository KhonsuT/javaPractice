package moderate;

import java.util.Arrays;

public class ticTacToe {
    public static void main(String[] args) {
        int[][] board = {
                        {2,2,2},
                        {1,1,0},
                        {1,0,1}
                        };
        System.out.println(ifWin(board,new int[]{2,2}));
    }
    public static boolean ifWin(int[][] board, int[] play) {
        //player can be represented by 1 or 2
        // 0 is empty unplaced
        //check the entire row, col, both diagonals
        int player = board[play[0]][play[1]];
        //horizontal&&vertical chekcs
        boolean horizontal = true;
        boolean vertical = true;
        boolean dia1 = true;
        boolean dia2 = true;
        for (int i = 0; i < board.length; i++) {
            if (board[play[0]][i]!=player) {
                horizontal = false;
                break;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][play[1]]!=player) {
                vertical = false;
                break;
            }
            // in the game of ttt there is only two diagon and they are fixed
            // {0,0},{1,1},{2,2}     {0,2},{1,1},{2,0}
        }
        if(Arrays.stream(new int[][]{{0,0},{1,1},{2,2},{0,2},{1,1},{2,0}}).anyMatch(row -> Arrays.equals(row, play))){
            for (int[] point: new int[][]{{0,0},{1,1},{2,2}}) {
                if (board[point[0]][point[1]]!=player) {
                    dia1 = false;
                    break;
                }
            }
            for (int[] point: new int[][]{{0,2},{1,1},{2,0}}) {
                if (board[point[0]][point[1]]!=player) {
                    dia2 = false;
                    break;
                }
            }
        }
        else {
            dia1 = false;
            dia2 = false;
        }
        return horizontal || vertical || dia1 || dia2;
    }
}

