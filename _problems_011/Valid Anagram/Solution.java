public class Solution {
    public boolean isAnagram(String s, String t) {
        int[] cnts = new int[256];
        for (int i = 0; i < s.length(); i++)
            cnts[s.charAt(i)]++;
        for (int i = 0; i < t.length(); i++)
            cnts[t.charAt(i)]--;
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] != 0)
                return false;
        }
        return true;
    }
}