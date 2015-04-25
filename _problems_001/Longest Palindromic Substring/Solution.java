public class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int longestBegin = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            if (Math.max(len1, len2) > maxLen) {
                maxLen = Math.max(len1, len2);
                longestBegin = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(longestBegin, longestBegin+maxLen);
    }
    public int expandAroundCenter(String s, int j, int k) {
        int n = s.length();
        while (j >= 0 && k < n) {
            if (s.charAt(j) != s.charAt(k)) {
                break;
            }
            j--; k++;
        }
        return (k-j-1);
    }
}
