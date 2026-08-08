public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindromes (e.g., "racecar")
            int len1 = expandAroundCenter(s, i, i);
            
            // Case 2: Even-length palindromes (e.g., "aabbaa")
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get the maximum length found at the current center
            int maxLen = Math.max(len1, len2);
            
            // Update the boundaries if a longer palindrome is found
            if (maxLen > (end - start)) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }
        
        // Return the slice from start to end (end is exclusive)
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        // Expand outwards as long as characters match and indices are in bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the valid palindromic substring found
        return right - left - 1;
    }
}
