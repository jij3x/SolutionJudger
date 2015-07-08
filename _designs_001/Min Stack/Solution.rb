class Solution
  def testMinStack(inp, metrics)
    metrics[0] = 0
    min_stk, r = MinStack.new, []
    Serializer.deserializeInt(inp).times do
      case Serializer.deserializeInt(inp)
      when 0
        n = Serializer.deserializeInt(inp)
        start_time = Time.now
        min_stk.push(n)
        metrics[0] += (Time.now - start_time) * 1000
      when 1
        start_time = Time.now
        min_stk.pop()
        metrics[0] += (Time.now - start_time) * 1000
      when 2
        start_time = Time.now
        r << min_stk.top()
        metrics[0] += (Time.now - start_time) * 1000
      when 3
        start_time = Time.now
        r << min_stk.getMin()
        metrics[0] += (Time.now - start_time) * 1000
      end
    end
    r
  end
end