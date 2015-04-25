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
    int findVal(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) return i;
        }
        return -1;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        if (n == 0) return null;
        int rootVal = postorder[n-1];
        int i = findVal(inorder, rootVal);
        TreeNode node = new TreeNode(rootVal);
        int[] inL = Arrays.copyOfRange(inorder, 0, i);
        int[] inR = Arrays.copyOfRange(inorder, i + 1, n);
        int[] postL = Arrays.copyOfRange(postorder, 0, i);
        int[] postR = Arrays.copyOfRange(postorder, i, n - 1);
        node.left = buildTree(inL, postL);
        node.right = buildTree(inR, postR);
        return node;
    }
}