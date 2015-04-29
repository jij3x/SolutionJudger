public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        ListNode p = head;
        for (int i = 0; p != null && i < n; i++) {
            p = p.next;
        }
        ListNode prev = null;
        while (p != null) {
            prev = curr;
            curr = curr.next;
            p = p.next;
        }
        
        if (curr == head) {
            head = head.next;
        } else {
            prev.next = curr.next;
        }
        
        return head;
    }
}