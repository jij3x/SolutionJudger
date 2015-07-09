class Trie
  def initialize
    @root = TrieNode.new
  end

  def insert(s)
    p = @root
    (0...s.length).each do |i|
      p.children[s[i]] = TrieNode.new if !p.children.key?(s[i])
      p = p.children[s[i]]
    end
    p.last = true
  end

  def search(key)
    p = traverse(key)
    p.nil? ? false : p.last
  end

  def startsWith(prefix)
    !traverse(prefix).nil?
  end

  def traverse(s)
    p = @root
    (0...s.length).each do |i|
      return nil if !p.children.key?(s[i])
      p = p.children[s[i]]
    end
    p
  end
end