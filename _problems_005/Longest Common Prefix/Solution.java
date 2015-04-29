public class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 0) return "";
        StringBuilder lcp = new StringBuilder();
        int m = strs[0].length();
        for (int i = 0; i < m; i++) {
            int k = lcp.length();
            char c = strs[0].charAt(k);
            for (int j = 1; j < n; j++) {
                if (k >= strs[j].length() || strs[j].charAt(k) != c)
                    return lcp.toString();
            }
            lcp.append(c);
        }
        return lcp.toString();
    }
}