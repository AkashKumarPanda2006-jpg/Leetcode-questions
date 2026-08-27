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
    private void dfs(TreeNode node , StringBuilder ans , List<String> path){

        int len =  ans.length();

        if (len > 0) {
            ans.append("->");
        }
        ans.append(node.val);

        
        if (node.left == null && node.right == null) {
            path.add(ans.toString());
        } else {
            
            if (node.left != null) {
                dfs(node.left, ans, path);
            }
            if (node.right != null) {
                dfs(node.right, ans, path);
            }
        }

        ans.setLength(len);
    }
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> path = new ArrayList<>();
        
        if(root != null){

            dfs(root , new StringBuilder() ,path);

        }
        return path ;       
    }
}