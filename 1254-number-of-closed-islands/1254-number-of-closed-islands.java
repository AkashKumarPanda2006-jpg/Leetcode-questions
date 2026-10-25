
class Solution {
    private int m, n;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, int r, int c) {
        // Reaching outside the grid means the island is open.
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return false;
        }

        // Water or already visited land.
        if (grid[r][c] == 1) {
            return true;
        }

        // Mark this land cell as visited.
        grid[r][c] = 1;

        // Explore all four directions.
        boolean up = dfs(grid, r - 1, c);
        boolean down = dfs(grid, r + 1, c);
        boolean left = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);

        return up && down && left && right;
    }
}
