class Solution {

    static class Node {
        int product;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;
    Node[] tree;

    private Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of the complete segment
        res.product = (int) ((long) left.product * right.product % k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Prefixes that start in left and continue into right
        for (int r = 0; r < k; r++) {

            int newRemainder =
                    (int) ((long) left.product * r % k);

            res.cnt[newRemainder] += right.cnt[r];
        }

        return res;
    }

    private void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            int value = nums[l] % k;

            tree[node].product = value;
            tree[node].cnt[value] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    private void update(
            int node,
            int l,
            int r,
            int index,
            int value) {

        if (l == r) {

            value %= k;

            tree[node].product = value;

            for (int i = 0; i < k; i++) {
                tree[node].cnt[i] = 0;
            }

            tree[node].cnt[value] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    private Node query(
            int node,
            int l,
            int r,
            int ql,
            int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(
                node * 2,
                l,
                mid,
                ql,
                qr
        );

        Node right = query(
                node * 2 + 1,
                mid + 1,
                r,
                ql,
                qr
        );

        return merge(left, right);
    }

    public int[] resultArray(
            int[] nums,
            int k,
            int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        buildTreeNodes(1, 0, n - 1);

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            update(
                    1,
                    0,
                    n - 1,
                    index,
                    value
            );

            // We need all prefixes of [start ... n-1]
            Node res = query(
                    1,
                    0,
                    n - 1,
                    start,
                    n - 1
            );

            result[i] = res.cnt[x];
        }

        return result;
    }

    private void buildTreeNodes(int node, int l, int r) {

        tree[node] = new Node(k);

        if (l == r) {
            return;
        }

        int mid = l + (r - l) / 2;

        buildTreeNodes(node * 2, l, mid);
        buildTreeNodes(node * 2 + 1, mid + 1, r);
    }
}