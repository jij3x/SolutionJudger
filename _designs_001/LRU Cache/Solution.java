public class Solution {
    private int nextIntFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public List<Integer> testLRUCache(StreamTokenizer tokenizer) throws IOException {
        LRUCache cache = new LRUCache(nextIntFromStream(tokenizer));
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = nextIntFromStream(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (nextIntFromStream(tokenizer)) {
            case 0:
                cache.set(nextIntFromStream(tokenizer), nextIntFromStream(tokenizer));
                break;
            case 1:
                result.add(cache.get(nextIntFromStream(tokenizer)));
                break;
            }
        }
        return result;
    }
}