public class Solution {
    boolean hasPathSum(TreeNode p, int currentSum, int sum) {
        if (p == null) return false;
        currentSum += p.val;
        if (currentSum == sum && p.left == null && p.right == null) return true;
        return hasPathSum(p.left, currentSum, sum) || hasPathSum(p.right, currentSum, sum);
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSum(root, 0, sum);
    }
}