class Solution {
    public int maxDepth(String s) {
        int n = s.length();
        int ptr = 0 ;
        int max = 0 ;

        for(int i=0 ; i<n ; i++){
            if(s.charAt(i) == '('){
                ptr++;
                max = Math.max(max,ptr);
            }else if(s.charAt(i) == ')'){
                ptr--;
            }
        }
        
        return max ;
    }
}