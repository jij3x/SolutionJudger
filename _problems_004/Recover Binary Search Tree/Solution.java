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
    TreeNode first, second, prev;
    public void recoverTree(TreeNode root) {
        first = second = prev = null;
        recoverHelper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    void recoverHelper(TreeNode p) {
        if (p == null) return;
        recoverHelper(p.left);
        if (prev != null && prev.val > p.val) {
            if (first == null)
                first = prev;
            second = p;
        }
        prev = p;
        recoverHelper(p.right);
    }
}