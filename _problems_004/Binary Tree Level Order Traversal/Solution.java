public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int numCurrLevel = 1;
        int numNextLevel = 0;
        q.add(root);
        ArrayList<Integer> row = new ArrayList<Integer>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            numCurrLevel--;
            if (node.left != null) {
                q.add(node.left);
                numNextLevel++;
            }
            if (node.right != null) {
                q.add(node.right);
                numNextLevel++;
            }
            row.add(node.val);
            if (numCurrLevel == 0) {
                numCurrLevel = numNextLevel;
                numNextLevel = 0;
                ret.add(row);
                row = new ArrayList<Integer>();
            }
        }
        return ret;
    }
}