import java.util.*;

public class Helper {

    /**
     * create integer stream
     */
    public static List<Integer> createIntStream() {
        return new ArrayList<Integer>();
    }

    /**
     * :int stream appender
     */
    public static void intVectorAppend(List<Integer> vector, int n) {
        vector.add(n);
    }

    /**
     * "Detect Cycle II" input processor
     */
    public static ListNode cyclizeIntSLList(ListNode node, int x) {
        if (node == null || x == -1)
            return node;

        ListNode tail = node;
        while (tail.next != null) {
            tail = tail.next;
        }

        ListNode ptr = node;
        for (int idx = 0; idx != x; idx++) {
            ptr = ptr.next;
        }

        tail.next = ptr;
        return node;
    }

    /**
     * "Detect Cycle II" output processor
     */
    public static String findIntSLListNodeIndex(ListNode head, ListNode node) {
        if (node == null)
            return "no cycle";

        for (int idx = 0; head != null; head = head.next, idx++) {
            if (head == node)
                return String.format("tail connects to node index %d", idx);
        }
        return "output node is not in the linked list";
    }

    /**
     * "Intersection of Two Linked Lists" input processor
     */
    public static void connectIntSLList(ListNode headA, ListNode headB, int aIdx, int bIdx) {
        ListNode nodeA = headA, prevA = headA;
        for (int i = 0; i < aIdx; i++) {
            prevA = nodeA;
            nodeA = nodeA.next;
        }

        ListNode nodeB = headB;
        for (int i = 0; i < bIdx; i++)
            nodeB = nodeB.next;

        if (nodeA == null || nodeB == null)
            return;

        prevA.next = nodeB;
    }

    /**
     * "Intersection of Two Linked Lists" output processor
     */
    public static String findIntersectionNode(ListNode node) {
        if (node == null)
            return "No intersection";

        return String.format("Intersected at '%d'", node.val);
    }

    /**
     * "Read N Characters Given Read4 II" input processor
     */
    public static Reader4 createReader4Instance(String s) {
        return new Reader4(s);
    }

}