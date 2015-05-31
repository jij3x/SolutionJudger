public class Solution {
    public List<Integer> testMinStack(List<Integer> procedure) {
        MinStack minStack = new MinStack();
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0, idx = 1; i < procedure.get(0); i++) {
            int op = procedure.get(idx++);
            switch (op) {
            case 0:
                minStack.push(procedure.get(idx++));
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