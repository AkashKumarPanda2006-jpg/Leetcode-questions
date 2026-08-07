class Solution {
    class Pair{
        int node , weight ;
        public Pair(int _node , int _weight){
            node = _node ;
            weight = _weight ;
        }
    }

    class Tuple{
        int stop , node , weight ;
        public Tuple(int _stop , int _node , int _weight){
            stop = _stop ;
            node = _node ;
            weight = _weight ; 

        }

    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        

       int m = flights.length ;
      

       for(int i=0 ; i<n ; i++){
            adj.add(new ArrayList<>());
       } 

       for(int i=0 ; i<m ; i++){
        adj.get(flights[i][0]).add(new Pair(flights[i][1] ,flights[i][2]));
       }

       int distance[] = new int [n];
       for(int i=0 ; i<n ; i++){
            distance[i] = Integer.MAX_VALUE;
       }

        Queue<Tuple> q = new LinkedList<>();
        
        distance[src] = 0 ;
        q.add(new Tuple(0,src,0));

        while(!q.isEmpty()){
            Tuple it = q.poll();
            int stops = it.stop ;
            int node = it.node; 
            int dist = it.weight;

            if(stops > k) continue ;

            for(Pair iter : adj.get(node)){
                int adjNode = iter.node ;
                int edw = iter.weight ;

                if(edw + dist < distance[adjNode] && stops <= k){
                    distance[adjNode] = edw + dist ;
                    q.add(new Tuple(stops + 1 , adjNode , edw + dist));
                }
            }
        }

       if(distance[dst] == Integer.MAX_VALUE) return -1;

       return distance[dst] ;

        
    }
}