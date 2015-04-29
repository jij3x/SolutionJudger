public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        int j = 0;
        while (j < haystack.length()) {
            int i = 0;
            int k = j;
            while (k < haystack.length() && i < needle.length() && haystack.charAt(k) == needle.charAt(i)) {
                i++;
                k++;
            }
            if (i == needle.length()) return j;
            if (k == haystack.length()) return -1;
            j++;
        }
        return -1;
    }
}