class Solution {
    public int minMoves(int[] nums) {
        int maximum = nums[0];
        for(int e : nums){
            maximum = Math.max(e,maximum);
        }
        int moves = 0 ;
        for(int e : nums){
            moves += Math.abs(maximum - e);
        }

        return moves ;
        
    }
}