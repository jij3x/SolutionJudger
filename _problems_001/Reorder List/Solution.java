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
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        ListNode now = head, next;
        while (now != null) {
            next = now.next;
            now.next = newHead;
            newHead = now;
            now = next;
        }
        return newHead;
    }
    public void reorderList(ListNode head) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (head == null || head.next == null) return;
        ListNode preMid, mid = head, two = head;
        do {
            preMid = mid;
            mid = mid.next;
            two = two.next;
            if (two != null) two = two.next;
        } while (two != null);
        preMid.next = null;
        mid = reverseList(mid);
        ListNode now = head, next, midNext;
        while (mid != null) {
            next = now.next;
            midNext = mid.next;
            now.next = mid;
            mid.next = next;
            mid = midNext;
            now = next;
        }
    }
}
