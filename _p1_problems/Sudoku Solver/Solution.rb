class Solution

  def solveSudoku(board)
    doSolve(board, 0)
  end

  def doSolve(board, idx)
    return true if idx == 9 * 9
    return doSolve(board, idx + 1) if board[idx / 9][idx % 9] != '.'

    getValidNumbers(board, idx).each do |c|
      board[idx / 9][idx % 9] = c
      return true if doSolve(board, idx + 1)
    end
    board[idx / 9][idx % 9] = '.'
    false
  end

  def getValidNumbers(board, idx)
    memo = Set.new
    (0...9).each do |i|
      memo.add(board[idx / 9][i])
      memo.add(board[i][idx % 9])
      memo.add(board[idx / 9 / 3 * 3 + i / 3][idx % 9 / 3 * 3 + i % 3])
    end

    r = []
    ('1'..'9').each do |c|
      r << c unless memo.include?(c)
    end
    r
  end

end
