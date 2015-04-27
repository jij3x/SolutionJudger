public class Solution {
    public boolean isValidBST(TreeNode root) {
        return valid(root, null, null);
    }
    
    private boolean valid(TreeNode p, Integer low, Integer high) {
        if (p == null) return true;
        return (low == null || p.val > low) && (high == null || p.val < high)
            && valid(p.left, low, p.val)
            && valid(p.right, p.val, high);
    }
}