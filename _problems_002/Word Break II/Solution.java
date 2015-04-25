public class Solution {
    Map<String, ArrayList<String>> memoized;
    public ArrayList<String> wordBreak(String input, Set<String> dict) {
        memoized = new HashMap<String, ArrayList<String>>();
        return wordBreakDfs(input, dict);
    }
    
    public ArrayList<String> wordBreakDfs(String input, Set<String> dict) {
      ArrayList<String> ans = new ArrayList<String>();
      if (input.equals("")) {
          ans.add("");
          return ans;
      }
      if (memoized.containsKey(input)) {
          return memoized.get(input);
      }
      int len = input.length();
      for (int i = 1; i <= len; i++) {
        String prefix = input.substring(0, i);
        if (dict.contains(prefix)) {
          String suffix = input.substring(i, len);
          ArrayList<String> segSuffix = wordBreakDfs(suffix, dict);
          for (String suf : segSuffix) {
              if (suf.equals("")) {
                  ans.add(prefix);
              } else {
                ans.add(prefix + " " + suf);
              }
          }
        }
      }
      memoized.put(input, ans);
      return ans;
    }
}