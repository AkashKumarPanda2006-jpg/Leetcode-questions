class Solution {
    public boolean checkOverlap(int r, int xc, int yc, int x1, int y1, int x2, int y2) {

        int x = Math.max(x1,Math.min(x2,xc)) - xc ;
        int y = Math.max(y1,Math.min(y2,yc)) - yc ;

        return x*x + y*y <= r*r;
        
    }
}