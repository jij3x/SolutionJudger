class Solution
  def testTwoSumIII(inp, metrics)
    metrics[0] = 0
    two_sum, r = TwoSum.new, []
    Serializer.deserializeInt(inp).times do
      case Serializer.deserializeInt(inp)
      when 0
        n = Serializer.deserializeInt(inp)
        start_time = Time.now
        two_sum.add(n)
        metrics[0] += (Time.now - start_time) * 1000
      when 1
        n = Serializer.deserializeInt(inp)
        start_time = Time.now
        r << two_sum.find(n)
        metrics[0] += (Time.now - start_time) * 1000
      end
    end
    r
  end
end