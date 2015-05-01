public class Solution {
    
    private static boolean[][] isVisited;
    
    public int numIslands(char[][] board) {
        
        int count = 0;
        
        int r = board.length;
        if (r == 0)
			return 0;

		int c = board[0].length;
        if (c == 0)
			return 0;

		isVisited = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!isVisited[i][j]) {
					if (board[i][j] == '1') {
						visit(i, j, board);
						count++;
					}
				}
			}
		}

		return count;
	}

	private static void visit(int i, int j, char[][] board) {

		int r = board.length;
		int c = board[0].length;

		if (i < 0 || j < 0 || i > r - 1 || j > c - 1)
			return;
		else {
			if (!isVisited[i][j]) {
				isVisited[i][j] = true;
				if (board[i][j] == '1') {
					visit(i - 1, j, board);
					visit(i + 1, j, board);
					visit(i, j - 1, board);
					visit(i, j + 1, board);
				}
			} else
				return;
		}
	}
}