class Solution {
    public int countCommas(int n) {
        int commas = 0;
        int p = 1000 ;

        while(p <= n){
            commas += (n - p + 1);
            p *= 1000 ;
        }

        return commas ; 
    }
}