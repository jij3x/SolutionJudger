class ListNode
  attr_accessor :_seq_no, :val, :next

  def initialize(val)
    @_seq_no, @val, @next = -1, val, nil
  end
end

class RandomListNode
  attr_accessor :_seq_no, :label, :next, :random

  def initialize(label)
    @_seq_no, @label, @next, @random = -1, label, nil, nil
  end
end

class TreeNode
  attr_accessor :_seq_no, :val, :left, :right

  def initialize(val)
    @_seq_no, @val, @left, @right = -1, val, nil, nil
  end
end

class TreeLinkNode
  attr_accessor :_seq_no, :val, :left, :right, :next

  def initialize(val)
    @_seq_no, @val, @left, @right, @next = -1, val, nil, nil, nil
  end
end

class Point
  attr_accessor :_seq_no, :x, :y

  def initialize(x = 0, y = 0)
    @_seq_no, @x, @y = -1, x, y
  end
end

class Interval
  attr_accessor :_seq_no, :start, :end

  def initialize(s = 0, e = 0)
    @_seq_no, @start, @end = -1, s, e
  end
end

class UndirectedGraphNode
  attr_accessor :_seq_no, :label, :neighbors

  def initialize(label)
    @_seq_no, @label, @neighbors = -1, label, []
  end
end