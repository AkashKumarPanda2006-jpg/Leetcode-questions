class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous index whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int value = num % k;

            // Subarrays ending at the current index
            long[] newDp = new long[k];

            // Start a new subarray with only nums[i]
            newDp[value]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] == 0) {
                    continue;
                }

                int newRemainder =
                        (int) (((long) r * value) % k);

                newDp[newRemainder] += dp[r];
            }

            // Add all subarrays ending at current index
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            // Current becomes previous for next iteration
            dp = newDp;
        }

        return result;
    }
}