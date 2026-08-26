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
    private int dfs(TreeNode root , int currMax , int currMin){

        if(root == null) return Math.abs(currMax - currMin) ;

        currMin = Math.min(currMin , root.val);
        currMax = Math.max(currMax , root.val);

        int leftDiff = dfs(root.left , currMax , currMin);
        int rightDiff = dfs(root.right , currMax , currMin) ;

        return Math.max(leftDiff , rightDiff) ;
       
    }
    public int maxAncestorDiff(TreeNode root) {

        if(root == null) return 0 ;
        return dfs(root , root.val , root.val);
    }
}