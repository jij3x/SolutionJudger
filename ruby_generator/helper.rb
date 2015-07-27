class Helper

  class << self

    # "Detect Cycle II" input processor
    def cyclizeIntSLList(node, x)
      return node if node.nil? or x == -1

      tail = ptr = node
      tail = tail.next until tail.next.nil?
      x.times { ptr = ptr.next }
      tail.next = ptr
      node
    end

    # "Detect Cycle II" output processor
    def findIntSLListNodeIndex(head, node)
      return 'no cycle' if node.nil?

      i = 0
      until head.nil? do
        return "tail connects to node index #{i}" if head == node
        head = head.next
        i += 1
      end
      'output node is not in the linked list'
    end

    # "Intersection of Two Linked Lists" input processor
    def connectIntSLList(headA, headB, aIdx, bIdx)
      nodeA, prevA = headA, headA
      aIdx.times do
        prevA = nodeA
        nodeA = nodeA.next
      end

      nodeB = headB
      bIdx.times { nodeB = nodeB.next }

      return if nodeA.nil? or nodeB.nil?
      prevA.next = nodeB
    end

    # "Intersection of Two Linked Lists" output processor
    def findIntersectionNode(node)
      (node.nil? ? 'No intersection' : "Intersected at '#{node.val}'")
    end

    # "Delete Node in a Linked List" input processor
    def getNthNode(head, n)
      ptr = head
      (0...n).each { |i| ptr = ptr.next }
      ptr
    end

    # "Lowest Common Ancestor of a Binary Tree" input processor
    def getNthBTNode(root, n)
      que, top = [root], 0
      while top < que.size()
        curr = que[top]
        top += 1
        next if curr.nil?
        que << curr.left
        que << curr.right
        return que[n] if que.size() > n
      end
    end

    # "Lowest Common Ancestor of a Binary Tree" output processor
    def getBTNodeVal(root)
      root.val
    end

    def newMetrics()
      []
    end

  end

end
