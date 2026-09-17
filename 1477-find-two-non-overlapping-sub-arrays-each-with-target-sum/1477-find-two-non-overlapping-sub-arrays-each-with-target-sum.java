import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = n + 1;

        
        int[] best = new int[n];

        Arrays.fill(best, INF);

        Map<Integer, Integer> map = new HashMap<>();

        
        map.put(0, -1);

        int prefix = 0;
        int answer = INF;
        int minLength = INF;

        for (int i = 0; i < n; i++) {

            prefix += arr[i];

            
            if (map.containsKey(prefix - target)) {

                int start = map.get(prefix - target);
                int length = i - start;

                
                if (start >= 0 && best[start] != INF) {
                    answer = Math.min(answer, length + best[start]);
                }

                
                minLength = Math.min(minLength, length);
            }

            
            if (i > 0) {
                best[i] = Math.min(best[i - 1], minLength);
            } else {
                best[i] = minLength;
            }

            map.put(prefix, i);
        }

        return answer == INF ? -1 : answer;
    }
}