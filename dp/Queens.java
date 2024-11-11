package dp;

import java.util.*;

public class Queens {
    static int[][] moves = {
        {1, 0}, {0, 1}, {0, -1}, {-1, 0}, // horizontal and vertical directions
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // diagonal directions
    };

    public static void main(String[] args) {
        int[][] board = new int[8][8];
        List<List<int[]>> result = new ArrayList<>();
        List<int[]> curPath = new ArrayList<>();
        
        // Recursively solve the board with backtracking
        rec(board, curPath, 0, result);
        
        // Print the valid solutions
        for (List<int[]> solution : result) {
            // Reset the board for each solution
            for (int[] position : solution) {
                board[position[0]][position[1]] = 1; // Mark the queen on the board
            }
            // Print the board configuration
            for (int[] row : board) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            // Reset the board for the next solution
            for (int[] position : solution) {
                board[position[0]][position[1]] = 0;
            }
        }
    }

    static void rec(int[][] board, List<int[]> curPath, int queensPlaced, List<List<int[]>> result) {
        // If all queens are placed, store the result
        if (queensPlaced == 8) {
            result.add(new ArrayList<>(curPath));
            return;
        }

        // Try placing queens in all columns of the current row (queensPlaced represents the current row)
        for (int col = 0; col < 8; col++) {
            int row = queensPlaced;

            // Only place queen if it is safe
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place the queen
                curPath.add(new int[]{row, col}); // Add this position to the current path

                // Recurse to place the next queen in the next row
                rec(board, curPath, queensPlaced + 1, result);

                // Backtrack: remove the queen and try the next column
                board[row][col] = 0;
                curPath.remove(curPath.size() - 1); // Remove last position from path
            }
        }
    }

    // Check if placing a queen at position (row, col) is safe
    static boolean isSafe(int[][] board, int row, int col) {
        // Check the column and diagonals for conflicts
        for (int i = 0; i < row; i++) {
            // Check if there's a queen in the same column
            if (board[i][col] == 1) {
                return false;
            }
            // Check diagonals
            if (col - (row - i) >= 0 && board[i][col - (row - i)] == 1) {
                return false; // Diagonal top-left
            }
            if (col + (row - i) < 8 && board[i][col + (row - i)] == 1) {
                return false; // Diagonal top-right
            }
        }
        return true; // No conflicts, so it's safe
    }
}
