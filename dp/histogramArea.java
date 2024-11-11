package dp;

import java.util.*;
class histogramArea {
    public int maximalRectangle(char[][] matrix) {
        int[] histogram = new int[matrix[0].length];
        int maxArea = 0;
        for (char[] row : matrix) {
            for (int i = 0; i< row.length; i++) {
                histogram[i] = (row[i]=='0')? 0: histogram[i]+Character.getNumericValue(row[i]);
            }
            System.out.println(Arrays.toString(histogram));
            maxArea = Math.max(maxArea, largestRectangle(histogram));
        }
        return maxArea;
    }

    public int largestRectangle(int[] heights) {
        int max = 0;
        Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            // When current height is smaller than the height at the top of the stack
            while (!stack.isEmpty() && stack.peek().get(1) > heights[i]) {
                List<Integer> lastE = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek().get(0) - 1;
                max = Math.max(max, width * lastE.get(1));
            }
            stack.push(Arrays.asList(i, heights[i]));
        }
        // Process remaining elements in the stack
        while (!stack.isEmpty()) {
            List<Integer> lastE = stack.pop();
            int width = stack.isEmpty() ? heights.length : heights.length - stack.peek().get(0) - 1;
            max = Math.max(max, width * lastE.get(1));
        }
        return max;
    }

}
