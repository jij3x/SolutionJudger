public class Solution {
    public List<String> anagrams(String[] strs) {
        int n = strs.length;
        ArrayList<String> ret = new ArrayList<String>();
        if (n == 0) return ret;
        Anagram[] anagrams = new Anagram[n];
        for (int i = 0; i < n; i++) {
            anagrams[i] = new Anagram(strs[i]);
        }
        Arrays.sort(anagrams, new Comparator<Anagram>() {
            public int compare(Anagram a1, Anagram a2) {
                return (a1.key.compareTo(a2.key));
            }
        });
        String key = anagrams[0].key;
        boolean same = false;
        for (int i = 1; i < n; i++) {
            if (anagrams[i].key.equals(key)) {
                ret.add(anagrams[i-1].word);
                same = true;
            } else {
                key = anagrams[i].key;
                if (same) {
                    ret.add(anagrams[i-1].word);
                }
                same = false;
            }
        }
        if (same) {
            ret.add(anagrams[n-1].word);
        }
        return ret;
    }
    class Anagram {
        public String word;
        public String key;
        Anagram(String w) {
            word = w;
            char[] k = w.toCharArray();
            Arrays.sort(k);
            key = String.valueOf(k);
        }
    }
}
