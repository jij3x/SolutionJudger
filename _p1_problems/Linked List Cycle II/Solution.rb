class Solution
  def detectCycle(head)
    slow = fast = head
    while (!fast.nil? && !fast.next.nil? && !fast.next.next.nil?)
      if ((slow = slow.next) == (fast = fast.next.next))
        while (head != slow)
          head = head.next
          slow = slow.next
        end
        return head
      end
    end
    nil
  end
end
