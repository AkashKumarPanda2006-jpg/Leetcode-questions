import java.util.Arrays;

class Solution {
    public int totalNumbers(int[] digits) {
        
        int[] globalCounts = new int[10];
        for (int d : digits) {
            globalCounts[d]++;
        }
        
        int matchCount = 0;
        
        
        for (int i = 100; i <= 998; i += 2) {
            int d1 = i / 100;       
            int d2 = (i / 10) % 10; 
            int d3 = i % 10;        
            
            
            int[] currentCounts = new int[10];
            currentCounts[d1]++;
            currentCounts[d2]++;
            currentCounts[d3]++;
            
            
            if (globalCounts[d1] >= currentCounts[d1] &&
                globalCounts[d2] >= currentCounts[d2] &&
                globalCounts[d3] >= currentCounts[d3]) {
                matchCount++;
            }
        }
        
        return matchCount;
    }
}
