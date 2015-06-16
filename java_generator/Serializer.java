import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Serializer {
    public static String serializeBool(boolean b) {
        return b ? "true" : "false";
    }

    public static String serializeBoolVector(List<Boolean> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (boolean b : vector) {
            r.append(",").append(serializeBool(b));
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeInt(int n) {
        return Integer.toString(n);
    }

    public static String serializeUnsignedInt(int n) {
        return Long.toString(n & (-1L >>> 32));
    }

    public static String serializeDouble(double n) {
        return String.format("%.5f", n);
    }

    public static String serializeString(String s) {
        return "\"" + s + "\"";
    }

    public static String serializeMutableString(StringBuilder s) {
        return serializeString(s.toString());
    }

    public static String serializeStringArray(String[] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            r.append(i > 0 ? "," : "").append(serializeString(array[i]));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeStringVector(List<String> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (String str : vector) {
            r.append(",").append(serializeString(str));
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeString2DVector(List<List<String>> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        int i = 0;
        for (List<String> v : vector) {
            r.append(i++ > 0 ? "," : "").append(serializeStringVector(v));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeStringSet(Set<String> set) {
        if (set.size() == 0)
            return "[]";

        ArrayList<String> words = new ArrayList<String>();
        for (String str : set) {
            words.add(str);
        }

        StringBuilder r = new StringBuilder("[");
        int i = 0;
        for (String word : words) {
            r.append(i++ > 0 ? ",\"" : "\"").append(word).append("\"");
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeText(String s) {
        return s;
    }

    public static String serializeIntArray(int[] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        r.append(Integer.toString(array[0]));
        for (int i = 1; i < array.length; i++) {
            r.append(",").append(Integer.toString(array[i]));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeInt2DArray(int[][] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        r.append(serializeIntArray(array[0]));
        for (int i = 1; i < array.length; i++) {
            r.append(",").append(serializeIntArray(array[i]));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIntVector(List<Integer> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (int n : vector) {
            r.append(",").append(Integer.toString(n));
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeInt2DVector(List<List<Integer>> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (List<Integer> v : vector) {
            r.append(",").append(serializeIntVector(v));
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeCharArray(char[] array) {
        if (array == null || array.length == 0)
            return "\"\"";

        StringBuilder r = new StringBuilder(new String(array));
        r.insert(0, "\"").append("\"");
        return r.toString();
    }

    public static String serializeChar2DArray(char[][] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        r.append(serializeCharArray(array[0]));
        for (int i = 1; i < array.length; i++)
            r.append(",").append(serializeCharArray(array[i]));
        r.append("]");
        return r.toString();
    }

    public static String serializeIntSLList(ListNode node) {
        if (node == null)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        r.append(node._seqNo).append(",").append(node.val);
        while ((node = node.next) != null) {
            r.append(",").append(node._seqNo).append(",").append(node.val);
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIntSLListVector(List<ListNode> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (ListNode node : vector) {
            r.append(",").append(serializeIntSLList(node));
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeIntSLRList(RandomListNode node) {
        if (node == null)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (RandomListNode ptr = node; ptr != null; ptr = ptr.next) {
            r.append(",").append(ptr._seqNo).append(",").append(ptr.label);
        }
        for (RandomListNode ptr = node; ptr != null; ptr = ptr.next) {
            r.append(",").append(ptr.random == null ? "#" : ptr.random.label);
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeIntBinaryTree(TreeNode node) {
        if (node == null)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.add(node);
        r.append(node._seqNo).append(",").append(node.val);
        while (!que.isEmpty()) {
            TreeNode curr = que.poll();
            if (curr.left == null) {
                r.append(",#");
            } else {
                que.add(curr.left);
                r.append(",").append(curr.left._seqNo).append(",").append(curr.left.val);
            }

            if (curr.right == null) {
                r.append(",#");
            } else {
                que.add(curr.right);
                r.append(",").append(curr.right._seqNo).append(",").append(curr.right.val);
            }
        }
        int len = r.length();
        while (r.charAt(len - 1) == '#' && r.charAt(len - 2) == ',')
            len -= 2;
        r.setLength(len);
        r.append("]");
        return r.toString();
    }

    public static String serializeIntBinaryTreeVector(List<TreeNode> list) {
        if (list == null || list.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (TreeNode tree : list) {
            r.append(",").append(serializeIntBinaryTree(tree));
        }
        r.setCharAt(0, '[');
        r.append("]");
        return r.toString();
    }

    public static String serializeIntLinkedBinaryTree(TreeLinkNode node) {
        if (node == null)
            return "[]";

        StringBuilder r = new StringBuilder();
        while (node != null) {
            TreeLinkNode ptr = node;
            node = null;
            while (ptr != null) {
                if ((ptr.left != null || ptr.right != null) && node == null)
                    node = ptr.left == null ? ptr.right : ptr.left;
                r.append(',').append(ptr._seqNo).append(',').append(ptr.val);
                ptr = ptr.next;
            }
            r.append(',').append('#');
        }
        r.setCharAt(0, '[');
        r.append(']');
        return r.toString();
    }

    public static String serializeIntUDGraph(UndirectedGraphNode graph) {
        if (graph == null)
            return "[]";

        HashSet<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        visited.add(graph);
        Queue<UndirectedGraphNode> que = new LinkedList<UndirectedGraphNode>();
        que.add(graph);
        while (!que.isEmpty()) {
            UndirectedGraphNode curr = que.poll();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    que.add(neighbor);
                }
            }
        }

        StringBuilder r = new StringBuilder("[");
        int i = 0;
        for (UndirectedGraphNode node : visited) {
            r.append(i++ > 0 ? "," : "").append("[").append(node._seqNo).append(",").append(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors)
                r.append(",").append(neighbor._seqNo).append(",").append(neighbor.label);
            r.append("]");
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIPoint(Point point) {
        if (point == null)
            return "[]";

        return String.format("[%d,%d,%d]", point._seqNo, point.x, point.y);
    }

    public static String serializeIPointArray(Point[] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            r.append(i == 0 ? "" : ",").append(serializeIPoint(array[i]));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIInterval(Interval interval) {
        if (interval == null)
            return "[]";

        return String.format("[%d,%d,%d]", interval._seqNo, interval.start, interval.end);
    }

    public static String serializeIIntervalVector(List<Interval> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        for (int i = 0; i < vector.size(); i++) {
            r.append(i == 0 ? "" : ",").append(serializeIInterval(vector.get(i)));
        }
        r.append("]");
        return r.toString();
    }

    public static StreamTokenizer getStreamToken(StreamTokenizer tokenizer) throws IOException {
        return tokenizer;
    }

    public static boolean deserializeBool(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval.equals("true") ? true : false;
    }

    public static List<Boolean> deserializeBoolVector(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        List<Boolean> vector = new ArrayList<Boolean>();
        for (int i = 0; i < size; i++) {
            vector.add(deserializeBool(tokenizer));
        }
        return vector;
    }

    public static int deserializeInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public static int deserializeUnsignedInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) ((long) tokenizer.nval);
    }

    public static double deserializeDouble(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.nval;
    }

    public static String deserializeString(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }

    public static StringBuilder deserializeMutableString(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return new StringBuilder(tokenizer.sval);
    }

    public static String[] deserializeStringArray(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = deserializeString(tokenizer);
        }
        return array;
    }

    public static List<String> deserializeStringVector(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            array.add(deserializeString(tokenizer));
        }
        return array;
    }

    public static List<List<String>> deserializeString2DVector(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        ArrayList<List<String>> array = new ArrayList<List<String>>();
        for (int i = 0; i < size; i++) {
            array.add(deserializeStringVector(tokenizer));
        }
        return array;
    }

    public static Set<String> deserializeStringSet(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < size; i++) {
            set.add(deserializeString(tokenizer));
        }
        return set;
    }

    public static String deserializeText(StreamTokenizer tokenizer) throws IOException {
        return deserializeString(tokenizer);
    }

    public static int[] deserializeIntArray(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = deserializeInt(tokenizer);
        }
        return array;
    }

    public static int[][] deserializeInt2DArray(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        int[][] array = new int[size][];
        for (int i = 0; i < size; i++) {
            array[i] = deserializeIntArray(tokenizer);
        }
        return array;
    }

    public static List<Integer> deserializeIntVector(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        List<Integer> vector = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            vector.add(deserializeInt(tokenizer));
        }
        return vector;
    }

    public static List<List<Integer>> deserializeInt2DVector(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        List<List<Integer>> vector = new ArrayList<List<Integer>>();
        for (int i = 0; i < size; i++) {
            vector.add(deserializeIntVector(tokenizer));
        }
        return vector;
    }

    public static char[] deserializeCharArray(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval.toCharArray();
    }

    public static char[][] deserializeChar2DArray(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);

        char[][] arr = new char[size][];
        for (int i = 0; i < size; i++) {
            arr[i] = deserializeCharArray(tokenizer);
        }
        return arr;
    }

    public static ListNode deserializeIntSLList(StreamTokenizer tokenizer) throws IOException {
        ListNode start = new ListNode(0), tail = start;

        int seqNo = 0;
        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
            ListNode node = new ListNode(deserializeInt(tokenizer));
            node._seqNo = seqNo++;
            tail.next = node;
            tail = node;
        }
        return start.next;
    }

    public static List<ListNode> deserializeIntSLListVector(StreamTokenizer tokenizer) throws IOException {
        ArrayList<ListNode> vector = new ArrayList<ListNode>();

        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
            vector.add(deserializeIntSLList(tokenizer));
        }
        return vector;
    }

    public static RandomListNode deserializeIntSLRList(StreamTokenizer tokenizer) throws IOException {
        RandomListNode start = new RandomListNode(0), tail = start;
        HashMap<Integer, RandomListNode> memo = new HashMap<Integer, RandomListNode>();

        int seqNo = 0;
        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size / 2; i++) {
            RandomListNode node = new RandomListNode(deserializeInt(tokenizer));
            memo.put(node.label, node);
            node._seqNo = seqNo++;
            tail.next = node;
            tail = node;
        }

        for (RandomListNode ptr = start.next; ptr != null; ptr = ptr.next) {
            tokenizer.nextToken();
            if (tokenizer.ttype != '#') {
                ptr.random = memo.get((int) tokenizer.nval);
            }
        }
        return start.next;
    }

    public static TreeNode deserializeIntBinaryTree(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        if (size == 0)
            return null;

        int seqNo = 0;
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        list.add(new TreeNode(0));
        for (int i = 0, f = 1, ptr = 0; i < size; i++) {
            TreeNode newNode = null;
            tokenizer.nextToken();
            if (tokenizer.ttype != '#') {
                newNode = new TreeNode((int) tokenizer.nval);
                newNode._seqNo = seqNo++;
                list.add(newNode);
            }
            list.get(ptr).left = (f == 0) ? newNode : list.get(ptr).left;
            list.get(ptr).right = (f == 1) ? newNode : list.get(ptr).right;
            ptr += (f ^= 1) == 0 ? 1 : 0;
        }
        return list.get(1);
    }

    public static List<TreeNode> deserializeIntBinaryTreeVector(StreamTokenizer tokenizer) throws IOException {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();

        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
            list.add(deserializeIntBinaryTree(tokenizer));
        }
        return list;
    }

    public static TreeLinkNode deserializeIntLinkedBinaryTree(StreamTokenizer tokenizer) throws IOException {
        int size = deserializeInt(tokenizer);
        if (size == 0)
            return null;

        int seqNo = 0;
        ArrayList<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
        list.add(new TreeLinkNode(0));
        for (int i = 0, f = 1, ptr = 0; i < size; i++) {
            TreeLinkNode newNode = null;
            tokenizer.nextToken();
            if (tokenizer.ttype != '#') {
                newNode = new TreeLinkNode((int) tokenizer.nval);
                newNode._seqNo = seqNo++;
                list.add(newNode);
            }
            list.get(ptr).left = (f == 0) ? newNode : list.get(ptr).left;
            list.get(ptr).right = (f == 1) ? newNode : list.get(ptr).right;
            ptr += (f ^= 1) == 0 ? 1 : 0;
        }
        return list.get(1);
    }

    public static UndirectedGraphNode deserializeIntUDGraph(StreamTokenizer tokenizer) throws IOException {
        UndirectedGraphNode graph = null;
        HashMap<Integer, UndirectedGraphNode> memo = new HashMap<Integer, UndirectedGraphNode>();

        int seqNo = 0;
        int size = deserializeInt(tokenizer);
        for (int i = 0; i < size; i++) {
        	int[] nodes = deserializeIntArray(tokenizer);
            UndirectedGraphNode node = memo.get(nodes[0]);
            if (node == null) {
                node = new UndirectedGraphNode(nodes[0]);
                node._seqNo = seqNo++;
                memo.put(nodes[0], node);
            }
            if (i == 0)
                graph = node;

            for (int j = 1; j < nodes.length; j++) {
                UndirectedGraphNode neighbor = memo.get(nodes[j]);
                if (neighbor == null) {
                    neighbor = new UndirectedGraphNode(nodes[j]);
                    neighbor._seqNo = seqNo++;
                    memo.put(nodes[j], neighbor);
                }

                node.neighbors.add(neighbor);
            }
        }
        return graph;
    }

    public static Point deserializeIPoint(StreamTokenizer tokenizer) throws IOException {
        int x = deserializeInt(tokenizer);
        int y = deserializeInt(tokenizer);

        return new Point(x, y);
    }

    public static Point[] deserializeIPointArray(StreamTokenizer tokenizer) throws IOException {
        int cnt = deserializeInt(tokenizer);

        Point[] array = new Point[cnt];
        for (int i = 0; i < cnt; i++) {
            array[i] = deserializeIPoint(tokenizer);
        }
        return array;
    }

    public static Interval deserializeIInterval(StreamTokenizer tokenizer) throws IOException {
        int start = deserializeInt(tokenizer);
        int end = deserializeInt(tokenizer);

        return new Interval(start, end);
    }

    public static List<Interval> deserializeIIntervalVector(StreamTokenizer tokenizer) throws IOException {
        int cnt = deserializeInt(tokenizer);

        ArrayList<Interval> vector = new ArrayList<Interval>();
        for (int i = 0; i < cnt; i++) {
            vector.add(deserializeIInterval(tokenizer));
        }
        return vector;
    }
}
