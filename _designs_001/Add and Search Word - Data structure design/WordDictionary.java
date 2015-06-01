public class WordDictionary {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean value = false;

        public TrieNode() {
        }

        public TrieNode getChild(char c) {
            int index = c - 'a';

            return children[index];
        }

        public TrieNode getOrCreateChild(char c) {
            int index = c - 'a';
            TrieNode child = children[index];

            if (child == null) {
                children[index] = new TrieNode();
            }

            return children[index];
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            node = node.getOrCreateChild(c);
        }

        node.value = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain wildcard character '.' to represent any one letter.
    public boolean search(String word) {
        return search(root, word, 0);
    }

    public boolean search(TrieNode node, String word, int start) {

        for (int i = start; i < word.length(); i++) {
            char c = word.charAt(i);

            if (c == '.') {
                for (int j = 0; j < node.children.length; j++) {
                    if (node.children[j] != null) {
                        if (search(node.children[j], word, i + 1))
                            return true;
                    }
                }

                return false;
            } else {
                node = node.getChild(c);
                if (node == null)
                    return false;
            }
        }

        return node.value;
    }
}