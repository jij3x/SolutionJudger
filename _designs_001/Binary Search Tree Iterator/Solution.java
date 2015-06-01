public class Solution {
    public List<Integer> testBSTIterator(TreeNode root) {
        BSTIterator it = new BSTIterator(root);
        List<Integer> result = new ArrayList<Integer>();

        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }
}