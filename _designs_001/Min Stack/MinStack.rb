class MinStack
  def initialize
    @stack, @min_stack = [], []
  end

  def push(x)
    @stack.push(x)
    @min_stack.push(x) if (@min_stack.empty? or @min_stack.last >= x)
  end

  def pop
    x = @stack.pop
    @min_stack.pop if @min_stack.last == x
    x
  end

  def top
    @stack.last
  end

  def getMin
    @min_stack.last
  end
end