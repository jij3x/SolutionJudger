/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int rem = 0;
    private TreeNode kthSmallestElementHelper(TreeNode root) {
        if (root == null) return null;
        TreeNode result = kthSmallestElementHelper(root.left);
        if (result != null) return result;
        if (--rem == 0) return root;
        return kthSmallestElementHelper(root.right);
    }
    
    public int kthSmallestElement(TreeNode root, int k) {
        rem = k;
        return kthSmallestElementHelper(root).val;
    }
}