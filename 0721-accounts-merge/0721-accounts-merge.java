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
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);

        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(int i=0 ; i<n ; i++){
            for(int j=1 ; j<accounts.get(i).size() ; j++){
                String mail = accounts.get(i).get(j) ; 

                if(map.containsKey(mail) == false) {

                    map.put(mail, i) ;

                }else{

                    ds.uniounBySize(i , map.get(mail)) ; 
                }
            }
        }

        //List to merge the mail according to the names 
        ArrayList<String> mergeMail[] = new ArrayList[n];
        for(int i=0 ; i<n ; i++){
            mergeMail[i] = new ArrayList<String>() ;
        }
        

        for(Map.Entry<String,Integer> it : map.entrySet()){ //for each entry in the map 
            String mail = it.getKey(); // get the mail 
            int node = ds.findUPar(it.getValue()); // get the ultimate parent
            //add 
            mergeMail[node].add(mail) ; // add the mail to the ulp name 
        }

        List<List<String>> ans = new ArrayList<>(); 
        for(int i=0 ; i<n ; i++){
            //if size of mergeMail = 0 ie duplicate account to be merged 
            if(mergeMail[i].size() == 0 ) continue ; 
            //Sort the ith mailaccounts in order 
            Collections.sort(mergeMail[i]);
            List<String> temp = new ArrayList<>() ;
            temp.add(accounts.get(i).get(0)) ; // add the name first to temp list 
            for(String it : mergeMail[i]) temp.add(it) ; // then add the sorted mails to the temp list 

            //Now add temp to answer and then return the ans 
            ans.add(temp) ; 
        }

        return ans ; 
    }
}