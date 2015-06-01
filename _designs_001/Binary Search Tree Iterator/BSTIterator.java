public class BSTIterator {
    Stack<TreeNode> s;

    public BSTIterator(TreeNode root) {
        s = new Stack<TreeNode>();
        for (; root != null; root = root.left)
            s.push(root);
    }

    public boolean hasNext() {
        return !s.empty();
    }

    public int next() {
        TreeNode p = s.peek();
        int result = p.val;
        s.pop();
        for (p = p.right; p != null; p = p.left)
            s.push(p);
        return result;
    }
}
