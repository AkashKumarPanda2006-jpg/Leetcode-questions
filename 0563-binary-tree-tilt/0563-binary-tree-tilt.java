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
    private int tilt = 0;

    private int calculateTilt(TreeNode root){

        if(root == null) return 0 ;

        int leftTilt = calculateTilt(root.left);
        int rightTilt = calculateTilt(root.right);

        int currTilt = Math.abs(leftTilt - rightTilt);
        tilt += currTilt ;

        return root.val + leftTilt + rightTilt ;
    }
    public int findTilt(TreeNode root) {

        tilt = 0 ;
        calculateTilt(root);
        return tilt ;
        
    }
}