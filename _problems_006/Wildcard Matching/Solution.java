public class Solution {
    public boolean isMatch(String str, String pat) {
       boolean star = false;
       int a = 0, b = 0, i = 0, j = 0;
        
        boolean exitLoop = false;
        while (!exitLoop) {
            
           for (i = a, j = b; i < str.length(); i++, j++) {
              if (j == pat.length()) {
                  if (!star) return false;
                  a++;
                  break;
              }
              char c = pat.charAt(j);
              if (c == '?') {
                    continue;
              } else if (c == '*') {
                    star = true;
                    a = i; b = j;
                    do { ++b; } while (b != pat.length() && pat.charAt(b) == '*');
                    if (b == pat.length()) return true;
                    break;
              } else {
                    if (str.charAt(i) != pat.charAt(j)) {
                        if (!star) return false;
                        a++;
                        break;
                    }
              }
           } /* endfor */
           if (i == str.length()) exitLoop = true;
        }
        
       while (j != pat.length() && pat.charAt(j) == '*') ++j;
       return (j == pat.length());

    }
}
