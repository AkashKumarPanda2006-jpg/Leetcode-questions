class Solution {
    public int[] getConcatenation(int[] nums) {

        int n = nums.length ;
        int newarr [] = new int [2*n] ;

        for(int i=0 ; i<n ; i++){
            newarr[i] = nums[i] ;
            newarr[i+n] = nums[i] ;
        }
        

        return newarr ;
    }
}