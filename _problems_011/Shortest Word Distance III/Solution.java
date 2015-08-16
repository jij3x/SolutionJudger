public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length <= 0) return -1;
        int index1 = -1, index2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (word1.equals(word2)) {
                if (words[i].equals(word1)) {
                    if (index1 == -1) index1 = i;
                    else index2 = i;
                }
            } else {
                if (words[i].equals(word1)) index1 = i;
                if (words[i].equals(word2)) index2 = i;
            }
            if ((words[i].equals(word1) || words[i].equals(word2)) && index1 != -1 && index2 != -1) {
                res = Math.min(Math.abs(index1 - index2), res);
                if (word1.equals(word2)) {
                    index1 = index2;
                    index2 = -1;
                }
            }
        }

        return res;
    }
}