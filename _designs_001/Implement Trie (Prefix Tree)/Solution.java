public class Solution {
    private int nextIntFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private String nextStringFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }

    public List<Boolean> testTrie(StreamTokenizer tokenizer) throws IOException {
        Trie trie = new Trie();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = nextIntFromStream(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (nextIntFromStream(tokenizer)) {
            case 0:
                trie.insert(nextStringFromStream(tokenizer));
                break;
            case 1:
                result.add(trie.search(nextStringFromStream(tokenizer)));
                break;
            case 2:
                result.add(trie.startsWith(nextStringFromStream(tokenizer)));
                break;
            }
        }
        return result;
    }
}