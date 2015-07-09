class Solution
  def testTrie(inp, metrics)
    metrics[0] = 0
    trie, r = Trie.new, []
    Serializer.deserializeInt(inp).times do
      case Serializer.deserializeInt(inp)
      when 0
        word = Serializer.deserializeString(inp)
        start_time = Time.now
        trie.insert(word)
        metrics[0] += (Time.now - start_time) * 1000
      when 1
        word = Serializer.deserializeString(inp)
        start_time = Time.now
        r << trie.search(word)
        metrics[0] += (Time.now - start_time) * 1000
      when 2
        word = Serializer.deserializeString(inp)
        start_time = Time.now
        r << trie.startsWith(word)
        metrics[0] += (Time.now - start_time) * 1000
      end
    end
    r
  end
end
