public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode s1 = head, s2 = head;
        do {
            s1 = s1.next;
            s2 = s2.next;
            if (s2 != null) s2 = s2.next;
        } while (s2 != null && s1 != s2);
        if (s2 == null) return null;
        s1 = head;
        while (s1 != s2) {
            s1 = s1.next;
            s2 = s2.next;
        }
        return s1;
    }
}