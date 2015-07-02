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
    word.split('').each { |c| node = node.getOrCreateChild(c) }
    node.value = true
  end

  def search(word)
    search_trie(@root, word, 0)
  end

  def search_trie(node, word, start)
    word[start..-1].split('').each_with_index do |c, i|
      if c == '.'
        node.children.each_value { |child| return true if search_trie(child, word, start + i + 1) }
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
