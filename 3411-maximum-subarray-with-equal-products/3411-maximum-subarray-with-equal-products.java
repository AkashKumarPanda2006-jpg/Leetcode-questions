
class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (nums[i] == 1) {
                ans = Math.max(ans, 1);
            }

            // Every subarray of length 2 is valid
            if (i + 1 < n) {
                ans = Math.max(ans, 2);
            }

            // Check whether the first two elements
            // are coprime before extending to length 3
            if (i + 2 >= n || gcd(nums[i], nums[i + 1]) != 1) {
                continue;
            }

            for (int j = i + 2; j < n; j++) {

                boolean valid = true;

                // New element must be coprime
                // with every previous element
                for (int k = i; k < j; k++) {

                    if (gcd(nums[k], nums[j]) != 1) {
                        valid = false;
                        break;
                    }
                }

                if (!valid) {
                    break;
                }

                ans = Math.max(ans, j - i + 1);
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
