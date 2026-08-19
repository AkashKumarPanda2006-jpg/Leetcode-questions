class DisjointSet{
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
    public int removeStones(int[][] stones) {
        int n = stones.length ; 
        int maxRow = 0;
        int maxCol = 0;

        for(int i=0 ; i<n ; i++){
            maxRow = Math.max(maxRow , stones[i][0]);
            maxCol = Math.max(maxCol , stones[i][1]);
        }

        DisjointSet ds = new DisjointSet(maxRow  + maxCol + 2) ;
        HashMap<Integer,Integer> stoneNodes = new HashMap<>();
        for(int i=0 ; i<n ; i++){
            int nodeRow = stones[i][0] ;
            int nodeCol = stones[i][1] + maxRow + 1 ;
            ds.uniounBySize(nodeRow,nodeCol);
            stoneNodes.put(nodeRow,1);
            stoneNodes.put(nodeCol,1) ;

        }

        int cnt = 0;
        for(Map.Entry<Integer,Integer> it : stoneNodes.entrySet()){
            if(ds.findUPar(it.getKey()) == it.getKey()) cnt ++ ; 
        }
        
        return n - cnt ; 
    }
}