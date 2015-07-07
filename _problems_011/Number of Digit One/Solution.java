public class Solution {
    public int countDigitOne(long n) {
        int ans = 0;
    	long base = 1;
    	while (base <= n) {
    		ans += n / 10 / base * base;
    		int digit = (int)(n / base % 10);
    		if (digit > 1) {
    			ans += base;
    		} else if (digit == 1) {
    			ans += 1 + n % base;
    		}
    		base *= 10;
    	}
    	return ans;
    }
}