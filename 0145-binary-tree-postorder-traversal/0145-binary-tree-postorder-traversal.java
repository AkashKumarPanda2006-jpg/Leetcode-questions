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
        TreeNode curr = root ;

        while(curr != null || !st.isEmpty()){
            
            if(curr != null ){
                st.add(curr);
                curr = curr.left ;
            }
            else{
               TreeNode  temp =  st.peek().right;
               if(temp == null){
                    temp = st.peek();
                    st.pop();
                    postorder.add(temp.val ) ;

                    while(!st.isEmpty() &&  temp == st.peek().right){
                        temp = st.peek();
                        st.pop();
                        postorder.add(temp.val) ;
                    }


               }else{
                curr = temp ; 
               }
            }
        }

        return postorder ;
    }
}