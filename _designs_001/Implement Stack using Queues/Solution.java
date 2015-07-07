public class Solution {
    public List<Integer> testMyStack(StreamTokenizer tokenizer, List<Double> metrics) throws IOException {
        MyStack myStack = new MyStack();
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            int n;
            double startTime;
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                n = Serializer.deserializeInt(tokenizer);
                startTime = System.nanoTime();
                myStack.push(n);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 1:
                startTime = System.nanoTime();
                myStack.pop();
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 2:
                startTime = System.nanoTime();
                result.add(myStack.top());
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            case 3:
                startTime = System.nanoTime();
                result.add(myStack.empty() ? 1 : 0);
                metrics.set(0, metrics.get(0) + (double) (System.nanoTime() - startTime) / 1000000L);
                break;
            }
        }
        return result;
    }
}
