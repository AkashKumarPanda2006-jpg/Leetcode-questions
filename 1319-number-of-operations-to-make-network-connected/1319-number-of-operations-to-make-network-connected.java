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
class Solution { 
    public int makeConnected(int n, int[][] connections) {
        
        DisjointSet ds = new DisjointSet(n);

        int m = connections.length ; 
        
        int cntExtra = 0 ;
        for(int i=0 ; i<m; i++){
            int u = connections[i][0] ;
            int v = connections[i][1] ;

            if(ds.findUPar(u) == ds.findUPar(v)) cntExtra++ ;
            else{
                ds.uniounBySize(u,v) ;
            }
        }

        int cnt = 0 ;
        for(int i=0 ; i<n ; i++){
            if(ds.parent.get(i) == i) cnt++ ;
        }

       int ans = cnt-1 ; 
       if(cntExtra >= ans) return ans ;

       return -1 ;

        
    
        
    }
}