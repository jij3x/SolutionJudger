class MyStack
  def initialize
    @que_in, @que_out = [], []
  end

  def push(x)
    @que_in.push(x)
    @que_in.push @que_out.shift until @que_out.empty?
    @que_in, @que_out = @que_out, @que_in
  end

  def pop
    @que_out.shift
  end

  def top
    @que_out.first
  end

  def empty
    @que_out.empty?
  end
end
