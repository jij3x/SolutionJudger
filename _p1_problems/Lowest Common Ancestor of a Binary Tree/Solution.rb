class Solution
  def lowestCommonAncestor(root, p, q)
    return root if [nil, p, q].index root
    left = lowestCommonAncestor(root.left, p, q)
    right = lowestCommonAncestor(root.right, p, q)
    left && right ? root : left || right
  end
end