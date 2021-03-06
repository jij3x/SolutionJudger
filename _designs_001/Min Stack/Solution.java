public class Solution {
    public List<Integer> testMinStack(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        MinStack minStack = new MinStack();
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            int n;
            double startTime;
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                minStack.push(n);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                startTime = System.nanoTime();
                minStack.pop();
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 2:
                startTime = System.nanoTime();
                result.add(minStack.top());
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 3:
                startTime = System.nanoTime();
                result.add(minStack.getMin());
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}