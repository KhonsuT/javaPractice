package dp;

import java.util.*;

public class robotPathFinder {
    private static int[][] moves = {{0,1},{1,0},{-1,0},{0,-1}};
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
        int[] start = {0,0};
        int[] end = {grid.length-1,grid[0].length-1};
        List<int[]> curPath1 = new ArrayList<>();
        List<List<int[]>> result1 = new ArrayList<>();
        List<int[]> curPath2 = new ArrayList<>();
        List<List<int[]>> result2 = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        long startTime = System.currentTimeMillis();
        bfs(grid, start, end, curPath2, result2);
        long endTime = System.currentTimeMillis();
        System.out.println("BFS RunTime:" + (endTime-startTime));
        long startTime2 = System.currentTimeMillis();
        dfs(grid,start,end,curPath1,result1, visited);
        long endTime2 = System.currentTimeMillis();
        System.out.println("DFS RunTime:" + (endTime2-startTime2));

        System.out.println("Paths found: ");
        for (List<int[]> path : result2) {
            for (int[] point : path) {
                System.out.print(Arrays.toString(point) + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int[][] grid, int[] start, int[] end, List<int[]> curPath, List<List<int[]>> result) {
        Queue<List<int[]>> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        curPath.add(start);
        q.offer(new ArrayList<>(curPath));
        visited.add(Arrays.toString(start));
        while (!q.isEmpty()) {
            List<int[]> path = q.poll();
            int[] cur = path.get(path.size()-1);

            if (Arrays.equals(cur, end)) {
                result.add(path);
                continue;
            }

            for (int[] move: moves) {
                int newRow = cur[0]+move[0];
                int newCol = cur[1]+move[1];

                if(!isOutOfBounds(newRow, newCol, grid) && grid[newRow][newCol] == 0 && !visited.contains(Arrays.toString(new int[]{newRow,newCol}))) {
                    visited.add(Arrays.toString(new int[]{newRow,newCol}));

                    List<int[]> newPath = new ArrayList<>(path);
                    newPath.add(new int[]{newRow, newCol});
                    q.offer(newPath);
                }
            }
        }
    }

    static void dfs(int[][] grid, int[] cur, int[] end, List<int[]> curPath, List<List<int[]>> result, boolean[][] visited) {
        if (visited[cur[0]][cur[1]]) return;
        curPath.add(new int[]{cur[0],cur[1]});
        visited[cur[0]][cur[1]] = true;
        if (Arrays.equals(cur, end)) {
            result.add(new ArrayList<>(curPath));
            curPath.remove(curPath.size()-1);
            return;
        }
        else {
            for (int[] move: moves) {
                int newRow = cur[0]+move[0];
                int newCol = cur[1]+move[1];
    
                if (!isOutOfBounds(newRow,newCol,grid)&&grid[newRow][newCol]==0) {
                    dfs(grid, new int[]{newRow, newCol},end,curPath,result,visited);
                }
            }
        }
        
        curPath.remove(curPath.size()-1);
        visited[cur[0]][cur[1]] = false;
    }
    
    static boolean isOutOfBounds(int i,int j, int[][] grid) {
        return (i<0 || j<0 || i>=grid.length || j>=grid[0].length);
    }
}