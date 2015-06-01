public class TrieNode {
    public boolean end;
    public TrieNode[] children;
    public TrieNode() {
        end = false;
        children = new TrieNode[26];
    }
}
