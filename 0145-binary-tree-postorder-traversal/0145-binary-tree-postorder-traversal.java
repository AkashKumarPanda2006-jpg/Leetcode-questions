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
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> st = new Stack<TreeNode>();
        Stack<TreeNode> st2 = new Stack<TreeNode>();

        if(root == null) return postorder ;

        st.add(root);
        while(!st.isEmpty()){
            root = st.pop();
            st2.add(root);

            if(root.left != null) st.add(root.left) ;
            if(root.right != null) st.add(root.right) ;
        }
        
        while(!st2.isEmpty()){
            postorder.add(st2.pop().val) ;
        }

        return postorder ;
    }
}