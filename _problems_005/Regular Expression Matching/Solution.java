public class Solution {
    public boolean isMatch(String s, String p) {
      return isMatch(s, p, 0, 0);
    }
    
    public boolean isMatch(String s, String p, int i, int j) {
        if (j == p.length()) return i == s.length();
    
      // next char is not '*': must match current character
      if (j == p.length()-1 || p.charAt(j+1) != '*') {
        //assert(*p != '*');
        return ((i != s.length() && p.charAt(j) == s.charAt(i)) || (p.charAt(j) == '.' && i != s.length())) && isMatch(s, p, i+1, j+1);
      }
      // next char is '*'
      while ((j < p.length() - 1 && i < s.length()) && 
          (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
        if (isMatch(s, p, i, j+2)) return true;
        i++;
      }
      return isMatch(s, p, i, j+2);
    }
}
