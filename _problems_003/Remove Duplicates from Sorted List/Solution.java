/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pp = head;
        while (pp != null) {
            ListNode p = pp;
            ListNode p2 = pp.next;
            while (p2 != null && p2.val == p.val) {
                p2 = p2.next;
            }
            if (p2 != p.next) {
                p.next = p2;
            }
            pp = p2;
        }
        return head;
    }
}