public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = null;
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l3 = l1;
            l1 = l1.next;
        } else {
            l3 = l2;
            l2 = l2.next;
        }
        ListNode iter = l3;
        while (l1 != null && l2 != null) {
            ListNode p;
            if (l1.val < l2.val) {
                p = l1;
                l1 = l1.next;
            } else {
                p = l2;
                l2 = l2.next;
            }
            iter.next = p;
            iter = iter.next;
        }
        while (l1 != null) {
            iter.next = l1;
            iter = iter.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            iter.next = l2;
            iter = iter.next;
            l2 = l2.next;
        }
        return l3;
    }
}