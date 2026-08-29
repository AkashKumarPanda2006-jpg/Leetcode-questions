class Solution {
    public int numJewelsInStones(String jewels, String stones) {

        boolean Jewels[] = new boolean[128];

        for(char ch : jewels.toCharArray()){
            Jewels[ch] = true ;
        }

        int cnt = 0;
        for(char ch : stones.toCharArray()){
            if(Jewels[ch] == true) cnt ++ ;
        }
        
        return cnt ;
    }
}