class MyQueue
  def initialize
    @stk_in, @stk_out = [], []
  end

  def push(x)
    @stk_in.push(x)
  end

  def pop
    peek
    @stk_out.pop
  end

  def peek
    @stk_out.push @stk_in.pop until @stk_in.empty? if @stk_out.empty?
    @stk_out.last
  end

  def empty
    @stk_in.empty? and @stk_out.empty?
  end
end
