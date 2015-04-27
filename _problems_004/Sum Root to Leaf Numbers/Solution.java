public class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return sumNumbers(root, 0);
    }
    
    private int sumNumbers(TreeNode root, int accum) {
        accum = accum * 10 + root.val;
        if (root.left == null && root.right == null) return accum;
        int sum = 0;
        if (root.left != null)
            sum += sumNumbers(root.left, accum);
        if (root.right != null)
            sum += sumNumbers(root.right, accum);
        return sum;
    }
}