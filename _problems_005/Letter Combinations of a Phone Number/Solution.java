public class Solution {
    static String[] mapDigitToLetters = new String[] {
        "",
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz",
    };
    public void letterCombinations(String s, int idx, String letters, ArrayList<String> ans) {
        if (idx == s.length()) {
            ans.add(letters);
            return;
        }
        for (int i = 0; i < mapDigitToLetters[s.charAt(idx)-'0'].length(); i++) {
            char c = mapDigitToLetters[s.charAt(idx)-'0'].charAt(i);
            letters = letters + c;
            letterCombinations(s, idx+1, letters, ans);
            letters = letters.substring(0, letters.length()-1);
        }
    }
    public List<String> letterCombinations(String digits) {
        ArrayList<String> ans = new ArrayList<String>();
        if (digits.length() == 0) return ans;
        String letters = "";
        letterCombinations(digits, 0, letters, ans);
        return ans;
    }
}
