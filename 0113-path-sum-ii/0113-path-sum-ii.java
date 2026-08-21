/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private void dfs(TreeNode root , int remainingSum ,List<List<Integer>> result,List<Integer> path  ){

        if(root == null){
            return ;
        }

        //add node to the path 
        path.add(root.val) ;

        //Check if leaf node 
        if(root.left == null && root.right == null && root.val == remainingSum){
            result.add(new ArrayList<>(path)) ;
        }

        //Call for the left and right subtree
        dfs(root.left, remainingSum - root.val , result , path);
        dfs(root.right, remainingSum - root.val , result , path);

        //Backtrack if targetSum not met
        path.remove(path.size() - 1) ;
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> path = new LinkedList<Integer>();
        dfs(root ,targetSum , result , path) ;
        return result ;
    }
        
    
}