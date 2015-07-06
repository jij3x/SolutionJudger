public class Solution {
    public List<Boolean> testWordDictionary(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        WordDictionary wd = new WordDictionary();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            String word;
            double startTime;
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                word = Serializer.deserializeString(tokenizer);
                startTime = System.nanoTime();
                wd.addWord(word);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                word = Serializer.deserializeString(tokenizer);
                startTime = System.nanoTime();
                result.add(wd.search(word));
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}