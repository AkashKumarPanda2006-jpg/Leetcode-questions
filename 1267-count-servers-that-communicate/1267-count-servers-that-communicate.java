
class Solution {
    public int countServers(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // First pass: count servers in every row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int ans = 0;

        // Second pass: count communicating servers
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 &&
                    (rowCount[i] > 1 || colCount[j] > 1)) {

                    ans++;
                }
            }
        }

        return ans;
    }
}
