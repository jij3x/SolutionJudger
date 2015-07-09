class TrieNode
  attr_accessor :last, :children

  def initialize
    @last, @children = false, Hash.new
  end
end
