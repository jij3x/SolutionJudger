import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Helper {

    /**
     * "Detect Cycle" input processor
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
     * "Detect Cycle" output processor
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
     * "Clone Graph" output processor
     *
     * Returns empty string if two input graphs are deeply cloned.
     */
    public static String checkDeepClonedIntUDGraph(UndirectedGraphNode n1, UndirectedGraphNode n2) {
        if (n1 == null || n2 == null)
            return "";

        HashSet<UndirectedGraphNode> hash = new HashSet<UndirectedGraphNode>();
        ArrayList<UndirectedGraphNode> que = new ArrayList<UndirectedGraphNode>();
        int head = 0;
        hash.add(n1);
        que.add(n1);
        while (head < que.size()) {
            UndirectedGraphNode now = que.get(head++);
            for (UndirectedGraphNode neighbor : now.neighbors) {
                if (hash.add(neighbor)) {
                    que.add(neighbor);
                }
            }
        }

        HashSet<UndirectedGraphNode> hashB = new HashSet<UndirectedGraphNode>();
        que.clear();
        head = 0;
        hashB.add(n2);
        que.add(n2);
        while (head < que.size()) {
            UndirectedGraphNode now = que.get(head++);
            if (hash.contains(now))
                return String.format("Node with label %d was not copied but a reference to the original one.",
                        now.label);
            for (UndirectedGraphNode neighbor : now.neighbors) {
                if (hashB.add(neighbor)) {
                    que.add(neighbor);
                }
            }
        }
        return "";
    }

}