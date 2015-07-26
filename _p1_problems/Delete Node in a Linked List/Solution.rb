class Solution
  def deleteNode(node)
    node.val = node.next.val
    node.next = node.next.next
  end
end
