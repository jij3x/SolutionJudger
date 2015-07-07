public class Solution {
    public List<Boolean> testTrie(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        Trie trie = new Trie();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            String word;
            double startTime;
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                word = Serializer.deserializeString(tokenizer);
                startTime = System.nanoTime();
                trie.insert(word);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                word = Serializer.deserializeString(tokenizer);
                startTime = System.nanoTime();
                result.add(trie.search(word));
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 2:
                word = Serializer.deserializeString(tokenizer);
                startTime = System.nanoTime();
                result.add(trie.startsWith(word));
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}