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

    public static String serializeInt(int n) {
        return Integer.toString(n);
    }

    public static String serializeDouble(double n) {
        return String.format("%.5f", n);
    }

    public static String serializeString(String s) {
        return "\"" + s + "\"";
    }

    public static String serializeStringArray(String[] array) {
        if (array == null || array.length == 0)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            r.append(i > 0 ? ",\"" : "\"").append(array[i]).append("\"");
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeStringVector(List<String> vector) {
        if (vector == null || vector.size() == 0)
            return "[]";

        StringBuilder r = new StringBuilder();
        for (String str : vector) {
            r.append(",\"").append(str).append("\"");
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
        Collections.sort(words);

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
        int i = 0;
        for (List<Integer> v : vector) {
            r.append(i++ > 0 ? "," : "").append(serializeIntVector(v));
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
            r.append(",").append(Integer.toString(node.val));
        }
        r.append("]");
        return r.toString();
    }

    public static String serializeIntBinaryTree(TreeNode node) {
        if (node == null)
            return "[]";

        StringBuilder r = new StringBuilder("[");
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.add(node);
        r.append(Integer.toString(node.val));
        while (!que.isEmpty()) {
            TreeNode curr = que.poll();
            if (curr.left == null) {
                r.append(",#");
            } else {
                que.add(curr.left);
                r.append(",").append(Integer.toString(node.left.val));
            }

            if (curr.right == null) {
                r.append(",#");
            } else {
                que.add(curr.right);
                r.append(",").append(Integer.toString(node.right.val));
            }
        }
        r.append("]");
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
            r.append(i++ > 0 ? "," : "").append("[").append(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors)
                r.append(",").append(neighbor.label);
            r.append("]");
        }
        r.append("]");
        return r.toString();
    }

    public static boolean deserializeBoo(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval.equals("true") ? true : false;
    }

    public static int deserializeInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public static double deserializeDouble(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.nval;
    }

    public static String deserializeString(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }

    public static String[] deserializeStringArray(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            array[i] = tokenizer.sval;
        }
        return array;
    }

    public static List<String> deserializeStringVector(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        ArrayList<String> array = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            array.add(tokenizer.sval);
        }
        return array;
    }

    public static List<List<String>> deserializeString2DVector(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        ArrayList<List<String>> array = new ArrayList<List<String>>();
        for (int i = 0; i < size; i++) {
            array.add(deserializeStringVector(tokenizer));
        }
        return array;
    }

    public static Set<String> deserializeStringSet(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            set.add(tokenizer.sval);
        }
        return set;
    }

    public static String deserializeText(StreamTokenizer tokenizer) throws IOException {
        return deserializeString(tokenizer);
    }

    public static int[] deserializeIntArray(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            array[i] = (int) tokenizer.nval;
        }
        return array;
    }

    public static int[][] deserializeInt2DArray(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        int[][] array = new int[size][];
        for (int i = 0; i < size; i++) {
            array[i] = deserializeIntArray(tokenizer);
        }
        return array;
    }

    public static List<Integer> deserializeIntVector(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        List<Integer> vector = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            vector.add((int) tokenizer.nval);
        }
        return vector;
    }

    public static List<List<Integer>> deserializeInt2DVector(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
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
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;

        char[][] arr = new char[size][];
        for (int i = 0; i < size; i++) {
            arr[i] = deserializeCharArray(tokenizer);
        }
        return arr;
    }

    public static ListNode deserializeIntSLList(StreamTokenizer tokenizer) throws IOException {
        ListNode start = new ListNode(0), tail = start;

        int seq = 0;
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            ListNode node = new ListNode((int) tokenizer.nval);
            node._seqNo = seq++;
            tail.next = node;
            tail = node;
        }
        return start.next;
    }

    public static TreeNode deserializeIntBinaryTree(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        if (size == 0)
            return null;

        String nullNode = "#";
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        list.add(new TreeNode(0));
        for (int i = 0, f = 1, ptr = 0; i < size; i++) {
            TreeNode newNode = null;
            tokenizer.nextToken();
            if (!nullNode.equals(tokenizer.sval)) {
                newNode = new TreeNode((int) tokenizer.nval);
                list.add(newNode);
            }
            list.get(ptr).left = (f == 0) ? newNode : list.get(ptr).left;
            list.get(ptr).right = (f == 1) ? newNode : list.get(ptr).right;
            ptr += (f = f ^ 1) == 0 ? 1 : 0;
        }
        return list.get(1);
    }

    public static UndirectedGraphNode deserializeIntUDGraph(StreamTokenizer tokenizer) throws IOException {
        UndirectedGraphNode graph = null;
        HashMap<Integer, UndirectedGraphNode> memo = new HashMap<Integer, UndirectedGraphNode>();

        tokenizer.nextToken();
        int size = (int) tokenizer.nval;
        for (int i = 0; i < size; i++) {
            tokenizer.nextToken();
            String nodeStr = tokenizer.sval;
            String[] nodes = nodeStr.split(",");

            int nodeLabel = Integer.parseInt(nodes[0]);
            UndirectedGraphNode node = memo.get(nodeLabel);
            if (node == null) {
                node = new UndirectedGraphNode(nodeLabel);
                memo.put(nodeLabel, node);
            }
            if (i == 0)
                graph = node;

            for (int j = 1; j < nodes.length; j++) {
                nodeLabel = Integer.parseInt(nodes[j]);
                UndirectedGraphNode neighbor = memo.get(nodeLabel);
                if (neighbor == null) {
                    neighbor = new UndirectedGraphNode(nodeLabel);
                    memo.put(nodeLabel, neighbor);
                }

                node.neighbors.add(neighbor);
            }
        }
        return graph;
    }
}
