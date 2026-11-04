
import java.util.*;

class Solution {

    private static final long INF = Long.MAX_VALUE / 4;

    // Ordered multiset with its size and sum.
    private static class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int value) {
            map.merge(value, 1, Integer::sum);
            size++;
            sum += value;
        }

        void remove(int value) {
            int count = map.get(value);

            if (count == 1) {
                map.remove(value);
            } else {
                map.put(value, count - 1);
            }

            size--;
            sum -= value;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }

        int removeFirst() {
            int value = first();
            remove(value);
            return value;
        }

        int removeLast() {
            int value = last();
            remove(value);
            return value;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Compute the cost of every length-x window.
        long[] cost = new long[n - x + 1];

        MultiSet lower = new MultiSet();
        MultiSet upper = new MultiSet();

        for (int i = 0; i < n; i++) {
            int value = nums[i];

            if (lower.size == 0 || value <= lower.last()) {
                lower.add(value);
            } else {
                upper.add(value);
            }

            // Remove the element leaving the window.
            if (i >= x) {
                int outgoing = nums[i - x];

                if (lower.map.containsKey(outgoing)) {
                    lower.remove(outgoing);
                } else {
                    upper.remove(outgoing);
                }
            }

            // lower should contain ceil(windowSize / 2) elements.
            int target = Math.min(x, i + 1);
            target = (target + 1) / 2;

            while (lower.size > target) {
                upper.add(lower.removeLast());
            }

            while (lower.size < target) {
                lower.add(upper.removeFirst());
            }

            if (i >= x - 1) {
                int start = i - x + 1;
                long median = lower.last();

                cost[start] =
                    median * lower.size - lower.sum
                    + upper.sum - median * upper.size;
            }
        }

        // Step 2: DP over prefix length and number of windows.
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;

            for (int j = 1; j <= k; j++) {
                // Option 1: Skip nums[i - 1].
                dp[i][j] = dp[i - 1][j];

                // Option 2: Select the window ending at i - 1.
                if (i >= x && dp[i - x][j - 1] != INF) {
                    long take =
                        dp[i - x][j - 1] + cost[i - x];

                    dp[i][j] = Math.min(dp[i][j], take);
                }
            }
        }

        return dp[n][k];
    }
}
