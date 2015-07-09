class Solution
  def testMyStack(inp, metrics)
    metrics[0] = 0
    stk, r = MyStack.new, []
    Serializer.deserializeInt(inp).times do
      case Serializer.deserializeInt(inp)
      when 0
        n = Serializer.deserializeInt(inp)
        start_time = Time.now
        stk.push(n)
        metrics[0] += (Time.now - start_time) * 1000
      when 1
        start_time = Time.now
        stk.pop
        metrics[0] += (Time.now - start_time) * 1000
      when 2
        start_time = Time.now
        r << stk.top
        metrics[0] += (Time.now - start_time) * 1000
      when 3
        start_time = Time.now
        r << (stk.empty == true ? 1 : 0)
        metrics[0] += (Time.now - start_time) * 1000
      end
    end
    r
  end
end
