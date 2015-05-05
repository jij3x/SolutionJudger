public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // No intersection if either list is empty
        if (headA==null || headB==null) return null;  
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        ListNode endA = null;   // Last nodes of the lists
        ListNode endB = null;
        while (pA!=pB)  // As long as pA does not coincide with pB
        {
            if (pA.next!=null)       
                pA = pA.next;  
            else {              // If pA is already the end of listA
                endA = pA;      // ** then redirect pA to the beginning of listB
                pA = headB;     // and memorize the last elem of listA 
            }
            if (pB.next!=null) 
                pB = pB.next;
            else {
                endB = pB;
                pB = headA;
            }
            // If the last elem of the two lists have been found
            // and they are not equal,
            // then there is absolutely no intersection.
            if (endA!=null && endB!=null && endA!=endB) return null;
        }
        return pA;
    }
};