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
    public ListNode swapPairs(ListNode head) {
        ListNode p = head;
        ListNode newHead = head;
        if (head != null && head.next != null) {
            newHead = head.next;
        }
        
        ListNode prev = null;
        while (p != null && p.next != null) {
            ListNode next = p.next.next;
            p.next.next = p;
            if (prev != null) {
                prev.next = p.next;
            }
            p.next = next;
            
            prev = p;
            p = next;
        }
        
        return newHead;
    }
}