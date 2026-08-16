class DisjointSet {
        ArrayList<Integer> parent = new ArrayList<>() ;
        ArrayList<Integer> size = new ArrayList<>() ;

        public DisjointSet(int n) {
            for(int i=0 ; i<n ; i++){
                parent.add(i);
                size.add(1) ;

            }
        }

        public int findUPar(int node ){
            // if node equals parent means it is the ulp 
            if(node == parent.get(node)) return node ;

            //else search for the ulp 
            int ulp = findUPar(parent.get(node));
            parent.set(node,ulp);
            return parent.get(node) ;
        }

        public void uniounBySize(int u , int v){
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if(ulp_u == ulp_v) return ;

            //if u less than p 
            if(size.get(ulp_u) < size.get(ulp_v)){

                parent.set(ulp_u,ulp_v);
                size.set(ulp_v , size.get(ulp_u) + size.get(ulp_v)) ;
            }//Both the equal and greater than case handled by this if 
            else {
                parent.set(ulp_v,ulp_u);
                size.set(ulp_u , size.get(ulp_u) + size.get(ulp_v)) ;
            }
        }
}





class Solution {

    private boolean isValid(int nrow , int ncol , int n) {
    return nrow >= 0 && nrow < n && ncol >=0 && ncol < n ;
    }

    public int largestIsland(int[][] grid) {

        int n = grid.length ; 
        DisjointSet ds = new DisjointSet(n * n) ;

        for(int row = 0 ; row<n ; row++){
            for(int col = 0 ; col < n ; col ++){
                if(grid[row][col] == 0) continue ;
                int dr[] = {-1,0,1,0} ;
                int dc[] = {0,-1,0,1} ;
                for(int ind = 0 ; ind < 4 ; ind ++){
                    int newr = row + dr[ind] ;
                    int newc = col + dc[ind] ;
                    if(isValid(newr , newc , n ) && grid[newr][newc] ==1){
                        int nodeNo = row * n + col ;
                        int adjNodeNo = newr * n + newc ;
                        ds.uniounBySize(nodeNo , adjNodeNo) ;
                    }
                }
            }
        }


        int mx = 0 ;
        for(int row = 0 ; row < n ; row ++){
            for(int col = 0 ; col < n ; col ++) {
                if(grid[row][col] == 1) continue ;
                int dr[] = {-1,0,1,0} ;
                int dc[] = {0,-1,0,1} ;
                HashSet<Integer> components = new HashSet<>() ;
                for(int ind = 0 ; ind < 4 ; ind ++){
                    int newr = row + dr[ind] ;
                    int newc = col + dc[ind] ;
                    if(isValid(newr , newc , n)){
                        if(grid[newr][newc] == 1) {
                            components.add(ds.findUPar(newr * n + newc)) ;
                        }
                    }
                }

                int sizeTotal = 0;
                for(Integer parents : components) {
                    sizeTotal += ds.size.get(parents) ;
                }

                mx = Math.max(mx,sizeTotal + 1) ;


            }
        }

        for(int cellNo = 0 ; cellNo < n*n ; cellNo ++ ){
            mx = Math.max(mx,ds.size.get(ds.findUPar(cellNo))) ;
        }
        
        return mx ;  
    }
}