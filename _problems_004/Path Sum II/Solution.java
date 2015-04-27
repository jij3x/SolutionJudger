public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;

        dfs(root, sum, new LinkedList<Integer>(), result);
        return result;
    }

    private void dfs(TreeNode root, int sum, LinkedList<Integer> path, ArrayList<List<Integer>> result) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == sum)
                result.add(new ArrayList<Integer>(path));
        } else {
            if (root.left != null)
                dfs(root.left, sum - root.val, path, result);
            if (root.right != null)
                dfs(root.right, sum - root.val, path, result);
        }
        path.removeLast();
    }
}
