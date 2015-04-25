/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    boolean isMirror(TreeNode tree, TreeNode tree2) {
        if(tree == null || tree2 == null){
                return tree == tree2;
        }
       
        if(tree.val != tree2.val){
                return false;
        }
       
        return isMirror(tree.right, tree2.left) &&
                        isMirror(tree.left, tree2.right);
    }
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
}