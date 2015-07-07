public class Solution {
    public List<Integer> testMyQueue(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        MyQueue myQueue = new MyQueue();
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            int n;
            double startTime;
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                myQueue.push(n);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                startTime = System.nanoTime();
                myQueue.pop();
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 2:
                startTime = System.nanoTime();
                result.add(myQueue.peek());
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 3:
                startTime = System.nanoTime();
                result.add(myQueue.empty() ? 1 : 0);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}
