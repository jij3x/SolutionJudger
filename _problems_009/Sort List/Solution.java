public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) { return head; }
        ListNode middle = getMiddle(head);      //get the middle of the list
        ListNode sHalf = middle.next; middle.next = null;   //split the list into two halfs
    
        return merge(sortList(head), sortList(sHalf));  //recurse on that
    }
    
    // Merge subroutine to merge two sorted lists
    public ListNode merge(ListNode a, ListNode b) {
        ListNode dummyHead, curr; dummyHead = new ListNode(0); curr = dummyHead;
        while(a != null && b != null) {
            if(a.val <= b.val) { curr.next = a; a = a.next; }
            else { curr.next = b; b = b.next; }
            curr = curr.next;
        }
        curr.next = (a == null) ? b : a;
        return dummyHead.next;
    }
    
    // Finding the middle element of the list for splitting
    public ListNode getMiddle(ListNode head) {
        if (head == null) { return head; }
        ListNode slow, fast; slow = fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next; fast = fast.next.next;
        }
        return slow;
    }
}