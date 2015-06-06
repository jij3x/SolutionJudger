public class Solution {
    private int getHeight(TreeNode now) {
        int x = 0;
        while (now != null) {
            x++;
            now = now.left;
        }
        return x;
    }
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int hl = getHeight(left);
        int hr = getHeight(right);
        if (hl == hr) {
            return (1 << hl) + countNodes(right);
        } else {
            return (1 << hr) + countNodes(left);
        }
    }
}