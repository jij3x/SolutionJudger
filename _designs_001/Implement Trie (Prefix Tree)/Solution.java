public class Solution {
    public List<Boolean> testTrie(StreamTokenizer tokenizer) throws IOException {
        Trie trie = new Trie();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                trie.insert(Serializer.deserializeString(tokenizer));
                break;
            case 1:
                result.add(trie.search(Serializer.deserializeString(tokenizer)));
                break;
            case 2:
                result.add(trie.startsWith(Serializer.deserializeString(tokenizer)));
                break;
            }
        }
        return result;
    }
}