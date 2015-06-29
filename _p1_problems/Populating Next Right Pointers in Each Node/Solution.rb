class Solution
  def connect(root)
    return if root.nil? or root.left.nil?

    root.left.next = root.right
    root.right.next = root.next.left unless root.next.nil?

    connect(root.left)
    connect(root.right)
  end
end
