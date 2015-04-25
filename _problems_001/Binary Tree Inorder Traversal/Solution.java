public class Solution {
    public ArrayList<Integer> inorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> al = new ArrayList<Integer>();
        if(root==null)
            return al;
        
        TreeNode prev=root;
        Stack<TreeNode> stack=new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty())
        {
            TreeNode node=stack.peek();
            
            if(node.right!=null && node.right==prev)
            {
                prev=stack.pop();
            }
            else if(node.left == null || node.left==prev)
            {
                al.add(node.val);
                if(node.right!=null)
                    stack.push(node.right);
                else
                    prev=stack.pop();
            }
            else
            {
                stack.push(node.left);
            }
        }
        return al;
    }
}