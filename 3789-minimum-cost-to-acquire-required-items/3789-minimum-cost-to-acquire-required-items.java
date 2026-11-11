
class Solution {
    public long minimumCost(
        int cost1,
        int cost2,
        int costBoth,
        int need1,
        int need2
    ) {

        // Strategy A: Buy individual items
        long a = (long) need1 * cost1
               + (long) need2 * cost2;

        // Strategy B: Buy only type 3 items
        long b = (long) Math.max(need1, need2) * costBoth;

        // Strategy C: Buy common requirements using type 3
        int common = Math.min(need1, need2);

        long c = (long) common * costBoth
               + (long) (need1 - common) * cost1
               + (long) (need2 - common) * cost2;

        return Math.min(a, Math.min(b, c));
    }
}

