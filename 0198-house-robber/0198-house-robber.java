class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        return f(n-1,dp,nums);
        
    }
    int f(int n ,int dp[],int nums[]){
        if(n==0) return nums[0];
        if(n <0 ) return 0 ;
        if(dp[n] != -1) return dp[n];
        //if pick current element ;
        int pick = nums[n] + f(n-2,dp,nums);
        //if we do not pick the current element 
        int notpick = f(n-1,dp,nums) ;
        return dp[n] = Math.max(pick,notpick);
    }
}