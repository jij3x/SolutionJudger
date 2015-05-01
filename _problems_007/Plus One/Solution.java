public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] ret;
        if (isAllNines(digits)) {
            ret = new int[n+1];
            ret[0] = 1;
            return ret;
        } else {
            ret = new int[n];
        }
        int carry = 1;
        for (int i = n-1; i >= 0; i--) {
            int sum = digits[i] + carry;
            ret[i] = sum % 10;
            carry  = sum / 10;
        }
        assert(carry == 0);
        return ret;
    }
    boolean isAllNines(int[] digits) {
        int n = digits.length;
        for (int i = 0; i < n; i++) {
            if (digits[i] != 9) return false;
        }
        return true;
    }
}