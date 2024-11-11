package dp;

import java.util.*;

public class paintFill {
    private static int[][] moves = {
        {1,0},// down
        {-1,0}, // up
        {0,1}, // right
        {0,-1} // left
    };
    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0},
            {1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1},
            {1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1},
            {0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0}
        };
        bfs(grid, new int[]{0,0}, 3);
        for (int[] row: grid) {
            System.out.println(Arrays.toString(row));
        }
    }
     
    static void bfs(int[][] grid, int[] start, int target) {
        Queue<int[]> q = new LinkedList<>();

        if (grid.length <= 0 || grid[0].length <= 0) {
            return;
        }

        q.offer(start);
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (grid[cur[0]][cur[1]]==0) {
                grid[cur[0]][cur[1]] = target;
                for (int[] move : moves) {
                    int newRow = cur[0] + move[0];
                    int newCol = cur[1] + move[1];

                    if (!isOutOfBound(newRow,newCol, grid) && grid[newRow][newCol] == 0) {
                        q.offer(new int[]{newRow,newCol});
                    }
                }
            }

        }
    }

    static boolean isOutOfBound(int i, int j, int[][] grid) {
        return (i<0||j<0||i>=grid.length||j>=grid[0].length);
    }
    
}
