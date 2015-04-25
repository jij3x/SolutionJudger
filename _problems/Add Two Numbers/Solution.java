public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
        if (carry == 1)
            return new ListNode(1);
        else
            return null;
        }
        
        int dig1 = (l1 != null ? l1.val : 0);
        int dig2 = (l2 != null ? l2.val : 0);
        
        int sum = dig1+dig2+carry;
        if (sum >= 10) {
            sum -= 10;
            carry = 1;
        } else {
            carry = 0;
        }
        
        ListNode l3 = new ListNode(sum);
        l3.next = addTwoNumbers((l1 != null ? l1.next : null), 
                                 (l2 != null ? l2.next : null),
                                 carry);
        return l3;
    }
    
    ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Start typing your C/C++ solution below
        // DO NOT write int main() function      
        return addTwoNumbers(l1, l2, 0);
    }
}