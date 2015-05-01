public class Solution {
    public String convertToTitle(int n) {
        int base = 64;
        StringBuilder ret = new StringBuilder();
        while (n > 0) {
            int last = n % 26;
            last = (last == 0) ? 26:last;
            ret.append(Character.toChars(base + last));
            n = (n - last) / 26;
        }
        return ret.reverse().toString();
    }
}