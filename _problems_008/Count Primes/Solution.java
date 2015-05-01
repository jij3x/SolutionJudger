// Sieve of Eratothenes algorithm.
public class Solution {
    public int countPrimes(int n) {
      if (n <= 2) return 0;
      boolean[] prime = new boolean[n + 1];
      int i, count = n - 1;
      for (i = 2; i <= n; i++)
        prime[i] = true;

      int limit = (int)Math.sqrt(n);
      for (i = 2; i <= limit; i++) {
        if (prime[i]) {
          for (int j = i * i; j < n; j += i) {
            if (prime[j]) count--;
            prime[j] = false;
          }
        }
      }
      return count - 1;
	}
}