public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<ArrayList<String>> tokensByLine = getTokensByLine(words, L);
        return justify(tokensByLine, L);
    }
    
    ArrayList<String> getLine(String[] tokens, int startIndex, int L) {
      ArrayList<String> line = new ArrayList<String>();
      int len = 0;
      for (int i = startIndex; i < tokens.length; i++) {
        len += tokens[i].length();
        if (i > startIndex) len++;
        if (len > L) break;
        line.add(tokens[i]);
      }
      return line;
    }
    
    ArrayList<ArrayList<String>> getTokensByLine(String[] tokens, int L) {
      ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
      int i = 0;
      while (i < tokens.length) {
        ArrayList<String> line = getLine(tokens, i, L);
        ret.add(line);
        i += line.size();
      }
      return ret;
    }
    
    int getTotalLetters(ArrayList<String> words) {
      int len = 0;
      for (int i = 0; i < words.size(); i++) {
        len += words.get(i).length();
      }
      return len;
    }
    
    String getSpace(int n) {
      StringBuffer spaces = new StringBuffer();
      for (int i = 0; i < n; i++) {
        spaces.append(' ');
      }
      return spaces.toString();
    }
    
    ArrayList<String> justify(ArrayList<ArrayList<String>> tokensByLine, int L) {
      ArrayList<String> ret = new ArrayList<String>();
      int totalLines = tokensByLine.size();
      for (int i = 0; i < totalLines - 1; i++) {
        int numWords = tokensByLine.get(i).size();
        int totalLetters = getTotalLetters(tokensByLine.get(i));
        assert(numWords >= 1);
        if (numWords == 1) { // left-justify
          ret.add(tokensByLine.get(i).get(0) + getSpace(L - totalLetters));
          continue;
        }
        int totalSpace = numWords - 1;
        int numSpaces = L - totalLetters;
        assert(numSpaces >= 0);
        int space = numSpaces / totalSpace;
        int extra = numSpaces % totalSpace;
        StringBuffer line = new StringBuffer();
        for (int j = 0; j < numWords; j++) {
          line.append(tokensByLine.get(i).get(j));
          if (j != numWords - 1) {
            int extraSpace = space;
            if (extra > 0) {
              extraSpace++;
              extra--;
            }
            line.append(getSpace(extraSpace));
          }
        }
        ret.add(line.toString());
      }
      int numWords = tokensByLine.get(totalLines - 1).size();
      StringBuffer line = new StringBuffer();
      for (int i = 0; i < numWords; i++) {
        line.append(tokensByLine.get(totalLines - 1).get(i));
        if (i != numWords - 1) {
            line.append(' ');
        }
      }
      ret.add(line.toString() + getSpace(L - line.length()));
      return ret;
    }
}