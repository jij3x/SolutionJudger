public class Solution {
    public List<Integer> testMinStack(StreamTokenizer tokenizer) throws IOException {
        MinStack minStack = new MinStack();
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                minStack.push(Serializer.deserializeInt(tokenizer));
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