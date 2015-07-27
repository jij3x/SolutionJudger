import java.util.*;

public class Helper {

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
     * "Delete Node in a Linked List" input processor
     */
    public static ListNode getNthNode(ListNode head, int n) {
        ListNode ptr = head;
        for (int i = 0; i < n; i++)
            ptr = ptr.next;
        return ptr;
    }

    /**
     * "Lowest Common Ancestor of a Binary Tree" input processor
     */
    public static TreeNode getNthBTNode(TreeNode root, int n) {
        ArrayList<TreeNode> que = new ArrayList<TreeNode>();
        que.add(root);
        int top = 0;
        while (top < que.size()) {
            TreeNode curr = que.get(top++);
            if (curr == null)
                continue;
            que.add(curr.left);
            que.add(curr.right);
            if (que.size() > n)
                return que.get(n);
        }
        return null;
    }

    /**
     * "Lowest Common Ancestor of a Binary Tree" output processor
     */
    public static int getBTNodeVal(TreeNode root) {
        return root.val;
    }

    public static List<Double> newMetrics() {
        ArrayList<Double> r = new ArrayList<Double>();
        for (int i = 0; i < 10; i++)
            r.add(0.0);
        return r;
    }

}