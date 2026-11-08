
class Solution {

    int[] parent;
    int[] size;

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (size[rootA] < size[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        size[rootA] += size[rootB];
    }

    public int countServers(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int total = m * n;

        parent = new int[total];
        size = new int[total];

        for (int i = 0; i < total; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Store the first server found in each row and column
        int[] rowFirst = new int[m];
        int[] colFirst = new int[n];

        java.util.Arrays.fill(rowFirst, -1);
        java.util.Arrays.fill(colFirst, -1);

        // Connect servers sharing the same row or column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    int id = i * n + j;

                    // Union with the first server in this row
                    if (rowFirst[i] != -1) {
                        union(id, rowFirst[i]);
                    } else {
                        rowFirst[i] = id;
                    }

                    // Union with the first server in this column
                    if (colFirst[j] != -1) {
                        union(id, colFirst[j]);
                    } else {
                        colFirst[j] = id;
                    }
                }
            }
        }

        // Count servers belonging to components of size > 1
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    int id = i * n + j;
                    int root = find(id);

                    if (size[root] > 1) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
