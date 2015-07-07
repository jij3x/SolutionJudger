public class Solution {
    public List<Integer> testLRUCache(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        LRUCache cache = new LRUCache(Serializer.deserializeInt(tokenizer));
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            int m, n;
            double startTime;
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                m = Serializer.deserializeInt(tokenizer);
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                cache.set(m, n);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                result.add(cache.get(n));
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}