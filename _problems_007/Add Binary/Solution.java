public class Solution {
    public String addBinary(String a, String b) {
        int n1 = a.length();
        int n2 = b.length();
        int len = Math.max(n1, n2) + 1;
        char[] out = new char[len];
        
        int carry = 0;
        for (int i = 0; i < len; i++) {
            int dig1 = (i < n1) ? a.charAt(n1 - i - 1) - '0' : 0;
            int dig2 = (i < n2) ? b.charAt(n2 - i - 1) - '0' : 0;
            int sum = dig1 ^ dig2 ^ carry;
            carry = (dig1 & dig2) | (dig1 & carry) | (dig2 & carry);
            out[len - i - 1] = (char)(sum + '0');
        }
        return trimZeroes(out);
    }
    String trimZeroes(char[] out) {
        int n = out.length;
        if (n == 0 || n == 1) return new String(out);
        if (out[0] != '0') return new String(out);
        int i;
        for (i = 1; i < n; i++) {
            if (out[i] != '0') break;
        }
        if (i == n) return "0";
        for (int j = 0; j < n-i; j++) {
            out[j] = out[j+i];
        }
        return (new String(out)).substring(0, n-i);
    }
}