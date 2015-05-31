public class Solution {
    private int nextIntFromStream(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public List<Integer> testMinStack(StreamTokenizer tokenizer) throws IOException {
        MinStack minStack = new MinStack();
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = nextIntFromStream(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (nextIntFromStream(tokenizer)) {
            case 0:
                minStack.push(nextIntFromStream(tokenizer));
                break;
            case 1:
                minStack.pop();
                break;
            case 2:
                result.add(minStack.top());
                break;
            case 3:
                result.add(minStack.getMin());
                break;
            }
        }
        return result;
    }
}