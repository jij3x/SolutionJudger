class TrieNode
  attr_accessor :children, :value

  def initialize
    @children, @value = Hash.new, false
  end

  def getChild(c)
    @children[c]
  end

  def getOrCreateChild(c)
    child = @children[c]
    @children[c] = TrieNode.new if child.nil?
    @children[c]
  end
end

class WordDictionary
  def initialize
    @root = TrieNode.new
  end

  def addWord(word)
    node = @root
    (0...word.length).each { |i| node = node.getOrCreateChild(word[i]) }
    node.value = true
  end

  def search(word)
    search_trie(@root, word, 0)
  end

  def search_trie(node, word, start)
    (start...word.length).each do |i|
      c = word[i]
      if c == '.'
        node.children.each_value { |child| return true if search_trie(child, word, i + 1) }
        return false
      else
        node = node.getChild(c)
        return false if node.nil?
      end
    end
    node.value
  end

  private :search_trie
end
