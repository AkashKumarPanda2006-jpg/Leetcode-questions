
import java.util.*;

class Solution {

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        // Step 1: Construct suffix array
        int[] sa = buildSuffixArray(nums);

        // Step 2: Construct inverse suffix array
        int[] rank = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            rank[sa[i]] = i;
        }

        // Step 3: Construct LCP array using Kasai's algorithm
        int[] lcp = new int[n + 1];

        int h = 0;

        for (int i = 0; i < n; i++) {

            int r = rank[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n
                    && nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        // Step 4: Find the smallest unique subarray
        int ans = n;

        for (int i = 0; i < n; i++) {

            int r = rank[i];

            int leftLCP = lcp[r];
            int rightLCP = (r + 1 <= n) ? lcp[r + 1] : 0;

            int maxLCP = Math.max(leftLCP, rightLCP);

            int requiredLength = maxLCP + 1;

            if (requiredLength <= n - i) {
                ans = Math.min(ans, requiredLength);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {

        int n = nums.length;
        int m = n + 1;

        // Append a unique smallest sentinel (0).
        // Original nums[i] values are positive.
        int[] a = new int[m];

        int alphabet = m;

        for (int i = 0; i < n; i++) {
            a[i] = nums[i] + 1;
            alphabet = Math.max(alphabet, a[i] + 1);
        }

        a[n] = 0;

        int[] p = new int[m];
        int[] c = new int[m];
        int[] cnt = new int[Math.max(alphabet, m)];

        // Initial counting sort
        for (int x : a) {
            cnt[x]++;
        }

        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = m - 1; i >= 0; i--) {
            p[--cnt[a[i]]] = i;
        }

        // Initial equivalence classes
        c[p[0]] = 0;
        int classes = 1;

        for (int i = 1; i < m; i++) {

            if (a[p[i]] != a[p[i - 1]]) {
                classes++;
            }

            c[p[i]] = classes - 1;
        }

        int[] pn = new int[m];
        int[] cn = new int[m];

        // Doubling algorithm
        for (int len = 1; len < m; len *= 2) {

            // Shift suffix positions
            for (int i = 0; i < m; i++) {
                pn[i] = p[i] - len;

                if (pn[i] < 0) {
                    pn[i] += m;
                }
            }

            // Counting sort by equivalence class
            Arrays.fill(cnt, 0);

            for (int x : pn) {
                cnt[c[x]]++;
            }

            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = m - 1; i >= 0; i--) {
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            // Recalculate equivalence classes
            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < m; i++) {

                int cur = p[i];
                int prev = p[i - 1];

                int midCur = (cur + len) % m;
                int midPrev = (prev + len) % m;

                if (c[cur] != c[prev]
                        || c[midCur] != c[midPrev]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == m) {
                break;
            }
        }

        return p;
    }
}
