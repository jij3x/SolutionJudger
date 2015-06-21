require 'set'
require 'scanf'
require 'data_structures'

class Serializer

  class << self

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
      "#{format('%.5f', d)}"
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
        parts1 << node._seq_no << node.label
        parts2 << (node.random.nil? ? '#' : node.random.label)
        node = node.next
      end
      "[#{parts1.concat(parts2).join(',')}]"
    end

    def serializeIntBinaryTree(node)
      r, q = node.nil? ? ['#'] : [node._seq_no, node.val], [node]
      until q.empty?
        curr = q.shift
        next if curr.nil?

        q << curr.left << curr.right
        r.concat(curr.left.nil? ? ['#'] : [curr.left._seq_no, curr.left.val])
        r.concat(curr.right.nil? ? ['#'] : [curr.right._seq_no, curr.right.val])
      end
      '[' + r.join(',').gsub(/(,#)*$/, '').gsub(/(#)$/, '') + ']'
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

    def getStreamToken(inp)
      inp
    end

    def deserializeBool(inp)
      inp.scanf('%s').first == 'true' ? true : false
    end

    def deserializeBoolVector(inp)
      vector = []
      inp.scanf('%d').first.times { vector << deserializeBool(inp) }
      vector
    end

    def deserializeInt(inp)
      inp.scanf('%d').first
    end

    def deserializeUnsignedInt(inp)
      deserializeInt(inp)
    end

    def deserializeDouble(inp)
      inp.scanf('%f').first
    end

    def deserializeString(inp)
      s, cnt = '', 0
      while cnt < 2 do
        char = inp.scanf('%c').first
        cnt += 1 if char == '"'
        s << char if cnt == 1 and char != '"'
      end
      return s
    end

    def deserializeMutableString(inp)
      deserializeString(inp)
    end

    def deserializeStringArray(inp)
      array = []
      deserializeInt(inp).times { array << deserializeString(inp) }
      array
    end

    def deserializeStringVector(inp)
      deserializeStringArray(inp)
    end

    def deserializeString2DVector(inp)
      array = []
      deserializeInt(inp).times { array << deserializeStringVector(inp) }
      array
    end

    def deserializeStringSet(inp)
      set = Set.new
      deserializeInt(inp).times { set.add(deserializeString(inp)) }
      set
    end

    def deserializeText(inp)
      deserializeString(inp)
    end

    def deserializeIntArray(inp)
      array = []
      deserializeInt(inp).times { array << deserializeInt(inp) }
      array
    end

    def deserializeInt2DArray(inp)
      array = []
      deserializeInt(inp).times { array << deserializeIntArray(inp) }
      array
    end

    def deserializeIntVector(inp)
      deserializeIntArray(inp)
    end

    def deserializeInt2DVector(inp)
      deserializeInt2DArray(inp)
    end

    def deserializeCharArray(inp)
      deserializeString(inp).split('')
    end

    def deserializeChar2DArray(inp)
      array = []
      deserializeInt(inp).times { array << deserializeCharArray(inp) }
      array
    end

    def deserializeIntSLList(inp)
      start = ListNode.new(0)
      tail, seq_no = start, 0
      deserializeInt(inp).times do
        tail.next = ListNode.new(deserializeInt(inp))
        tail = tail.next
        tail._seq_no = (seq_no += 1)
      end
      start.next
    end

    def deserializeIntSLListVector(inp)
      vector = []
      deserializeInt(inp).times { vector << deserializeIntSLList(inp) }
      vector
    end

    def deserializeIntSLRList(inp)
      start = RandomListNode.new(0)
      tail, seq_no, memo = start, 0, Hash.new
      (deserializeInt(inp) / 2).times do
        tail.next = RandomListNode.new(deserializeInt(inp))
        tail = tail.next
        tail._seq_no = (seq_no += 1)
        memo[tail.label] = tail
      end

      tail = start.next
      until tail.nil? do
        r_raw = inp.scanf('%s').first
        tail.random = memo[r_raw.to_i] unless r_raw == '#'
        tail = tail.next
      end
      start.next
    end

    def deserialize_bt(inp, bt_class)
      list, seq_no, f, ptr = [bt_class.new(0)], 0, 1, 0
      deserializeInt(inp).times do
        new_node, v_raw = nil, inp.scanf('%s').first
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

    def deserializeIntBinaryTree(inp)
      deserialize_bt(inp, TreeNode)
    end

    def deserializeIntBinaryTreeVector(inp)
      vector = []
      deserializeInt(inp).times { vector << deserializeIntBinaryTree(inp) }
      vector
    end

    def deserializeIntLinkedBinaryTree(inp)
      deserialize_bt(inp, TreeLinkNode)
    end

    def deserializeIntUDGraph(inp)
      graph, memo, seq_no = nil, Hash.new, 0
      deserializeInt(inp).times do
        nodes = deserializeIntArray(inp)
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

    def deserializeIPoint(inp)
      Point.new(deserializeInt(inp), deserializeInt(inp))
    end

    def deserializeIPointArray(inp)
      array = []
      deserializeInt(inp).times { array << deserializeIPoint(inp) }
      array
    end

    def deserializeIInterval(inp)
      Interval.new(deserializeInt(inp), deserializeInt(inp))
    end

    def deserializeIIntervalVector(inp)
      vector = []
      deserializeInt(inp).times { vector << deserializeIInterval(inp) }
      vector
    end

    private :deserialize_bt

  end

end
