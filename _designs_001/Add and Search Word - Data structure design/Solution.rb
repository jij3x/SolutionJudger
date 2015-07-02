class Solution
  def testWordDictionary(inp)
    wd, r = WordDictionary.new, []
    Serializer.deserializeInt(inp).times do
        case Serializer.deserializeInt(inp)
        when 0
          wd.addWord(Serializer.deserializeString(inp))
        when 1
          r << wd.search(Serializer.deserializeString(inp))
        end
    end
    r
  end
end
