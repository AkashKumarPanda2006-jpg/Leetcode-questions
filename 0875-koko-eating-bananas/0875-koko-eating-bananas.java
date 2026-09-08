class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canEatInTime(piles, mid, h)) {
                result = mid;
                right = mid - 1; 
            } else {
                left = mid + 1; 
            }
        }
        
        return result;
    }
    
    private boolean canEatInTime(int[] piles, int k, int h) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile + k - 1) / k; 
        }
        return totalHours <= h;
    }
}
