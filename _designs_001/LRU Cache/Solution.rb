class Solution
  def testLRUCache(inp, metrics)
    metrics[0] = 0
    lru, r = LRUCache.new(Serializer.deserializeInt(inp)), []
    Serializer.deserializeInt(inp).times do
      case Serializer.deserializeInt(inp)
      when 0
        m, n = Serializer.deserializeInt(inp), Serializer.deserializeInt(inp)
        start_time = Time.now
        lru.set(m, n)
        metrics[0] += (Time.now - start_time) * 1000
      when 1
        n = Serializer.deserializeInt(inp)
        start_time = Time.now
        r << lru.get(n)
        metrics[0] += (Time.now - start_time) * 1000
      end
    end
    r
  end
end