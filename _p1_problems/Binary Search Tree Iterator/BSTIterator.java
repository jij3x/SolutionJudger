public class LRUCache {
    private class DoublyLinkedNode {
        int key, value;
        DoublyLinkedNode prev, next;

        DoublyLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, DoublyLinkedNode> map;
    private int capacity, size;
    private DoublyLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<Integer, DoublyLinkedNode>();
        this.capacity = capacity;
        head = new DoublyLinkedNode(0, 0);
        tail = new DoublyLinkedNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    private void refresh(DoublyLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        DoublyLinkedNode curr = map.get(key);
        refresh(curr);
        return curr.value;
    }

    public void set(int key, int value) {
        DoublyLinkedNode curr = map.get(key);
        if (curr != null) {
            curr.value = value;
            refresh(curr);
            return;
        }

        curr = new DoublyLinkedNode(key, value);
        map.put(key, curr);
        curr.next = head.next;
        head.next.prev = curr;
        head.next = curr;
        curr.prev = head;
        if (++size > capacity) {
            size = capacity;
            map.remove(tail.prev.key);
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
        }
    }
}