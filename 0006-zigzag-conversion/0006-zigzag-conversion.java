public class Solution {
    public String convert(String s, int numRows) {
        // Base case: if numRows is 1 or string is too short, no zigzag is possible
        if (numRows <= 1 || s.length() <= numRows) {
            return s;
        }

        // Initialize a StringBuilder for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        int direction = 1; // 1 means moving down, -1 means moving up

        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Reverse direction when hitting the top or bottom row
            if (currentRow == 0) {
                direction = 1; // Go down
            } else if (currentRow == numRows - 1) {
                direction = -1; // Go up
            }

            // Move to the next row
            currentRow += direction;
        }

        // Combine all rows into a single final string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
