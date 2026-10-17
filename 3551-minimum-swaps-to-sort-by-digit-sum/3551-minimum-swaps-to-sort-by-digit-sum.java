
import java.util.*;

class Solution {
    private int digitSum(int x) {
        int sum = 0;

        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Store each number with its digit sum.
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = digitSum(nums[i]);
            arr[i][1] = nums[i];
        }

        // Sort by digit sum, then by numerical value.
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Map each number to its target index.
        Map<Integer, Integer> target = new HashMap<>();

        for (int i = 0; i < n; i++) {
            target.put(arr[i][1], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        // Count permutation cycles.
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleSize = 0;

            while (!visited[j]) {
                visited[j] = true;
                cycleSize++;
                j = target.get(nums[j]);
            }

            swaps += cycleSize - 1;
        }

        return swaps;
    }
}
