public class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    public boolean solve(char[][] board) {
        Point nextEmpty = getNextEmpty(board);
        if (nextEmpty == null) {
            return true;
        }
        int r = nextEmpty.x, c = nextEmpty.y;
        for (char digit = '1'; digit <= '9'; digit++) {
            board[r][c] = digit;
            if (isValidSudoku(board)) {
                if (solve(board)) return true;
            }
        }
        board[r][c] = '.';
        return false;
    }
   
    boolean valid(char[][] board, int sRow, int sCol, int eRow, int eCol) {
        boolean[] used = new boolean[10];
        for (int r = sRow; r <= eRow; r++) {
            for (int c = sCol; c <= eCol; c++) {
                if (board[r][c] == '.') continue;
                if (used[board[r][c]-'0']) return false;
                used[board[r][c]-'0'] = true;
            }
        }
        return true;
    }
    public boolean isValidSudoku(char[][] board) {
        for (int r = 0; r < 9; r++) {   // check each row
            if (!valid(board, r, 0, r, 8)) return false;
        }
        for (int c = 0; c < 9; c++) {   // check each col
            if (!valid(board, 0, c, 8, c)) return false;
        }
        for (int i = 0; i < 3; i++) {   // check each section
            for (int j = 0; j < 3; j++) {
                if (!valid(board, i*3, j*3, i*3+2, j*3+2)) return false;
            }
        }
        return true;
    }
    Point getNextEmpty(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    return new Point(r, c);
                }
            }
        }
        return null;
    }
    private class Point {
        int x;
        int y;       
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
