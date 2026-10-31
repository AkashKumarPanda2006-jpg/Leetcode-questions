
class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Store the actual landing cell as i * n + j.
        // -1 means the move is invalid.
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // Precompute destinations in reverse order.
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                // Attempt to move right.
                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        right[i][j] = i * n + (j + 1);
                    } else {
                        // Hit a mirror: turn down.
                        right[i][j] = down[i][j + 1];
                    }
                } else {
                    right[i][j] = -1;
                }

                // Attempt to move down.
                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        down[i][j] = (i + 1) * n + j;
                    } else {
                        // Hit a mirror: turn right.
                        down[i][j] = right[i + 1][j];
                    }
                } else {
                    down[i][j] = -1;
                }
            }
        }

        // Dynamic programming.
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long ways = dp[i][j];

                if (ways == 0) {
                    continue;
                }

                // Move right (including any mirror reflections).
                int r = right[i][j];

                if (r != -1) {
                    int x = r / n;
                    int y = r % n;

                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }

                // Move down (including any mirror reflections).
                int d = down[i][j];

                if (d != -1) {
                    int x = d / n;
                    int y = d % n;

                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
