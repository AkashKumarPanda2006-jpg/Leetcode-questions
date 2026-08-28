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
    private boolean isSame(TreeNode root , int val){
        if(root == null) return true ;
        if(root.val != val) return false ;

        return isSame(root.left , val) && isSame(root.right , val) ;
       
    }
    public boolean isUnivalTree(TreeNode root) {

        return isSame(root , root.val);
        
    }
}