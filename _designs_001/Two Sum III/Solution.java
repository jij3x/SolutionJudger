public class Solution {
    public List<Boolean> testTwoSumIII(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        TwoSum twoSum = new TwoSum();
        List<Boolean> result = new ArrayList<Boolean>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            int n;
            double startTime;

            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                twoSum.add(n);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                result.add(twoSum.find(n));
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}