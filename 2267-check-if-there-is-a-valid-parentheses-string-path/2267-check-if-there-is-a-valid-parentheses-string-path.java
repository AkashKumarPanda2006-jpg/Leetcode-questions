class Solution {

    private int m, n;
    private char[][] grid;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // Path length must be even for a valid parentheses string
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // A valid path cannot start with ')'
        if (grid[0][0] == ')') {
            return false;
        }

        memo = new Boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {

        // Add current character
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // More closing brackets than opening brackets
        if (balance < 0) {
            return false;
        }

        // Balance can never exceed the remaining possible path length
        int remaining = (m - 1 - r) + (n - 1 - c);

        if (balance > remaining) {
            return false;
        }

        // Destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean result = false;

        // Move down
        if (r + 1 < m) {
            result = dfs(r + 1, c, balance);
        }

        // Move right
        if (!result && c + 1 < n) {
            result = dfs(r, c + 1, balance);
        }

        return memo[r][c][balance] = result;
    }
}