class Solution
  def getIntersectionNode(headA, headB)
    return nil if headA.nil? or headB.nil?

    pA, endA, pB, endB = headA, nil, headB, nil
    while pA != pB do
      if !pA.next.nil? then
        pA = pA.next
      else
        endA = pA
        pA = headB
      end

      if !pB.next.nil? then
        pB = pB.next
      else
        endB = pB
        pB = headA
      end

      return nil if !endA.nil? and !endB.nil? and endA != endB
    end
    pA
  end
end
