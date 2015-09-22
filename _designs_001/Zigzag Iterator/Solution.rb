class Solution
  def testBSTIterator(root)
    it, r = BSTIterator.new(root), []
    r << it.next while it.hasNext
    r
  end
end
