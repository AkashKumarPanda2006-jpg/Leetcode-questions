
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        // Maximum of elements before each index.
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        // Minimum of elements from each index onward.
        suffMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        // Count adjacent descents: nums[i - 1] > nums[i].
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int answer = 0;

        // Enumerate divisors of n.
        for (int d = 1; d <= n / d; d++) {
            if (n % d != 0) {
                continue;
            }

            if (isSortable(nums, d, prefMax, suffMin, prefDrop)) {
                answer += d;
            }

            int other = n / d;

            if (other != d &&
                isSortable(nums, other, prefMax, suffMin, prefDrop)) {
                answer += other;
            }
        }

        return answer;
    }

    private boolean isSortable(
            int[] nums,
            int k,
            int[] prefMax,
            int[] suffMin,
            int[] prefDrop) {

        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            // Descents inside this block.
            int drops = prefDrop[end] - prefDrop[start];

            // Include the circular last-to-first comparison.
            if (nums[end] > nums[start]) {
                drops++;
            }

            // This block cannot be rotated into sorted order.
            if (drops > 1) {
                return false;
            }

            // Earlier elements must not exceed any later element.
            if (prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}
