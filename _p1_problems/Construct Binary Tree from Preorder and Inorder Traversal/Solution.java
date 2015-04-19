public class Solution {
    // Makes unnecessary copy for each recursive call.
    int findIndex(int[] in, int findVal) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == findVal) return i;
        }
        return -1;
    }    
    public TreeNode buildTree(int[] pre, int[] in) {
        int n = pre.length;
        if (n == 0) return null;
        int rootVal = pre[0];
        int idx = findIndex(in, rootVal);
        int nLeft = idx;
        int[] leftPre = Arrays.copyOfRange(pre, 1, 1 + nLeft);
        int[] rightPre = Arrays.copyOfRange(pre, 1 + nLeft, n);
        int[] leftIn = Arrays.copyOfRange(in, 0, idx);
        int[] rightIn = Arrays.copyOfRange(in, idx + 1, n);
        TreeNode node = new TreeNode(rootVal);
        node.left = buildTree(leftPre, leftIn);
        node.right = buildTree(rightPre, rightIn);
        return node;
    }
}
