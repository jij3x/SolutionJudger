public class Solution {
    public int lengthOfLastWord(String s) {
        int len = 0;
        boolean inWord = false;
        for (int i = 0; i < s.length(); i++) {
            if (!inWord && !isSpace(s.charAt(i))) {
    			inWord = true;
    			len = 0;
    		} else if (inWord && isSpace(s.charAt(i))) {
    			inWord = false;
    		}
    		if (inWord) len++;
    	}
    	return len;
    }
    
    boolean isSpace(char c) {
        return c == ' ';
    }
}