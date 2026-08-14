import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    // 4-directional offsets: up, down, left, right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        // Map to store islandId -> islandArea
        Map<Integer, Integer> islandSizes = new HashMap<>();
        int islandId = 2; // Start labels from 2 to avoid conflicting with 0 and 1
        int maxArea = 0;

        // Step 1: Precompute areas of all existing islands
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    int size = dfs(grid, r, c, islandId);
                    islandSizes.put(islandId, size);
                    maxArea = Math.max(maxArea, size); // Tracks grid with no 0s (like [[1,1],[1,1]])
                    islandId++;
                }
            }
        }

        // Step 2: Iterate through water cells and try flipping them
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    Set<Integer> uniqueNeighbors = new HashSet<>();
                    
                    // Check all 4 adjacent directions
                    for (int[] dir : DIRECTIONS) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        
                        if (isValid(grid, nr, nc) && grid[nr][nc] > 1) {
                            uniqueNeighbors.add(grid[nr][nc]);
                        }
                    }

                    // Total potential area is 1 (the flipped cell) + neighbor areas
                    int potentialArea = 1;
                    for (int id : uniqueNeighbors) {
                        potentialArea += islandSizes.get(id);
                    }
                    
                    maxArea = Math.max(maxArea, potentialArea);
                }
            }
        }

        return maxArea;
    }

    // Helper DFS function to mark islands and count their size
    private int dfs(int[][] grid, int r, int c, int id) {
        if (!isValid(grid, r, c) || grid[r][c] != 1) {
            return 0;
        }

        grid[r][c] = id; // Label the cell with the unique island ID
        int size = 1;

        for (int[] dir : DIRECTIONS) {
            size += dfs(grid, r + dir[0], c + dir[1], id);
        }

        return size;
    }

    // Boundary check helper
    private boolean isValid(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
