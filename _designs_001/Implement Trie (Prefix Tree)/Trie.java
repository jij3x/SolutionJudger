public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (p.children[c - 'a'] == null)
                p.children[c - 'a'] = new TrieNode();
            p = p.children[c - 'a'];
        }
        p.end = true;
    }

    public boolean search(String key) {
        TrieNode p = traverse(key);
        return p == null ? false : p.end;
    }
    
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }
    
    private TrieNode traverse(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (p.children[c - 'a'] == null)
                return null;
            p = p.children[c - 'a'];
        }
        return p;
    }
}