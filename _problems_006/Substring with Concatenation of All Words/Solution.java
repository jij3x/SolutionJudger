public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (String s : L)
            dict.put(s, dict.containsKey(s) ? dict.get(s) + 1 : 1);

        for (int i = 0, wl = L[0].length(); i < wl; i++) {
            HashMap<String, Integer> memo = new HashMap<String, Integer>();
            for (int missing = L.length, right = i, left = right; right + wl <= S.length(); right += wl) {
                String word = S.substring(right, right + wl);
                if (!dict.containsKey(word)) {
                    left = right + wl;
                    missing = L.length;
                    memo.clear();
                    continue;
                }

                memo.put(word, memo.containsKey(word) ? memo.get(word) + 1 : 1);
                if (memo.get(word) > dict.get(word)) {
                    for (; left <= right; missing++) {
                        String toDrop = S.substring(left, left + wl);
                        memo.put(toDrop, memo.get(toDrop) - 1);
                        left += wl;
                        if (toDrop.equals(word))
                            break;
                    }
                } else if (--missing == 0) {
                    result.add(left);
                    String toDrop = S.substring(left, left + wl);
                    left += wl;
                    memo.put(toDrop, memo.get(toDrop) - 1);
                    missing++;
                }
            }
        }
        return result;
    }
}