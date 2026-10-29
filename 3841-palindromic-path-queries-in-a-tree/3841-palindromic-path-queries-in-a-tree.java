
import java.util.*;

class Solution {
    private int n, LOG;
    private List<Integer>[] graph;
    private int[] tin, tout, depth, parent;
    private int[][] up;
    private Fenwick bit;

    private static class Fenwick {
        private final int[] tree;

        Fenwick(int n) {
            tree = new int[n + 2];
        }

        void add(int i, int value) {
            while (i < tree.length) {
                tree[i] ^= value;
                i += i & -i;
            }
        }

        int query(int i) {
            int result = 0;
            while (i > 0) {
                result ^= tree[i];
                i -= i & -i;
            }
            return result;
        }

        void rangeXor(int left, int right, int value) {
            add(left + 1, value);
            add(right + 2, value);
        }

        int pointQuery(int index) {
            return query(index + 1);
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        up = new int[LOG][n];

        Arrays.fill(parent, -1);

        // Iterative DFS to obtain Euler order and parents.
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;

            for (int v : graph[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            }
        }

        // Compute subtree sizes in reverse order.
        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
        }

        // Binary lifting table.
        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u];
        }

        for (int k = 1; k < LOG; k++) {
            for (int u = 0; u < n; u++) {
                up[k][u] = up[k - 1][up[k - 1][u]];
            }
        }

        char[] chars = s.toCharArray();

        // XOR mask from root to each node.
        int[] prefix = new int[n];

        for (int u : order) {
            prefix[u] = 1 << (chars[u] - 'a');

            if (u != 0) {
                prefix[u] ^= prefix[parent[u]];
            }
        }

        bit = new Fenwick(n);

        // Initialize the Fenwick tree.
        for (int u = 0; u < n; u++) {
            bit.rangeXor(tin[u], tin[u], prefix[u]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                if (delta != 0) {
                    bit.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int maskU = bit.pointQuery(tin[u]);
                int maskV = bit.pointQuery(tin[v]);
                int ancestorMask =
                        1 << (chars[ancestor] - 'a');

                int pathMask = maskU ^ maskV ^ ancestorMask;

                // A palindrome is possible if at most
                // one character occurs an odd number of times.
                answer.add(
                    (pathMask & (pathMask - 1)) == 0
                );
            }
        }

        return answer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }
}
