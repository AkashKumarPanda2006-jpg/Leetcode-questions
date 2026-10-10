class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        long totalDiff = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (totalDiff <= k) {
            return 0L;
        }

        
        int left = 0;
        int right = maxDiff;

        while (left < right) {
            int mid = left + (right - left) / 2;

            long needed = 0;
            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int level = left;
        long used = 0;
        long answer = 0;

        for (int d : diff) {
            if (d > level) {
                used += d - level;
                d = level;
            }
            answer += (long) d * d;
        }

        
        long remaining = k - used;
        answer -= remaining * (2L * level - 1);

        return answer;
    }
}