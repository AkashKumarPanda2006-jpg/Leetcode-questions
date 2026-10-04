class Solution {
    public int minOperations(int[] nums) {
        int operations = 0 ;

        for(int i=1 ; i<nums.length ; i++){
            if(nums[i-1] >= nums[i]){
                int difference = (nums[i-1] + 1) - nums[i] ;
                operations += difference ;
                nums[i] = nums[i-1] + 1 ;
            }
        }
        
        return operations ;
    }
}