public class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nPrev = curr;
            ListNode nCurr = curr.next;
            ListNode p = dummyHead;
            while (p.next != curr) {
                if (p.val <= curr.val && curr.val <= p.next.val) {
                    ListNode temp = curr.next;
                    ListNode q = p.next;
                    p.next = curr;
                    curr.next = q;
                    prev.next = temp;
                    nPrev = prev;
                    break;
                }
                p = p.next;
            }
            prev = nPrev;
            curr = nCurr;
        }
        return dummyHead.next;
    }
}