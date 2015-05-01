public class Solution {
    public int titleToNumber(String s) {
        int n = 0;
        for (char c : s.toCharArray()) n = n * 26 + (c & 31);
        return n;
    }
}