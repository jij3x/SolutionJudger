public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) return ret;
        Stack<TreeNode> q = new Stack<TreeNode>();
        Stack<TreeNode> nextQ = new Stack<TreeNode>();
        q.push(root);
        ArrayList<Integer> row = new ArrayList<Integer>();
        boolean leftRight = true;
        while (!q.isEmpty()) {
            TreeNode node = q.pop();
            if (leftRight) {
                if (node.left != null) nextQ.push(node.left);
                if (node.right != null) nextQ.push(node.right);
            } else {
                if (node.right != null) nextQ.push(node.right); // here you can actually use push_front. 
                if (node.left != null) nextQ.push(node.left); // but you would have to retrieve using q.front() and pop_front().
            }
            row.add(node.val);
            if (q.isEmpty()) {
                ret.add(row);
                row = new ArrayList<Integer>();
                q = nextQ;
                nextQ = new Stack<TreeNode>();
                leftRight = !leftRight;
            }
        }
        return ret;
    }
}