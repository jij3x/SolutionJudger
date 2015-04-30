public class Solution {
    public List<List<String>> solveNQueens(int n) {
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        doSolveQueens(0, new int[n], result);
        return result;
    }

    private void doSolveQueens(int level, int[] board, List<List<String>> result) {
        if (level == board.length) {
            ArrayList<String> subResult = new ArrayList<String>();
            for (int i = 0; i < board.length; i++) {
                char[] row = new char[board.length];
                Arrays.fill(row, '.');
                row[board[i]] = 'Q';
                subResult.add(new String(row));
            }
            result.add(subResult);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            boolean valid = true;
            for (int j = level - 1; j >= 0; j--) {
                if (board[j] == i || board[j] == i - (level - j) || board[j] == i + (level - j))
                    valid = false;
            }

            if (valid) {
                board[level] = i;
                doSolveQueens(level + 1, board, result);
            }
        }
    }
}