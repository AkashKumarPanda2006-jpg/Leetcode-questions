
class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] sumSq;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long answer = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];

            // Only these starting positions gain a new distinct value.
            int left = last[x] + 1;
            update(1, 0, n - 1, left, r);

            // Sum of squares for all subarrays ending at r.
            answer = (answer + sumSq[1]) % MOD;

            last[x] = r;
        }

        return (int) answer;
    }

    private void apply(int node, int l, int r, long delta) {
        long len = r - l + 1L;

        // Use the old sum to update the sum of squares.
        sumSq[node] = (
            sumSq[node]
            + 2L * delta % MOD * sum[node] % MOD
            + len % MOD * delta % MOD * delta % MOD
        ) % MOD;

        sum[node] = (
            sum[node] + len % MOD * delta % MOD
        ) % MOD;

        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = l + (r - l) / 2;
        long delta = lazy[node];

        apply(node * 2, l, mid, delta);
        apply(node * 2 + 1, mid + 1, r, delta);

        lazy[node] = 0;
    }

    private void update(
        int node, int l, int r, int ql, int qr
    ) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        pushDown(node, l, r);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        sumSq[node] = (
            sumSq[node * 2] + sumSq[node * 2 + 1]
        ) % MOD;
    }
}
