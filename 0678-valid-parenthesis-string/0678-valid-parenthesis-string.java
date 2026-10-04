class Solution {
    public boolean checkValidString(String s) {

        int minBalance = 0;
        int maxBalance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minBalance++;
                maxBalance++;
            }
            else if (ch == ')') {
                minBalance--;
                maxBalance--;
            }
            else { // '*'

                // Treat '*' as ')'
                minBalance--;

                // Treat '*' as '('
                maxBalance++;
            }

            // We can never have a negative minimum balance
            minBalance = Math.max(0, minBalance);

            // Even the maximum possible balance is negative
            if (maxBalance < 0) {
                return false;
            }
        }

        return minBalance == 0;
    }
}