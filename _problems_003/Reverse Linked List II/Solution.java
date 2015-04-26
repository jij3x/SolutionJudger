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
    ListNode prev, curr;
    public ListNode reverseBetween(ListNode head, int m, int n) {
        prev = null;
        curr = head;
        for (int i = 1; i < m; i++) {
            step();
        }
        ListNode start = prev;
        ListNode end = curr;
        
        step();
        for (int i = m; i < n; i++) {
            ListNode temp = prev;
            step();
            prev.next = temp;
        }
        
        if (start != null) {
            start.next = prev;
        } else {
            head = prev;
        }
        end.next = curr;
        
        return head;
    }
    void step() {
        ListNode next = curr.next;
        prev = curr;
        curr = next;
    }
}