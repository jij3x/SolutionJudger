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
    
    public ListNode mergeTwoLists(ListNode p, ListNode q){
        ListNode pre_head = new ListNode(0);
        ListNode r = pre_head;
        while(p != null && q != null){
            if(p.val < q.val) {
                r.next = p;
                p = p.next;
            } 
            else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        while(p != null){
            r.next = p;
            p = p.next;
            r = r.next;
        }
        while(q != null){
            r.next = q;
            q = q.next;
            r = r.next;
        }
        return pre_head.next;
    }
    
    public ListNode mergeKLists(List<ListNode> lists) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int last = lists.size() - 1;
        if(last < 0) return null;
        while(last > 0){
            int cur = 0;    
            while(cur < last){
                lists.set(cur, mergeTwoLists(lists.get(cur++), lists.get(last--)));
            }
        }
        return lists.get(0);
    }
}