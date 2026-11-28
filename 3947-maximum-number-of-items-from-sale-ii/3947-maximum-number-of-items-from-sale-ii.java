
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        int[] freq = new int[n + 1];

        // Count factor frequencies and find minimum price.
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            freq[factor]++;
            maxFactor = Math.max(maxFactor, factor);
            minPrice = Math.min(minPrice, price);
        }

        // Calculate the number of possible free items for each factor.
        int[] gain = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            int count = 0;

            for (int multiple = f; multiple <= maxFactor; multiple += f) {
                count += freq[multiple];
            }

            gain[f] = count - 1;
        }

        // Process item types in increasing order of price.
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long answer = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Correct condition: a bonus purchase must cost less
            // than two ordinary purchases of the cheapest item.
            if (price >= 2L * minPrice) {
                break;
            }

            if (gain[factor] == 0) {
                continue;
            }

            long take = Math.min(
                (long) gain[factor],
                remaining / price
            );

            answer += 2L * take;
            remaining -= take * price;
        }

        // Spend the remaining budget on the cheapest item.
        answer += remaining / minPrice;

        return (int) answer;
    }
}
