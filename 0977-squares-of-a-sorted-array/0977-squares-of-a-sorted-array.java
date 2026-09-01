class Solution {
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;
        int result[] = new int[n] ;

        int left = 0 ;
        int right = n - 1 ;

        for(int i= n-1 ; i >= 0 ; i--){
            int leftans = nums[left] * nums[left] ;
            int rightans =  nums[right] * nums[right] ;

            if(leftans > rightans){
                result[i] = leftans ;
                left++;
              
            }else{
                result[i] = rightans ;
                right--;

            }
        }
        
        return result ;
    }
}