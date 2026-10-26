
import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        ArrayDeque<int[]> stack = new ArrayDeque<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] <= 0) {
                    continue;
                }

                long sum = 0;

                stack.push(new int[]{r, c});

                while (!stack.isEmpty()) {
                    int[] cell = stack.pop();
                    int x = cell[0];
                    int y = cell[1];

                    if (grid[x][y] <= 0) {
                        continue;
                    }

                    sum += grid[x][y];
                    grid[x][y] = 0; // Mark visited.

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dr[d];
                        int ny = y + dc[d];

                        if (nx >= 0 && nx < m &&
                            ny >= 0 && ny < n &&
                            grid[nx][ny] > 0) {
                            stack.push(new int[]{nx, ny});
                        }
                    }
                }

                if (sum % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}

