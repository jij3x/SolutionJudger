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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 0) return head;
        ListNode p = head;
        for (int i = 0; i < k; i++) {
    		if (p == null) return head;
    		p = p.next;
    	}
    	ListNode rev = reverse(head, k);
    	head.next = reverseKGroup(p, k);
    	return rev;
    }
    ListNode reverse(ListNode head, int n) {
        ListNode curr = head;
        ListNode prev = null;
        for (int i = 0; i < n; i++) {
    		ListNode next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    	}
    	return prev;
    }
}