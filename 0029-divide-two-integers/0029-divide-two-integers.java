class Solution {
    public int divide(int dividend, int divisor) {
        // Handle edge case for integer overflow: -2^31 / -1 = 2^31 (exceeds Integer.MAX_VALUE)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the final quotient
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to long to prevent overflow during absolute value conversion
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int quotient = 0;

        // Perform bitwise exponential subtraction
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            int shiftCount = 0;

            // Find the highest multiple of divisor using left shift (equivalent to multiplying by 2)
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                shiftCount++;
            }

            // Deduct the largest shifted divisor from dividend
            absDividend -= tempDivisor;

            // Add the corresponding binary power (2^shiftCount) to the quotient
            quotient += (1 << shiftCount);
        }

        return isNegative ? -quotient : quotient;
    }
}
