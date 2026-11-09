
import java.util.*;

class Solution {
    public List<Integer> busiestServers(
        int k, int[] arrival, int[] load
    ) {

        // Available server IDs in sorted order
        TreeSet<Integer> available = new TreeSet<>();

        // Busy servers: {finishTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                return Long.compare(a[1], b[1]);
            }
        );

        int[] count = new int[k];

        // Initially all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            long currentTime = arrival[i];

            // Release servers that have completed their requests
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                long[] server = busy.poll();
                available.add((int) server[1]);
            }

            // If all servers are busy, drop this request
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find smallest available server >= preferred
            Integer server = available.ceiling(preferred);

            // If not found, wrap around to the smallest ID
            if (server == null) {
                server = available.first();
            }

            // Assign request to this server
            available.remove(server);

            count[server]++;

            // Mark server as busy
            long finishTime = currentTime + load[i];

            busy.offer(new long[]{finishTime, server});
        }

        // Find maximum number of requests handled
        int maxCount = 0;

        for (int c : count) {
            maxCount = Math.max(maxCount, c);
        }

        // Collect all busiest servers
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                answer.add(i);
            }
        }

        return answer;
    }
}
