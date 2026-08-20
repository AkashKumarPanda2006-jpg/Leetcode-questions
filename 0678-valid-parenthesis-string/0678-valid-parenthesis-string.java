class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                minOpen--; // If '*' is treated as ')'
                maxOpen++; // If '*' is treated as '('
            }

            // If maxOpen is negative, we have too many close brackets.
            // Even if all '*' were open brackets, it wouldn't fix it.
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot drop below 0 because we cannot have a negative 
            // balance of open brackets that actually matters at this point.
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // The string is valid if we can achieve exactly 0 open brackets.
        return minOpen == 0;
    }
}
