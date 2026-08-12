class Solution {
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
            }

            //Both the equal and greater than case handled by this if 
            else {
                parent.set(ulp_v,ulp_u);
                size.set(ulp_u , size.get(ulp_u) + size.get(ulp_v)) ;
            }
        }
    }
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length ;

        Solution solution = new Solution();
        Solution.DisjointSet ds = solution.new DisjointSet(n);

        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<n ; j++){
                if(isConnected[i][j] == 1){
                    ds.uniounBySize(i,j) ;
                }
            }
        }

        int cnt = 0 ;
        for(int i=0 ; i<n ; i++){
            if(ds.parent.get(i) == i) cnt++ ;
        }

        return cnt ;

        
    }
}