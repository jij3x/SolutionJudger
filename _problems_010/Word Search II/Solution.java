public class Solution {
    public List<String> boggle(char[][] board, String[] words) {
        Node root = new Node();
        for (String word : words) {
            int len = word.length();
            Node cur = root;
            for (int i = 0; i < len; i++) {
                char c = word.charAt(i);
                if (!cur.map.containsKey(c)) {
                    Node newNode = new Node();
                    cur.map.put(c, newNode);
                }
                cur = cur.map.get(c);
                if (i == len - 1) {
                    cur.isEnd = true;
                }
            }
        }
        
        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                helper(board, i, j, root, sb, res);
            }
        }
        List<String> result = new ArrayList<>();
        result.addAll(res);
        return result;
    }
    
    public void helper(char[][] board, int i, int j, Node cur, StringBuilder sb, Set<String> res) {
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || !cur.map.containsKey(board[i][j])) {
            return;
        }
        char c = board[i][j];
        sb.append(c);
        Node next = cur.map.get(c);
        if (next.isEnd) {
            res.add(sb.toString());
        }
        board[i][j] = '#';
        helper(board, i - 1, j, next, sb, res);
        helper(board, i + 1, j, next, sb, res);
        helper(board, i, j - 1, next, sb, res);
        helper(board, i, j + 1, next, sb, res);
        board[i][j] = c;
        sb.deleteCharAt(sb.length() - 1);
    }
    
    public class Node {
        public Node() {
            map = new HashMap<>();
            isEnd = false;
        }
        
        Map<Character, Node> map;
        boolean isEnd;
    }
}