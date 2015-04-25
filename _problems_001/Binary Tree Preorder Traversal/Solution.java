public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        // Base Case
        if (root == null)
           return ret;
     
        // Create an empty stack and push root to it
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        nodeStack.push(root);
     
        /* Pop all items one by one. Do following for every popped item
           a) print it
           b) push its right child
           c) push its left child
        Note that right child is pushed first so that left is processed first */
        while (!nodeStack.empty())
        {
            // Pop the top item from stack and print it
            TreeNode node = nodeStack.pop();
            ret.add(node.val);
     
            // Push right and left children of the popped node to stack
            if (node.right != null)
                nodeStack.push(node.right);
            if (node.left != null)
                nodeStack.push(node.left);
        }
        return ret;
    }
}