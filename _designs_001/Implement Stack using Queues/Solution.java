public class Solution {
    public List<Integer> testMyStack(StreamTokenizer tokenizer) throws IOException {
        MyStack myStack = new MyStack();
        List<Integer> result = new ArrayList<Integer>();

        int opsCnt = Serializer.deserializeInt(tokenizer);
        for (int i = 0; i < opsCnt; i++) {
            switch (Serializer.deserializeInt(tokenizer)) {
            case 0:
                myStack.push(Serializer.deserializeInt(tokenizer));
                break;
            case 1:
                myStack.pop();
                break;
            case 2:
                result.add(myStack.top());
                break;
            case 3:
                result.add(myStack.empty() ? 1 : 0);
                break;
            }
        }
        return result;
    }
}
