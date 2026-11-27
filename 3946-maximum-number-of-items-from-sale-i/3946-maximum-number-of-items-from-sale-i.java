
import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] dp = new int[budget + 1];

        // Exact-spending DP: initially unreachable
        Arrays.fill(dp, -1_000_000);
        dp[0] = 0;

        int minPrice = Integer.MAX_VALUE;

        // Step 1: Calculate the special gain for each item
        for (int i = 0; i < n; i++) {

            int factor = items[i][0];
            int price = items[i][1];

            minPrice = Math.min(minPrice, price);

            int freeCount = 0;

            for (int j = 0; j < n; j++) {

                if (i != j && items[j][0] % factor == 0) {
                    freeCount++;
                }
            }

            int gain = 1 + freeCount;

            // Step 2: 0/1 Knapsack
            for (int j = budget; j >= price; j--) {

                if (dp[j - price] != -1_000_000) {

                    dp[j] = Math.max(
                        dp[j],
                        dp[j - price] + gain
                    );
                }
            }
        }

        // Step 3: Spend the remaining budget
        // on the cheapest item
        int ans = 0;

        for (int spent = 0; spent <= budget; spent++) {

            if (dp[spent] == -1_000_000) {
                continue;
            }

            int remaining = budget - spent;

            int extraItems = remaining / minPrice;

            ans = Math.max(ans, dp[spent] + extraItems);
        }

        return ans;
    }
}
