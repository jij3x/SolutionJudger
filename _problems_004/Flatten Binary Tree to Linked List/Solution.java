public class Solution {
    void flattenHelper(TreeNode p) {
        if (p == null) return;
        flattenHelper(p.right);
        flattenHelper(p.left);
        p.right = prev;
        p.left = null;
        prev = p;
    }
    TreeNode prev;
    public void flatten(TreeNode root) {
        prev = null;
        flattenHelper(root);
    }
}
