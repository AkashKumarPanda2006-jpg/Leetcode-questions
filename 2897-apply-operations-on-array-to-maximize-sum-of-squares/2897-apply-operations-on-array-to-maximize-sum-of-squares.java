class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;

        int[] bitCount = new int[31];

        // Count set bits at each position.
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if ((num & (1 << b)) != 0) {
                    bitCount[b]++;
                }
            }
        }

        long answer = 0;

        // Construct k numbers greedily.
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int b = 30; b >= 0; b--) {
                if (bitCount[b] > 0) {
                    value |= (1L << b);
                    bitCount[b]--;
                }
            }

            answer = (answer + value * value) % MOD;
        }

        return (int) answer;
    }
}