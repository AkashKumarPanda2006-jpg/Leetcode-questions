class Solution {
    public int singleNumber(int[] nums) {
        
        int result = 0;
        
        for (int num : nums) {

            result ^= num; // Bitwise XOR operation = number^0 = number 
        }
        
        return result;
    }
}

        
