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
    private void findNodes(TreeNode root , int x ,int y , int level[] , int parents[] ,int currLevel , int currParent){
        if(root == null) return ;
        if(root.val == x){
            level[0] = currLevel ;
            parents[0] = currParent ;
        }

        if(root.val == y){
            level[1] = currLevel ;
            parents[1] = currParent ;
        }

        findNodes(root.left ,x, y ,level,  parents , currLevel + 1 , root.val  );
        findNodes(root.right ,x, y ,level , parents , currLevel + 1 , root.val );

    }
    public boolean isCousins(TreeNode root, int x, int y) {

        int level[] = {-1,1};
        int parents[] = {-1,1};

        findNodes(root , x, y ,level ,  parents , 0 ,root.val);
        if(level[0] == level[1] && parents[0] != parents[1]){
            return true ;
        }
        
        return false ;
    }
}