public class Solution {
    // solution by peking2:
    // http://www.mitbbs.com/article_t1/JobHunting/32238697_0_1.html
    TreeLinkNode findNext(TreeLinkNode node) {
        while (node != null) {
            if(node.left != null)
                return node.left;
            if(node.right != null)
                return node.right;
            node=node.next;
        }
        return null;
    }
    void dfs(TreeLinkNode node, TreeLinkNode parent){
        if (node == null) return;
        if (node == parent.left && parent.right!=null) {
            node.next = parent.right;
        }
        else {
            node.next = findNext(parent.next);
        }
        dfs(node.right,node);
        dfs(node.left,node);
    }   
    void connect(TreeLinkNode root) {
        if (root==null) return;
        dfs(root.right, root);
        dfs(root.left, root);
    }
}
