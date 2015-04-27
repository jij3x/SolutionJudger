public class Solution {
    int currMax;
    int maxPathSumHelper(TreeNode root) {
      if (root == null) return 0;
      int maxL = maxPathSumHelper(root.left);
      int maxR = maxPathSumHelper(root.right);
      int maxLR = Math.max(maxL, maxR);
      int ret = root.val + ((maxLR < 0) ? 0 : maxLR);
      currMax = Math.max(currMax, Math.max(ret, root.val + maxL + maxR));
      return ret;
    }
    public int maxPathSum(TreeNode root) {
        currMax = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return currMax;
    }
}
