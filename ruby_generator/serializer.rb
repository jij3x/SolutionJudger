require 'set'
require 'scanf'
require 'data_structures'

class Serializer

  def serializeBool(b)
    b ? 'true' : 'false'
  end

  def serializeBoolVector(vector)
    (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |b| serializeBool(b) }.join(',') + ']'
  end

  def serializeInt(n)
    n.to_s
  end

  def serializeUnsignedInt(n)
    (n + 0x1_0000_0000).to_s
  end

  def serializeDouble(d)
    d.round(5).to_s
  end

  def serializeString(s)
    "\"#{s}\""
  end

  def serializeMutableString(s)
    serializeString(s)
  end

  def serializeStringArray(array)
    (array.nil? || array.empty?) ? '[]' : '[' + array.map { |s| serializeString(s) }.join(',') + ']'
  end

  def serializeStringVector(vector)
    serializeStringArray(vector)
  end

  def serializeString2DVector(vector)
    (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |v| serializeStringVector(v) }.join(',') + ']'
  end

  def serializeStringSet(set)
    (set.nil? || set.empty?) ? '[]' : serializeStringArray(set.to_a)
  end

  def serializeText(s)
    s
  end

  def serializeIntArray(array)
    (array.nil? || array.empty?) ? '[]' : '[' + array.map { |n| serializeInt(n) }.join(',') + ']'
  end

  def serializeInt2DArray(array)
    (array.nil? || array.empty?) ? '[]' : '[' + array.map { |a| serializeIntArray(a) }.join(',') + ']'
  end

  def serializeIntVector(vector)
    serializeIntArray(vector)
  end

  def serializeInt2DVector(vector)
    serializeInt2DArray(vector)
  end

  def serializeCharArray(array)
    (array.nil? || array.empty?) ? '""' : "\"#{array.join('')}\""
  end

  def serializeChar2DArray(array)
    (array.nil? || array.empty?) ? '[]' : '[' + array.map { |a| serializeCharArray(a) }.join(',') + ']'
  end

  def serializeIntSLList(node)
    parts = []
    until node.nil?
      parts << node._seq_no << node.val
      node = node.next
    end
    "[#{parts.join(',')}]"
  end

  def serializeIntSLListVector(vector)
    (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |n| serializeIntSLList(n) }.join(',') + ']'
  end

  def serializeIntSLRList(node)
    parts1, parts2 = [], []
    until node.nil?
      parts1 << node._seq_no << node.val
      parts2 << node.random.nil? ? '#' : node.random.label
      node = node.next
    end
    "[#{parts1.concat(parts2).join(',')}]"
  end

  def serializeIntBinaryTree(node)
    r, q = [], [node]
    until q.empty?
      curr = q.shift
      next if curr.nil?

      q << curr.left << curr.right
      r.concat(curr.left.nil? ? ['#'] : [curr.left._seq_no, curr.left.val])
      r.concat(curr.right.nil? ? ['#'] : [curr.right._seq_no, curr.right.val])
    end
    '[' + r.join(',').gsub(/(,#)*$/, '') + ']'
  end

  def serializeIntBinaryTreeVector(v)
    (v.nil? || v.empty?) ? '[]' : '[' + v.map { |n| serializeIntBinaryTree(n) }.join(',') + ']'
  end

  def serializeIntLinkedBinaryTree(node)
    r = []
    until node.nil?
      ptr, next_head = node, nil
      until ptr.nil?
        if (!ptr.left.nil? || !ptr.right.nil?) && next_head.nil?
          next_head = ptr.left.nil? ? ptr.right : ptr.left
        end
        r << ptr._seq_no << ptr.val
        ptr = ptr.next
      end
      r << '#'
      node = next_head
    end
    '[' + r.join(',') + ']'
  end

  def serializeIntUDGraph(node)
    return '[]' if node.nil?

    visited, que = Set.new, [node]
    until node.nil?
      curr = que.shift
      curr.neighbors.each do |neighbor|
        unless visited.include?(neighbor)
          visited.push(neighbor)
          que.push(neighbor)
        end
      end
    end

    r = []
    visited.each do |v|
      parts = [v._seq_no, v.label]
      v.neighbors.each { |n| parts << n._seq_no << n.label } unless v.neighbors.nil?
      r << "[#{parts.join(',')}]"
    end
    '[' + r.join(',') + ']'
  end

  def serializeIPoint(point)
    point.nil? ? '[]' : "[#{point._seq_no},#{point.x},#{point.y}]"
  end

  def serializeIPointArray(array)
    (array.nil? || array.empty?) ? '[]' : '[' + array.map { |p| serializeIPoint(p) }.join(',') + ']'
  end

  def serializeIInterval(interval)
    interval.nil? ? '[]' : "[#{interval._seq_no},#{interval.start},#{interval.end}]"
  end

  def serializeIIntervalVector(vector)
    (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |i| serializeIInterval(i) }.join(',') + ']'
  end

  def getStreamToken
    nil
  end

  def deserializeBool
    scanf('%s').first == 'true' ? true : false
  end

  def deserializeBoolVector
    vector = []
    scanf('%d').first.times { vector << deserializeBool }
    vector
  end

  def deserializeInt
    scanf('%d').first
  end

  def deserializeUnsignedInt
    deserializeInt
  end

  def deserializeDouble
    scanf('%f').first
  end

  def deserializeString
    scanf('%s').first[1..-2]
  end

  def deserializeMutableString
    scanf('%s').first[1..-2]
  end

  def deserializeStringArray
    array = []
    deserializeInt.times { array << deserializeString }
    array
  end

  def deserializeStringVector
    deserializeStringArray
  end

  def deserializeString2DVector
    array = []
    deserializeInt.times { array << deserializeStringVector }
    array
  end

  def deserializeStringSet
    set = Set.new
    deserializeInt.times { set.add(deserializeString) }
    set
  end

  def deserializeText
    deserializeString
  end

  def deserializeIntArray
    array = []
    deserializeInt.times { array << deserializeInt }
    array
  end

  def deserializeInt2DArray
    array = []
    deserializeInt.times { array << deserializeIntArray }
    array
  end

  def deserializeIntVector
    deserializeIntArray
  end

  def deserializeInt2DVector
    deserializeInt2DArray
  end

  def deserializeCharArray
    deserializeString.split('')
  end

  def deserializeChar2DArray
    array = []
    deserializeInt.times { array << deserializeCharArray }
    array
  end

  def deserializeIntSLList
    start = ListNode.new(0)
    tail, seq_no = start, 0
    deserializeInt.times do
      tail.next = ListNode.new(deserializeInt)
      tail = tail.next
      tail._seq_no = (seq_no += 1)
    end
    start.next
  end

  def deserializeIntSLListVector
    vector = []
    deserializeInt.times { vector << deserializeIntSLList }
    vector
  end

  def deserializeIntSLRList
    start = RandomListNode.new(0)
    tail, seq_no, memo = start, 0, Hash.new
    (deserializeInt / 2).times do
      tail.next = RandomListNode.new(deserializeInt)
      tail = tail.next
      tail._seq_no = (seq_no += 1)
      memo[tail.label] = tail
    end

    tail = start.next
    until tail.nil? do
      r_raw = scanf('%s')
      tail.random = memo[r_raw.to_i] unless r_raw == '#'
    end
    start.next
  end

  def deserialize_bt(bt_class)
    list, seq_no, f, ptr = [bt_class.new(0)], 0, 1, 0
    deserializeInt.times do
      new_node, v_raw = nil, scanf('%s')
      unless v_raw == '#'
        new_node = bt_class.new(v_raw.to_i)
        new_node._seq_no = (seq_no += 1)
        list << new_node
      end
      list[ptr].left = (f == 0) ? new_node : list[ptr].left
      list[ptr].right = (f == 1) ? new_node : list[ptr].right
      ptr += (f ^= 1) == 0 ? 1 : 0
    end
    list[1]
  end

  def deserializeIntBinaryTree
    deserialize_bt(TreeNode)
  end

  def deserializeIntBinaryTreeVector
    vector = []
    deserializeInt.times { vector << deserializeIntBinaryTree }
    vector
  end

  def deserializeIntLinkedBinaryTree
    deserialize_bt(TreeLinkNode)
  end

  def deserializeIntUDGraph
    graph, memo, seq_no = nil, Hash.new, 0
    deserializeInt.times do
      nodes = deserializeIntArray
      node = memo[nodes[0]]
      if node.nil?
        node = UndirectedGraphNode.new(nodes[0])
        node._seq_no = (seq_no += 1)
        memo[nodes[0]] = node
      end
      graph = node if i == 0

      nodes[1..-1].each do |n|
        neighbor = memo[n]
        if neighbor.nil?
          neighbor = UndirectedGraphNode.new(n)
          neighbor._seq_no = (seq_no += 1)
          memo[n] = neighbor
        end
      end
    end
    graph
  end

  def deserializeIPoint
    Point.new(deserializeInt, deserializeInt)
  end

  def deserializeIPointArray
    array = []
    deserializeInt.times { array << deserializeIInterval }
    array
  end

  def deserializeIInterval
    Interval.new(deserializeInt, deserializeInt)
  end

  def deserializeIIntervalVector
    vector = []
    deserializeInt.times { vector << deserializeIInterval }
    vector
  end

  private :deserialize_bt

end
