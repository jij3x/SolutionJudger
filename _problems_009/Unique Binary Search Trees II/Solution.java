public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return dfsGenerateTrees(1, n);
    }

    private List<TreeNode> dfsGenerateTrees(int start, int end) {
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        if (start > end)
            result.add(null);

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = dfsGenerateTrees(start, i - 1);
            List<TreeNode> rightTrees = dfsGenerateTrees(i + 1, end);

            for (TreeNode leftRoot : leftTrees) {
                for (TreeNode rightRoot : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftRoot; // Need deep copy?
                    root.right = rightRoot; // Need deep copy?
                    result.add(root);
                }
            }
        }
        return result;
    }
}