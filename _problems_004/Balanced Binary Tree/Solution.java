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
    int height(TreeNode p) {
        if (p == null) return 0;
        return 1 + Math.max(height(p.left), height(p.right));
    }
    public boolean isBalanced(TreeNode root) {
        return root == null || (Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right));
    }
}