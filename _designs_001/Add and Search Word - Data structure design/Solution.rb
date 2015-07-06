class Solution
  def testWordDictionary(inp, metrics)
    metrics[0] = 0
    wd, r = WordDictionary.new, []
    Serializer.deserializeInt(inp).times do
      case Serializer.deserializeInt(inp)
      when 0
        word = Serializer.deserializeString(inp)
        start_time = Time.now
        wd.addWord(word)
        metrics[0] += (Time.now - start_time) * 1000
      when 1
        word = Serializer.deserializeString(inp)
        start_time = Time.now
        r << wd.search(word)
        metrics[0] += (Time.now - start_time) * 1000
      end
    end
    r
  end
end
