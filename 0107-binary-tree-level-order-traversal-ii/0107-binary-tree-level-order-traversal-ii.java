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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<>();
        Stack<List<Integer>> st = new Stack<>();

        if(root == null) return result ;
        q.offer(root) ;

        while(!q.isEmpty()){
            int level = q.size();
            List<Integer> ans = new LinkedList<Integer>();

            for(int i=0 ; i<level ; i++){
                if(q.peek().left != null) q.offer(q.peek().left) ;
                if(q.peek().right != null) q.offer(q.peek().right) ; 

                ans.add(q.poll().val) ;

            }

           st.add(ans) ;
        }

        while(!st.isEmpty()){
            result.add(st.pop());
        }

        return result ; 
    }
}