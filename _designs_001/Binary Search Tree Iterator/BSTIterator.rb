class BSTIterator
  def initialize(root)
    @s = []
    until root.nil?
      @s.push(root)
      root = root.left
    end
  end

  def hasNext
    !@s.empty?
  end

  def next
    p = @s.pop
    r = p.val
    p = p.right
    until p.nil?
      @s.push(p)
      p = p.left
    end
    r
  end
end