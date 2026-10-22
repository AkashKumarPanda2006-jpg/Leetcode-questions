
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        // Check whether the sequence increases cyclically
        // when traversing from index zero in the given direction.
        int best = Integer.MAX_VALUE;

        for (int step : new int[]{1, -1}) {
            boolean valid = true;

            for (int i = 1; i < n; i++) {
                int prev = (zero + (i - 1) * step + n) % n;
                int curr = (zero + i * step + n) % n;

                if (nums[prev] > nums[curr]) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                continue;
            }

            if (step == 1) {
                best = Math.min(best, zero);
                best = Math.min(best, n - zero + 2);
            } else {
                best = Math.min(best, zero + 2);
                best = Math.min(best, n - zero);
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}
