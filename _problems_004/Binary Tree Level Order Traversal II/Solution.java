public class Solution {
    void printLevel(int k, TreeNode p, ArrayList<List<Integer>> ret) {
        if (p == null) return;
        printLevel(k - 1, p.left, ret);
        printLevel(k - 1, p.right, ret);
        ret.get(k).add(p.val);
    }
    int getHeight(TreeNode p) {
        if (p == null) return 0;
        return 1 + Math.max(getHeight(p.left), getHeight(p.right));
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        int height = getHeight(root);
        for (int i = 0; i < height; i++) {
            ret.add(new ArrayList<Integer>());
        }
        printLevel(height - 1, root, ret);
        return ret;
    }
}
