/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode s1 = head, s2 = head;
        do {
            s1 = s1.next;
            s2 = s2.next;
            if (s2 != null) s2 = s2.next;
        } while (s2 != null && s1 != s2);
        return s2 != null;
    }
}
