public class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[512][512];
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (exist(r, c, m, n, visited, board, word, new StringBuffer(""))) return true;
            }
        }
        return false;
    }
    boolean exist(int row, int col, int m, int n, boolean[][] visited, char[][] board, String word, StringBuffer s) {
        if (row < 0 || row >= m || col < 0 || col >= n) return false;
        if (visited[row][col]) return false;
        
        s.append(board[row][col]);
        
        
        if (s.charAt(s.length()-1) != word.charAt(s.length()-1)) {
            s.deleteCharAt(s.length()-1);
            return false;
        }
        if (s.length() == word.length()) {
            s.deleteCharAt(s.length()-1);
            return true;
        }
        
        visited[row][col] = true;
        
        boolean ret = exist(row-1, col,   m, n, visited, board, word, s) ||
                   exist(row+1, col,   m, n, visited, board, word, s) ||
                   exist(row,   col-1, m, n, visited, board, word, s) ||
                   exist(row,   col+1, m, n, visited, board, word, s);
                   
        visited[row][col] = false;
        s.deleteCharAt(s.length()-1);
        
        return ret;
    }
}