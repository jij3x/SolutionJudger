require 'prime'

class Solution
  def numSquares(n)
    n /= 4 while n % 4 == 0
    return 4 if n % 8 == 7
    return 3 if n.prime_division.any? { |p, e| p % 4 == 3 && e.odd? }
    (n**0.5).to_i**2 == n ? 1 : 2
  end
end
