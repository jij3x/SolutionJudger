public class Solution {
    
public ListNode removeElements(ListNode head, int val) {
	
	if(head == null){
		return null;
	}
	
	ListNode newHead = null;
	ListNode pt = null;
	ListNode curr = head;
	
	while(curr != null) {
		if(curr.val != val){
			if(newHead == null){
				pt = curr;
				newHead = curr;
			} else {
				pt.next = curr;
				pt = pt.next;
			}
		}
		
		curr = curr.next;
	}
	
	if(pt != null)
		pt.next = null;
	
	return newHead;
}
}