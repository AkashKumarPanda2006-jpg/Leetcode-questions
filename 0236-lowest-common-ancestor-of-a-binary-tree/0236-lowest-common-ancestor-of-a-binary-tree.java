/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //if root is null or matches any value 
        if(root == null || root.val == p.val || root.val == q.val){
            return root ;
        }
        
        //Call for the left and right subtree 
        TreeNode leftlca = lowestCommonAncestor(root.left , p , q);
        TreeNode rightlca = lowestCommonAncestor(root.right , p , q) ;

        //if any subtree is null return the other ans 
        if(rightlca == null){
        return leftlca ;
       }

        if(leftlca == null){
        return rightlca ;
       }

        return root ; //if not rsubtree or lsubtree then root is the lca itself 
    }
}