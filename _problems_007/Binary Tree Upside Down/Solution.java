public class Solution {
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        TreeNode parent = null;
		TreeNode parentRight = null;
		return UpsideDownBinaryTree(root, parent, parentRight);
    }
    
    private TreeNode UpsideDownBinaryTree(
        TreeNode cur, TreeNode parent, TreeNode parentRight) {
		if (cur == null) {
			return parent;
		}
		TreeNode curLeft = cur.left;
		TreeNode curRight = cur.right;
		cur.left = parentRight;
		cur.right = parent;
		return UpsideDownBinaryTree(curLeft, cur, curRight);
	}
}