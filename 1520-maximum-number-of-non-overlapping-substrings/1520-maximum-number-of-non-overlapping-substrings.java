import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create a valid interval starting
        // at the first occurrence of each character.
        for (int c = 0; c < 26; c++) {

            if (first[c] == n)
                continue;

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            for (int i = l; i <= r; i++) {

                int current = s.charAt(i) - 'a';

                // This character appeared before l,
                // so we cannot create a valid substring
                // starting at l.
                if (first[current] < l) {
                    valid = false;
                    break;
                }

                // We must include all occurrences
                // of this character.
                r = Math.max(r, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position.
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();

        int previousEnd = -1;

        // Greedily select the interval with
        // earliest ending position.
        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > previousEnd) {

                result.add(s.substring(l, r + 1));

                previousEnd = r;
            }
        }

        return result;
    }
}