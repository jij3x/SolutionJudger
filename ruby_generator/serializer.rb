require 'set'
require 'scanf'
require 'data_structures'

def Serializer
    def serializeBool(b)
        return b ? 'true' : 'false'
    end

    def serializeBoolVector(vector)
        return (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |b| serializeBool(b) }.join(',') + ']'
    end

    def serializeInt(n)
        return n.to_s
    end

    def serializeUnsignedInt(n)
        return (n + 0x1_0000_0000).to_s
    end

    def serializeDouble(d)
        return d.round(5).to_s
    end

    def serializeString(s)
        return "\"#{s}\""
    end

    def serializeMutableString(s)
        return serializeString(s)
    end

    def serializeStringArray(array)
        return (array.nil? || array.empty?) ? '[]' : '[' + array.map { |s| serializeString(s) }.join(',') + ']'
    end

    def serializeStringVector(vector)
        return serializeStringArray(vector)
    end

    def serializeString2DVector(vector)
        return (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |v| serializeStringVector(v) }.join(',') + ']'
    end

    def serializeStringSet(set)
        return (set.nil? || set.empty?) ? '[]' : serializeStringArray(set.to_a)
    end

    def serializeText(s)
        return s
    end

    def serializeIntArray(array)
        return (array.nil? || array.empty?) ? '[]' : '[' + array.map { |n| serializeInt(n) }.join(',') + ']'
    end

    def serializeInt2DArray(array)
        return (array.nil? || array.empty?) ? '[]' : '[' + array.map { |a| serializeIntArray(a) }.join(',') + ']'
    end

    def serializeIntVector(vector)
        return serializeIntArray(vector)
    end

    def serializeInt2DVector(vector)
        return serializeInt2DArray(vector)
    end

    def serializeCharArray(array)
        return (array.nil? || array.empty?) ? '""' : "\"#{array.join('')}\""
    end

    def serializeChar2DArray(array)
        return (array.nil? || array.empty?) ? '[]' : '[' + array.map { |a| serializeCharArray(a) }.join(',') + ']'
    end

    def serializeIntSLList(node)
        parts = []
        until node.nil?
            parts << node._seq_no << node.val
            node = node.next
        end
        return "[#{parts.join(',')}]"
    end

    def serializeIntSLListVector(vector)
        return (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |n| serializeIntSLList(n) }.join(',') + ']'
    end

    def serializeIntSLRList(node)
        parts1, parts2 = [], []
        until node.nil?
            parts1 << node._seq_no << node.val
            parts2 << node.random.nil? ? '#' : node.random.label
            node = node.next
        end
        return "[#{parts1.concat(parts2).join(',')}]"
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
        return '[' + r.join(',').gsub(/(,#)*$/, '') + ']'
    end

    def serializeIntBinaryTreeVector(v)
        return (v.nil? || v.empty?) ? '[]' : '[' + v.map { |n| serializeIntBinaryTree(n) }.join(',') + ']'
    end

    def serializeIntLinkedBinaryTree(node)
        r = []
        until node.nil?
            ptr, next_head = node, nil
            until ptr.nil?
                if ((!ptr.left.nil? || !ptr.right.nil?) && next_head.nil?)
                    next_head = ptr.left.nil? ? ptr.right : ptr.left
                end
                r << ptr._seq_no << ptr.val
                ptr = ptr.next
            end
            r << '#'
            node = next_head
        end
        return '[' + r.join(',') + ']'
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
        return '[' + r.join(',') + ']'
    end

    def serializeIPoint(point)
        return point.nil? ? '[]' : "[#{point._seq_no},#{point.x},#{point.y}]"
    end

    def serializeIPointArray(array)
        return (array.nil? || array.empty?) ? '[]' : '[' + array.map { |p| serializeIPoint(p) }.join(',') + ']'
    end

    def serializeIInterval(interval)
        return interval.nil? ? '[]' : "[#{interval._seq_no},#{interval.start},#{interval.end}]"
    end

    def serializeIIntervalVector(vector)
        return (vector.nil? || vector.empty?) ? '[]' : '[' + vector.map { |i| serializeIInterval(i) }.join(',') + ']'
    end

    def getStreamToken
        return nil
    end

    def deserializeBool
        return scanf('%s').first == 'true' ? true : false
    end

    def deserializeBoolVector
        vector = []
        scanf('%d').first.times { vector << deserializeBool }
        return vector
    end

    def deserializeInt
        return scanf('%d').first
    end

    def deserializeUnsignedInt
        return deserializeInt
    end

    def deserializeDouble
        return scanf('%f').first
    end

    def deserializeString
        return scanf('%s').first[1..-2]
    end

    def deserializeMutableString
        return scanf('%s').first[1..-2]
    end

    def deserializeStringArray
        array = []
        deserializeInt.times { array << deserializeString }
        return array
    end

    def deserializeStringVector
        return deserializeStringArray
    end

    def deserializeString2DVector
        array = []
        deserializeInt.times { array << deserializeStringVector }
        return array
    end

    def deserializeStringSet
        set = Set.new
        deserializeInt.times { set.add(deserializeString) }
        return set
    end

    def deserializeText
        return deserializeString
    end

    def deserializeIntArray
        array = []
        deserializeInt.times { array << deserializeInt }
        return array
    end

    def deserializeInt2DArray
        array = []
        deserializeInt.times { array << deserializeIntArray }
        return array
    end

    def deserializeIntVector
        return deserializeIntArray
    end

    def deserializeInt2DVector
        return deserializeInt2DArray
    end

    def deserializeCharArray
        return deserializeString.split('')
    end

    def deserializeChar2DArray
        array = []
        deserializeInt.times { array << deserializeCharArray }
        return array
    end

    def deserializeIntSLList
        start = ListNode.new(0)
        tail, seq_no = start, 0
        deserializeInt.times do
            tail.next = ListNode.new(deserializeInt)
            tail = tail.next
            tail._seq_no = (seq_no += 1)
        end
        return start.next
    end

    public static List<ListNode> deserializeIntSLListVector(StreamTokenizer tokenizer) throws IOException {
        ArrayList<ListNode> vector = new ArrayList<ListNode>();

        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
            vector.add(deserializeIntSLList(tokenizer));
        }
        return vector;
    }

    public static RandomListNode deserializeIntSLRList(StreamTokenizer tokenizer) throws IOException {
        RandomListNode start = new RandomListNode(0), tail = start;
        HashMap<Integer, RandomListNode> memo = new HashMap<Integer, RandomListNode>();

        int seqNo = 0;
        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size / 2; i++) {
            RandomListNode node = new RandomListNode(deserializeInt(tokenizer));
            memo.put(node.label, node);
            node._seqNo = seqNo++;
            tail.next = node;
            tail = node;
        }

        for (RandomListNode ptr = start.next; ptr != null; ptr = ptr.next) {
            tokenizer.nextToken();
            if (tokenizer.ttype != '#') {
                ptr.random = memo.get((int) tokenizer.nval);
            }
        }
        return start.next;
    }

    public static TreeNode deserializeIntBinaryTree(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        if (size == 0)
            return null;

        int seqNo = 0;
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        list.add(new TreeNode(0));
        for (int i = 0, f = 1, ptr = 0; i < size; i++) {
            TreeNode newNode = null;
            tokenizer.nextToken();
            if (tokenizer.ttype != '#') {
                newNode = new TreeNode((int) tokenizer.nval);
                newNode._seqNo = seqNo++;
                list.add(newNode);
            }
            list.get(ptr).left = (f == 0) ? newNode : list.get(ptr).left;
            list.get(ptr).right = (f == 1) ? newNode : list.get(ptr).right;
            ptr += (f = f ^ 1) == 0 ? 1 : 0;
        }
        return list.get(1);
    }

    public static List<TreeNode> deserializeIntBinaryTreeVector(StreamTokenizer tokenizer) throws IOException {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();

        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
            list.add(deserializeIntBinaryTree(tokenizer));
        }
        return list;
    }

    public static TreeLinkNode deserializeIntLinkedBinaryTree(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        if (size == 0)
            return null;

        int seqNo = 0;
        ArrayList<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
        list.add(new TreeLinkNode(0));
        for (int i = 0, f = 1, ptr = 0; i < size; i++) {
            TreeLinkNode newNode = null;
            tokenizer.nextToken();
            if (tokenizer.ttype != '#') {
                newNode = new TreeLinkNode((int) tokenizer.nval);
                newNode._seqNo = seqNo++;
                list.add(newNode);
            }
            list.get(ptr).left = (f == 0) ? newNode : list.get(ptr).left;
            list.get(ptr).right = (f == 1) ? newNode : list.get(ptr).right;
            ptr += (f = f ^ 1) == 0 ? 1 : 0;
        }
        return list.get(1);
    }

    public static UndirectedGraphNode deserializeIntUDGraph(StreamTokenizer tokenizer) throws IOException {
        UndirectedGraphNode graph = null;
        HashMap<Integer, UndirectedGraphNode> memo = new HashMap<Integer, UndirectedGraphNode>();

        int seqNo = 0;
        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
            String nodeStr = deserializeString(tokenizer);
            String[] nodes = nodeStr.split(",");

            int nodeLabel = Integer.parseInt(nodes[0]);
            UndirectedGraphNode node = memo.get(nodeLabel);
            if (node == null) {
                node = new UndirectedGraphNode(nodeLabel);
                node._seqNo = seqNo++;
                memo.put(nodeLabel, node);
            }
            if (i == 0)
                graph = node;

            for (int j = 1; j < nodes.length; j++) {
                nodeLabel = Integer.parseInt(nodes[j]);
                UndirectedGraphNode neighbor = memo.get(nodeLabel);
                if (neighbor == null) {
                    neighbor = new UndirectedGraphNode(nodeLabel);
                    neighbor._seqNo = seqNo++;
                    memo.put(nodeLabel, neighbor);
                }

                node.neighbors.add(neighbor);
            }
        }
        return graph;
    }

    public static Point deserializeIPoint(StreamTokenizer tokenizer) throws IOException {
        int x = deserializeInt(tokenizer);
        int y = deserializeInt(tokenizer);

        return new Point(x, y);
    }

    public static Point[] deserializeIPointArray(StreamTokenizer tokenizer) throws IOException {
        int cnt = deserializeInt(tokenizer);

        Point[] array = new Point[cnt];
        for (int i = 0; i < cnt; i++) {
            array[i] = deserializeIPoint(tokenizer);
        }
        return array;
    }

    public static Interval deserializeIInterval(StreamTokenizer tokenizer) throws IOException {
        int start = deserializeInt(tokenizer);
        int end = deserializeInt(tokenizer);

        return new Interval(start, end);
    }

    public static List<Interval> deserializeIIntervalVector(StreamTokenizer tokenizer) throws IOException {
        int cnt = deserializeInt(tokenizer);

        ArrayList<Interval> vector = new ArrayList<Interval>();
        for (int i = 0; i < cnt; i++) {
            vector.add(deserializeIInterval(tokenizer));
        }
        return vector;
    }
end
