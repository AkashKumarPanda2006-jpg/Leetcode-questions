class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // Check whether we have a pair of ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    // Insert a ')' to complete the pair
                    insertions++;
                    i++;
                }

                if (open > 0) {
                    open--;
                } else {
                    // Insert a '(' to match this closing pair
                    insertions++;
                }
            }
        }

        // Each unmatched '(' requires two ')'
        return insertions + 2 * open;
    }
}