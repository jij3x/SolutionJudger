public class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) return;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            q.add(i*m);
            q.add(i*m+m-1);
        }
        for (int i = 1; i < m - 1; i++) {
            q.add(i);
            q.add((n-1)*m+i);
        }
        while (!q.isEmpty()) {
            int p = q.poll();
            int x = p/m, y = p%m;
            if (!isValid(x, y, visited, board)) continue;
            visited[x][y] = true;
            q.add(x*m+y-1);
            q.add(x*m+y+1);
            q.add((x-1)*m+y);
            q.add((x+1)*m+y);
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!visited[x][y] && board[x][y] == 'O') {
                    board[x][y] = 'X';
                }
            }
        }
    }
    boolean isValid(int x, int y, boolean[][] visited, char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (x < 0 || y < 0 || x > n-1 || y > m-1 || visited[x][y] || board[x][y] == 'X')
            return false;
        return true;
    }
}
