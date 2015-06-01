public class Solution {
    public List<Integer> testLRUCache(StreamTokenizer tokenizer) throws IOException {
        LRUCache cache = new LRUCache(Serializer.deserializeInt(tokenizer));
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                cache.set(Serializer.deserializeInt(tokenizer), Serializer.deserializeInt(tokenizer));
                break;
            case 1:
                result.add(cache.get(Serializer.deserializeInt(tokenizer)));
                break;
            }
        }
        return result;
    }
}