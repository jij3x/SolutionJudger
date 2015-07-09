class MyStack
  def initialize
    @que_in, @que_out = [], []
  end

  def push(x)
    @que_in.push(x)
  end

  def pop
    top
    @que_out.shift
  end

  def top
    @que_out.push @que_in.pop until @que_in.empty? if @que_out.empty?
    @que_out.first
  end

  def empty
    @que_in.empty? and @que_out.empty?
  end
end
