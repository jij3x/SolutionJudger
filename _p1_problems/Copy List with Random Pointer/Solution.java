public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        RandomListNode next, now = head;
        while (now != null) {
          next = now.next;
          RandomListNode newNow = new RandomListNode(now.label);
          now.next = newNow;
          newNow.next = next;
          now = next;
        }
        now = head;
        while (now != null) {
          if (now.random != null) {
            now.next.random = now.random.next;
          } else {
            now.next.random = null;
          }
          now = now.next.next;
        }
        RandomListNode newHead = null;
        now = head;
        while (now != null) {
          RandomListNode newNow = now.next;
          if (newHead == null) newHead = newNow;
          now.next = now.next.next;
          if (newNow.next != null) newNow.next = newNow.next.next;
          else newNow.next = null;
          now = now.next;
        }
        return newHead;
    }
}